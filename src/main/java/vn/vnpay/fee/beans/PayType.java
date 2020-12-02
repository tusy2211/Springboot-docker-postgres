package vn.vnpay.fee.beans;

public enum PayType {
    INTERNAL_CARD(1L,"Thẻ nội địa"),
    DOMESTIC_CARD(2L,"Thẻ quốc tế trong nước"),
    INTERNATIONAL_CARD(3L,"Thẻ quốc tế ngoài nước");


    private Long code;
    private String message;

    PayType(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
