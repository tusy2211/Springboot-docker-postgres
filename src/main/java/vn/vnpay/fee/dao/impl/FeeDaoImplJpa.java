package vn.vnpay.fee.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import vn.vnpay.fee.beans.*;
import vn.vnpay.fee.dao.FeeJpaDao;
import vn.vnpay.fee.dao.specification.SpecificationCustom;
import vn.vnpay.fee.repository.FeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class FeeDaoImplJpa implements FeeJpaDao {

    @Autowired
    FeeRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<FeeJpaDTO> getListFee(SearchJpaReq req) {
        Specification conditions = Specification.where(SpecificationCustom.hasValue(FeeBean_.PAY_TYPE,
                req.getPayType())
                .and(SpecificationCustom.hasValue(FeeBean_.CALCULATION_FORM, 1)
                .or((SpecificationCustom.hasValue(FeeBean_.CALCULATION_FORM, 2))
                        .and(SpecificationCustom.hasValue(FeeBean_.MAX_FEE_LEVEL,1)))));
        PageRequest pageable = PageRequest.of(req.getFromRow(), req.getToRow(), Sort.by(FeeBean_.ID).descending());
        Page<FeeBean> bankPage = repository.findAll(conditions, pageable);
        Long totalElement = bankPage.getTotalElements();
        List<FeeJpaDTO> jpaDTOList = bankPage.getContent().stream()
                .map(x -> {
                    return modelMapper.map(x, FeeJpaDTO.class);
                }).collect(Collectors.toList());
        return jpaDTOList;
    }


}
