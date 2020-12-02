package vn.vnpay.fee.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "QR_FEE")
public class FeeBean {

    @Id
    @Schema(description = "ID tự tăng của bảng QR_FEE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Schema(description = "Mã phí")
    @Size(min = 0, max = 10, message = "feeCode max 10 characters")
    @Column(name = "FEE_CODE")
    private String feeCode;  // ma phi

    @Schema(description = "Tên mức phí")
    @Size(min = 0, max = 100, message = "FeeName max 100 characters")
    @Column(name = "FEE_NAME")
    private String feeName;  // ten muc phi

    @Schema(description = "Hình thức thanh toán. 1: Cố định, 2: bậc thang")
    @Column(name = "PAY_TYPE")
    private String payType;  //  loai phi

    @Schema(description = "Tên mức phí")
    @Size(min = 0, max = 1000, message = "description max 1000 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "DESCRIPTION")
    private String description;  // ten muc phi

    @Schema(description = "Người tạo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "CREATE_USER")
    private String createUser;

    @Schema(description = "Người sửa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "MODIFY_USER")
    private String modifyUser;

    @Schema(description = "Ngày tạo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "CREATE_DATE")
    private String createDate;

    @Schema(description = "Ngày sửa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "MODIFY_DATE")
    private String modifyDate;

    @Schema(description = "Người khóa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "LOCK_USER")
    private String lockUser; // nguoi khoa

    @Schema(description = "Ngày khóa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "LOCK_DATE")
    private String lockDate; // ngay khoa

    @Schema(description = "Người mở khóa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "UNLOCK_USER")
    private String unlockUser;

    @Schema(description = "Ngày mở khóa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "UNLOCK_DATE")
    private String unlockDate;

    @Schema(description = "Người mở khóa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "COPY_USER")
    private String copyUser;

    @Schema(description = "Ngày mở khóa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "COPY_DATE")
    private String copyDate;

    @Schema(description = "Trạng thái: 1:Hoat dong 2: Khoi tao -1: Khoa")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "STATUS")
    private String status;

    @Schema(description = "Hình thức tính. 1: Theo công thức, 2: theo bậc thang")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "CALCULATION_FORM")
    private String calculationForm; // hinh thuc tinh

    @Schema(description = "Quy luật tính. 1: doanh số, 2: số lượng gd, 3: giá trị gd")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "CALCULATION_RULES")
    private String calculationRules; // quy luat tinh

    @Schema(description = "Tính theo. 1: ngày, 2: Tuần, 3: Tháng")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "FOLOW_CAL")
    private String  folowCal; // tinh theo

    @Schema(description = "Phí xử lý giao dịch")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "TRANSACTION_HANDLE_FEE")
    private Double transactionHandleFee; // Phi xu ly giao dich

    @Schema(description = "phí thanh toán")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "PAYMENT_FEE")
    private Double paymentFee; //Phi thanh toan

    @Schema(description = "Giá trị/số lượng giao dịch TỪ")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "TRANS_QUANTITY_FROM")
    private Double transQuantityFrom; // Giá trị/số lượng giao dịch TỪ

    @Schema(description = "Cấp bậc phí theo bậc thang. Ex: 1->9")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "FEE_LEVEL")
    private Long level;

    @Schema(description = "Số bậc của mức phí tính theo bậc thang")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "NUMBER_LEVEL")
    private Long numberLevel;

    @Schema(description = "Bậc tính phí cao nhất của phí bậc thang. description = 1")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "MAX_FEE_LEVEL")
    private Long maxFeeLevel;

//    @Schema(description = "STT danh sách")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @Transient
//    private Long rowNum;
}
