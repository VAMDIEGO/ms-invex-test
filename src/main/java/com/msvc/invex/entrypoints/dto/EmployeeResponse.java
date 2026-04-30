package com.msvc.invex.entrypoints.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Respuesta con la información de un empleado")
public class EmployeeResponse {

    @Schema(example = "1", description = "Identificador único del empleado")
    private Long id;

    @Schema(example = "Juan Carlos Perez Lopez", description = "Nombre completo del empleado generado automáticamente")
    private String fullName;

    @Schema(example = "28", description = "Edad del empleado")
    private Integer age;

    @Schema(example = "M", description = "Sexo del empleado")
    private String gender;

    @Schema(example = "Developer", description = "Puesto del empleado")
    private String position;

    @Schema(example = "true", description = "Estado del empleado (activo/inactivo)")
    private Boolean active;

    @Schema(example = "1995-08-15", description = "Fecha de nacimiento del empleado")
    private LocalDate birthDate;

    @Schema(example = "2026-04-30T10:15:30", description = "Fecha de creación del registro")
    private LocalDateTime createdAt;
}