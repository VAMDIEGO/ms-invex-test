package com.msvc.invex.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Schema(description = "Solicitud para crear o actualizar un empleado")
public class EmployeeRequest {

    @NotBlank(message = "El primer nombre es obligatorio")
    @Schema(example = "Juan", description = "Primer nombre del empleado")
    private String firstName;

    @Schema(example = "Carlos", description = "Segundo nombre del empleado (opcional)")
    private String middleName;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Schema(example = "Perez", description = "Apellido paterno del empleado")
    private String lastName;

    @Schema(example = "Lopez", description = "Apellido materno del empleado (opcional)")
    private String secondLastName;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 65, message = "La edad máxima es 65 años")
    @Schema(example = "28", description = "Edad del empleado (18-65 años)")
    private Integer age;

    @NotBlank(message = "El sexo es obligatorio")
    @Schema(example = "M", description = "Sexo del empleado (M/F)")
    private String gender;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(
            example = "15-08-1995",
            description = "Fecha de nacimiento en formato dd-MM-yyyy"
    )
    private LocalDate birthDate;

    @NotBlank(message = "El puesto es obligatorio")
    @Schema(example = "Developer", description = "Puesto del empleado")
    private String position;

    @NotNull(message = "El estado activo es obligatorio")
    @Schema(example = "true", description = "Indica si el empleado está activo")
    private Boolean active;
}