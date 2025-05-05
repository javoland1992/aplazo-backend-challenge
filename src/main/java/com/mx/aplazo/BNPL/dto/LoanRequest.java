package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Customer ID", example = "cde1581f-df97-4054-9a2a-ec68cfaed11b")
    String customerId;

    @NotNull(message = "is null")
    @DecimalMin(value = "0.0", message = "must be greater than or equal to 0.0")
    @Schema(description = "Loan amount", example = "1234.45")
    BigDecimal amount;
}
