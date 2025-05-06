package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@lombok.Generated
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthSignUpResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
