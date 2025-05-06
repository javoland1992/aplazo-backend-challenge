package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude()
public class AuthRequest {
    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Schema(description = "Username to create a new user", example = "testuser")
    private String username;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Schema(description = "Password to create new user", example = "testpass")
    private String password;
}
