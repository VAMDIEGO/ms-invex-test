package com.msvc.invex.entrypoints.mapper;

import java.time.LocalDateTime;

import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.entrypoints.dto.EmployeeRequest;
import com.msvc.invex.entrypoints.dto.EmployeeResponse;
import com.msvc.invex.infrastructure.persistence.EmployeeEntity;

public class EmployeeMapper {

	public static Employee toDomain(EmployeeRequest dto) {
		if (dto == null)
			return null;

		return Employee.builder().firstName(dto.getFirstName()).middleName(dto.getMiddleName())
				.lastName(dto.getLastName()).secondLastName(dto.getSecondLastName()).age(dto.getAge())
				.gender(dto.getGender()).birthDate(dto.getBirthDate()).position(dto.getPosition())
				.active(dto.getActive()).build();
	}

	public static EmployeeEntity toEntity(Employee domain) {
		if (domain == null)
			return null;

		return EmployeeEntity.builder().id(domain.getId()).firstName(domain.getFirstName())
				.middleName(domain.getMiddleName()).lastName(domain.getLastName())
				.secondLastName(domain.getSecondLastName()).age(domain.getAge()).gender(domain.getGender())
				.birthDate(domain.getBirthDate()).position(domain.getPosition())
				.createdAt(domain.getCreatedAt() != null ? domain.getCreatedAt() : LocalDateTime.now())
				.active(domain.getActive()).build();
	}

	public static Employee toDomain(EmployeeEntity entity) {
		if (entity == null)
			return null;

		return Employee.builder().id(entity.getId()).firstName(entity.getFirstName()).middleName(entity.getMiddleName())
				.lastName(entity.getLastName()).secondLastName(entity.getSecondLastName()).age(entity.getAge())
				.gender(entity.getGender()).birthDate(entity.getBirthDate()).position(entity.getPosition())
				.createdAt(entity.getCreatedAt()).active(entity.getActive()).build();
	}

	public static EmployeeResponse toResponse(Employee domain) {
		if (domain == null)
			return null;

		return EmployeeResponse.builder().id(domain.getId()).fullName(buildFullName(domain)).age(domain.getAge())
				.gender(domain.getGender()).birthDate(domain.getBirthDate()).position(domain.getPosition())
				.createdAt(domain.getCreatedAt()).active(domain.getActive()).build();
	}

	private static String buildFullName(Employee e) {
		return String.join(" ", safe(e.getFirstName()), safe(e.getMiddleName()), safe(e.getLastName()),
				safe(e.getSecondLastName())).trim();
	}

	private static String safe(String value) {
		return value == null ? "" : value;
	}
}