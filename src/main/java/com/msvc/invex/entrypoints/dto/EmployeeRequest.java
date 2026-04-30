package com.msvc.invex.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotBlank(message = "El primer nombre es obligatorio")
    private String firstName;

    private String middleName;

    @NotBlank(message = "El apellido paterno es obligatorio")
    private String lastName;

    private String secondLastName;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 65, message = "La edad máxima es 65 años")
    private Integer age;

    @NotBlank(message = "El sexo es obligatorio")
    private String gender;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotBlank(message = "El puesto es obligatorio")
    private String position;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean active;
}