package vn.vnpay.fee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.vnpay.fee.beans.BaseRes;
import vn.vnpay.fee.beans.FeeJpaDTO;
import vn.vnpay.fee.beans.SearchJpaReq;
import vn.vnpay.fee.dao.FeeJpaDao;
import vn.vnpay.fee.minio.FileBuilder;
import vn.vnpay.fee.report.ExcelBuilder;
import vn.vnpay.fee.service.FeeJpaService;

import java.io.InputStream;
import java.util.List;

@Service
public class FeeServiceJpaImpl implements FeeJpaService {

    @Autowired
    FeeJpaDao feeDao;

    @Autowired
    FileBuilder fileBuilder;

    @Autowired
    ExcelBuilder excelBuilder;

    @Value(value = "${spring.minio.bucket}")
    private String bucket;

    @Override
    public BaseRes getListFee(SearchJpaReq req) throws Exception {
        List<FeeJpaDTO> listFee = feeDao.getListFee(req);
        if (req.getExcel()) {
            InputStream inputStream = excelBuilder.exportExcelFile("FEE.xlsx", listFee);
            String url =  fileBuilder.uploadFile(bucket, "FEE.xlsx", inputStream);
            return BaseRes.of(url);
        }
        return BaseRes.of(listFee);
    }
}
