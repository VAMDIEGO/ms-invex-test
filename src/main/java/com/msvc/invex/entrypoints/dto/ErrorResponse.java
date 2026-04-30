package com.msvc.invex.entrypoints.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Estructura estándar de respuesta de error de la API")
public class ErrorResponse {

    @Schema(example = "EMPLOYEE_NOT_FOUND", description = "Código interno del error")
    private String code;

    @Schema(example = "Employee not found", description = "Mensaje descriptivo del error")
    private String message;

    @Schema(
            example = "[\"id must not be null\", \"age must be greater than 18\"]",
            description = "Lista de detalles adicionales del error"
    )
    private List<String> details;

    @Schema(
            example = "2026-04-30T10:15:30",
            description = "Fecha y hora en la que ocurrió el error"
    )
    private LocalDateTime timestamp;
}