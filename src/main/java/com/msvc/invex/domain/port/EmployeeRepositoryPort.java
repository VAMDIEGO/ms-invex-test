
package com.msvc.invex.domain.port;

import com.msvc.invex.domain.model.Employee;
import java.util.*;

public interface EmployeeRepositoryPort {
	
	List<Employee> findAll();

	Optional<Employee> findById(Long id);

	List<Employee> findByName(String name);

	List<Employee> saveAll(List<Employee> employees);

	Employee save(Employee employee);

	void deleteById(Long id);
}
