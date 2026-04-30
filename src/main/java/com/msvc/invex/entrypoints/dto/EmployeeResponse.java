package com.msvc.invex.entrypoints.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EmployeeResponse {

    private Long id;
    private String fullName;
    private Integer age;
    private String gender;
    private String position;
    private Boolean active;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    
}