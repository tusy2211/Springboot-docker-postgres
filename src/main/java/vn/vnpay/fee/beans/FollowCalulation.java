package vn.vnpay.fee.beans;

public enum FollowCalulation {
    DAY(1, "Ngày"),
    WEEK(2, "Tuần"),
    MONTH(3, "Tháng");


    private int code;
    private String message;

    FollowCalulation(int code, String message) {
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
