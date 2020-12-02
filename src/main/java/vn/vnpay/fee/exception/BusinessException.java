package vn.vnpay.fee.exception;

import lombok.*;
import vn.vnpay.fee.constants.Rescode;
@Data
public class BusinessException extends Exception {

    private Rescode c;
    private String code;
    private String description;

    public Rescode getResCode() {
        return c;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String code, String description) {
        super(code + " - " + description);
        this.code = code;
        this.description = description;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
