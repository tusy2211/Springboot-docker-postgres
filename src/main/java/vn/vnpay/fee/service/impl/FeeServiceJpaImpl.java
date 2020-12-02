package vn.vnpay.fee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vnpay.fee.beans.BaseRes;
import vn.vnpay.fee.beans.SearchJpaReq;
import vn.vnpay.fee.dao.FeeJpaDao;
import vn.vnpay.fee.service.FeeJpaService;

@Service
public class FeeServiceJpaImpl implements FeeJpaService {

    @Autowired
    FeeJpaDao feeDao;

    @Override
    public BaseRes getListFee(SearchJpaReq req) {
        return feeDao.getListFee(req);
    }
}
