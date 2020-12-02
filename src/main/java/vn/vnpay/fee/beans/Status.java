package vn.vnpay.fee.beans;

public enum Status {

    ACTIVE(1, "Hoạt động"),
    LOCK(-1, "Khóa"),
    INIT(2, "Khởi tạo");

    private int code;
    private String message;

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

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
