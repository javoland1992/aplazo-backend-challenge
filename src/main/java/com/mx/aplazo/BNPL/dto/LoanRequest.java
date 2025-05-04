package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude()
public class LoanRequest {

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    String customerId;

    @NotNull(message = "is null")
    @DecimalMin(value = "0.0", message = "must be greater than or equal to 0.0")
    BigDecimal amount;
}
