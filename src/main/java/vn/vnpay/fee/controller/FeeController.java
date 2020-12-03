package vn.vnpay.fee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.vnpay.fee.beans.BaseRes;
import vn.vnpay.fee.beans.SearchJpaReq;
import vn.vnpay.fee.beans.SearchReq;
import vn.vnpay.fee.exception.BusinessException;
import vn.vnpay.fee.exception.DaoException;
import vn.vnpay.fee.service.FeeJpaService;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@Validated
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    FeeJpaService feeJpaService;


    @PostMapping(value = "/test")
    public String test() {
        return "Hello Spring boot docker";
    }

    @Operation(summary = "List All Fee using JPA")
    @PostMapping(value = "/all-jpa")
    public BaseRes getAllJpa(@Valid @RequestBody SearchJpaReq req) throws Exception {
        return feeJpaService.getListFee(req);
    }
}
