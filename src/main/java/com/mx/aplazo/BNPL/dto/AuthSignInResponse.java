package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@lombok.Generated
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthSignInResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String access_token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token_type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer expires_in;
}
