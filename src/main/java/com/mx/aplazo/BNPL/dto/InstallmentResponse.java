package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.aplazo.BNPL.model.Installment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@lombok.Generated
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Installment> installments;
}
