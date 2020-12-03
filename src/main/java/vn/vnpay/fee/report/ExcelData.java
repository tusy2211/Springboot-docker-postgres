package vn.vnpay.fee.report;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExcelData {

    private String sheetName;
    private List data;

    public static ExcelData of(String sheetName, List data) {
        return ExcelData.builder().sheetName(sheetName).data(data).build();
    }
}
