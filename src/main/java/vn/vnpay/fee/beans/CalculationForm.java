package vn.vnpay.fee.beans;

public enum CalculationForm {

    FIXED(1,"Cố định"),
    STAIRS(2, "Bậc thang");

    private int code;
    private String message;

    CalculationForm(int code, String message) {
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
