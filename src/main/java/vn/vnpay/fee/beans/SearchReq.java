package vn.vnpay.fee.beans;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Object search request")
public class SearchReq {
    @Schema(description = "0: lấy tất cả. 1: the noi dia, 2: the quoc te trong nuoc, 3: the quoc te ngoai nuoc")
    @NotNull
    private Long payType;
}
