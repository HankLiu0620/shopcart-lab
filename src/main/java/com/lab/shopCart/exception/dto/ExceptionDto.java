package com.lab.shopCart.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    @JsonProperty("returnCode")
    private String returnCode;
    @JsonProperty("detail")
    private Detail detail;
    @JsonProperty("returnMsg")
    private String returnMsg;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Detail{
        @JsonProperty("errorCode")
        private String errorCode;
        @JsonProperty("errorMsg")
        private String errorMsg;
    }
}
