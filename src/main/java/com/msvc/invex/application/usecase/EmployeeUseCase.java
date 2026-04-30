
package com.msvc.invex.application.usecase;

import com.msvc.invex.domain.model.Employee;
import java.util.List;

public interface EmployeeUseCase {
	
	List<Employee> getAll();

	Employee getById(Long id);

	List<Employee> create(List<Employee> employees);

	Employee update(Long id, Employee employee);

	void delete(Long id);

	List<Employee> search(String name);
}
