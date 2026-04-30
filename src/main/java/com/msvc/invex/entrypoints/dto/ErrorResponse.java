package com.msvc.invex.entrypoints.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

	private String code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
