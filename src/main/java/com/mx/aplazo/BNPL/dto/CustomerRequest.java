package com.mx.aplazo.BNPL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonInclude()
public class CustomerRequest {

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    private String firstName;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    private String lastName;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    private String secondLastName;

    @NotNull(message = "is null")
    @NotBlank(message = "is blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "invalid date format, must be yyyy-MM-dd")
    private String dateOfBirth;
}
