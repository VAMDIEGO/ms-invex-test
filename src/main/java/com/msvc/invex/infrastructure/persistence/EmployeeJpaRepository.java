
package com.msvc.invex.infrastructure.persistence;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {
	 List<EmployeeEntity> findByFirstNameContainingIgnoreCase(String name);
}
