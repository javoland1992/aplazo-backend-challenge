package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@lombok.Generated
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal creditLineAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal availableCreditLineAmount;
}
