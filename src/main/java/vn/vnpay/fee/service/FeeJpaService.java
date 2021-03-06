package vn.vnpay.fee.service;

import vn.vnpay.fee.beans.BaseRes;
import vn.vnpay.fee.beans.FeeBean;
import vn.vnpay.fee.beans.SearchJpaReq;
import vn.vnpay.fee.beans.SearchReq;
import vn.vnpay.fee.exception.BusinessException;
import vn.vnpay.fee.exception.DaoException;

public interface FeeJpaService {

    BaseRes getListFee(SearchJpaReq req) throws Exception;
}
