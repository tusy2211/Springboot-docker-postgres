package vn.vnpay.fee.exception;

import lombok.Data;
import vn.vnpay.fee.constants.Rescode;

@Data
public class DaoException extends Exception {

    private Rescode c;
    private String code;
    private String description;

    public Rescode getResCode() {
        return c;
    }

    public DaoException() {
        super();
    }

    public DaoException(String code, String description) {
        super(code + " - " + description);
        this.code = code;
        this.description = description;
    }

    public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
