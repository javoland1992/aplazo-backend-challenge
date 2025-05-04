package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@lombok.Generated
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentPlan {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal commissionAmount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    InstallmentResponse installmentResponse;
}
