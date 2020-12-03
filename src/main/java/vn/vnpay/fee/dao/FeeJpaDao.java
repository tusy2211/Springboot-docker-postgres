package vn.vnpay.fee.dao;

import vn.vnpay.fee.beans.*;
import vn.vnpay.fee.exception.BusinessException;
import vn.vnpay.fee.exception.DaoException;

import java.util.List;

public interface FeeJpaDao {

    List<FeeJpaDTO> getListFee(SearchJpaReq req);
}
