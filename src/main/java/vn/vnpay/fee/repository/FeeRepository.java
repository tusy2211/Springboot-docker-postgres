package vn.vnpay.fee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.vnpay.fee.beans.FeeBean;

@Repository
public interface FeeRepository extends JpaRepository<FeeBean, Long>, JpaSpecificationExecutor<FeeBean> {
}
