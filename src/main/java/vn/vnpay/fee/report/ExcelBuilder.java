package vn.vnpay.fee.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiContext;
import org.jxls.util.JxlsHelper;
import vn.vnpay.fee.minio.FileBuilder;

@Slf4j
@Component
public class ExcelBuilder {

    private static final String CELL_A1 = "!A1";

    private static final String SHEET_NAME = "Sheet";

    @Value("${report.excel.sheet-size:500000}")
    int sheetSize;

    public InputStream exportExcelFile(String fileName, List list) throws Exception {
        long cur = System.currentTimeMillis();
        log.info("Start export excel with fileName: {}, size data: {} ",
                fileName, list.size());
        List<ExcelData> excelDataList = splitBatch(list, sheetSize);
        try (InputStream is = ExcelBuilder.class.getClassLoader().getResourceAsStream(fileName);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Context context = new PoiContext();
            context.putVar("excelDatas", excelDataList);
            JxlsHelper.getInstance()
                    .setEvaluateFormulas(true)
                    .processTemplateAtCell(is, outputStream, context,
                            excelDataList.get(0).getSheetName() + CELL_A1);
            try (InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray())) {
                log.info("End export is success file: {}, with time: {}", fileName, (System.currentTimeMillis() - cur));
                return inputStream;
            }
        }
    }

    /**
     * Paging sheet excel file by sheetSize
     */
    private List<ExcelData> splitBatch(List list, int sheetSize) {
        int sheet = list.size() / sheetSize + 1;
        List<ExcelData> excelData = new ArrayList<>();
        for (int i = 1; i <= sheet; i++) {
            int begin = (i - 1) * sheetSize;
            int end = Math.min(i * sheetSize, list.size());
            excelData.add(ExcelData.of(SHEET_NAME + i, list.subList(begin, end)));
        }
        return excelData;
    }
}
