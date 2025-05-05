package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonInclude()
public class CustomerRequest {

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Schema(description = "First name of the customer", example = "Pepe")
    private String firstName;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Schema(description = "Last name of the customer", example = "García")
    private String lastName;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Schema(description = "Second last name of the customer", example = "Flores")
    private String secondLastName;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "invalid date format, must be yyyy-MM-dd")
    @Schema(description = "Date of birth of the customer", example = "1998-07-21", defaultValue = "1998-07-21")
    private String dateOfBirth;
}
