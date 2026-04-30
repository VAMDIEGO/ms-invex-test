
package com.msvc.invex.application.service;

import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.domain.port.EmployeeRepositoryPort;
import com.msvc.invex.infrastructure.exceptions.EmployeeNotFoundException;
import com.msvc.invex.application.usecase.EmployeeUseCase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService implements EmployeeUseCase {

	private final EmployeeRepositoryPort repository;

	public EmployeeService(EmployeeRepositoryPort repository) {
		this.repository = repository;
	}

	@Override
	public List<Employee> getAll() {
		return repository.findAll();
	}

	@Override
	public Employee getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@Override
	public List<Employee> create(List<Employee> employees) {
		return repository.saveAll(employees);
	}

	@Override
	public Employee update(Long id, Employee employee) {
		Employee existing = getById(id);
		existing.setFirstName(employee.getFirstName());
		existing.setLastName(employee.getLastName());
		return repository.save(existing);
	}

	@Override
	public void delete(Long id) {
		getById(id);
		repository.deleteById(id);
	}

	@Override
	public List<Employee> search(String name) {
		return repository.findByName(name);
	}
}
