package com.msvc.invex.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Long id;

	private String firstName;
	private String middleName;
	private String lastName;
	private String secondLastName;

	private Integer age;
	private String gender;

	private LocalDate birthDate;

	private String position;

	private LocalDateTime createdAt;

	private Boolean active;
}