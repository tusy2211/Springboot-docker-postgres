package vn.vnpay.fee.beans;

public enum CalculationRule {

    SALES(1, "Doanh số"),
    TRANS_QUANTITY(2, "Số lượng giao dịch"),
    TRANS_VALUE(3, "Giá trị giao dịch");


    private int code;
    private String message;

    CalculationRule(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
