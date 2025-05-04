package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@lombok.Generated
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String estatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    PaymentPlan paymentPlan;

}
