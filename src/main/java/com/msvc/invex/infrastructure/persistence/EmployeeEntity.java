package com.msvc.invex.infrastructure.persistence;

import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employees")
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}