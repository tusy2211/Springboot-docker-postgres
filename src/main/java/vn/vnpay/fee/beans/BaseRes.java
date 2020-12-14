package vn.vnpay.fee.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.vnpay.fee.constants.Rescode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRes<T> {

    @Schema(description = "Code response")
    private String code;

    @Schema(description = "Message response")
    private String msg;

    @Schema(description = "url download file")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    @Schema(description = "Object response")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static BaseRes of(Object data) {
        return of(Rescode.SUCCESS.code(), Rescode.SUCCESS.description(), data);
    }

    public static BaseRes of(String url) {
        return of(Rescode.SUCCESS.code(), Rescode.SUCCESS.description(), url);
    }

    public static BaseRes of(Rescode resCode) {
        return of(resCode.code(), resCode.description());
    }

    public static BaseRes of(String code, String msg) {
        return of(code, msg,null);
    }

    public static BaseRes of(String code, String msg, Object data) {
        return BaseRes.builder().code(code).msg(msg).data(data).build();
    }

    public static BaseRes of(String code, String msg, String url) {
        return BaseRes.builder().code(code).msg(msg).url(url).build();
    }
}
