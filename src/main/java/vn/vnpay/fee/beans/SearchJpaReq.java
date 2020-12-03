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
public class SearchJpaReq {
    @Schema(description = "1: the noi dia, 2: the quoc te trong nuoc, 3: the quoc te ngoai nuoc")
    @NotNull
    private Long payType;

    @Schema(description = "Check export excel?")
    private Boolean excel;

    @Schema(description = "Pagination. Ex: fromRow:0 toRow: 10")
    private int fromRow;

    @Schema(description = "Pagination. Ex: fromRow:0 toRow: 10")
    private int toRow;
}
