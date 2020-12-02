package vn.vnpay.fee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import vn.vnpay.fee.beans.BaseRes;
import vn.vnpay.fee.constants.Rescode;
import vn.vnpay.fee.exception.BusinessException;
import vn.vnpay.fee.exception.DaoException;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleAllException(Exception ex, WebRequest request) {
        log.error("err: ", ex);
        BaseRes baseRes = null;
        if (ex instanceof NullPointerException) {
            baseRes = BaseRes.of(Rescode.MAINTENANCE);
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex1 = (MethodArgumentNotValidException) ex;
            BindingResult result = ex1.getBindingResult();
            final List<FieldError> fieldErrors = result.getFieldErrors();
            baseRes = BaseRes.of(Rescode.MAINTENANCE.code(),
                    fieldErrors.get(0).getField() + " "+  fieldErrors.get(0).getDefaultMessage());
        } else if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException ex1 = (ConstraintViolationException) ex;
            baseRes = BaseRes.of(Rescode.MAINTENANCE.code(), ex1.getMessage());
        } else if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            baseRes = BaseRes.of(businessException.getCode(), businessException.getDescription());
        } else if (ex instanceof DaoException) {
            DaoException daoException = (DaoException) ex;
            baseRes = BaseRes.of(daoException.getCode(), daoException.getDescription());
        } else {
            baseRes = BaseRes.of(Rescode.EXCEPTION);
        }

        return ResponseEntity.ok(baseRes);
    }
}
