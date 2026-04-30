
package com.msvc.invex.infrastructure.persistence;

import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.domain.port.EmployeeRepositoryPort;
import com.msvc.invex.entrypoints.mapper.EmployeeMapper;

import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.*;

@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

	 private final EmployeeJpaRepository jpa;

	    public EmployeeRepositoryAdapter(EmployeeJpaRepository jpa) {
	        this.jpa = jpa;
	    }

	    @Override
	    public List<Employee> findAll() {
	        return jpa.findAll()
	                .stream()
	                .map(EmployeeMapper::toDomain)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public Optional<Employee> findById(Long id) {
	        return jpa.findById(id)
	                .map(EmployeeMapper::toDomain);
	    }

	    @Override
	    public List<Employee> findByName(String name) {
	        return jpa.findByFirstNameContainingIgnoreCase(name)
	                .stream()
	                .map(EmployeeMapper::toDomain)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<Employee> saveAll(List<Employee> employees) {
	        List<EmployeeEntity> entities = employees.stream()
	                .map(EmployeeMapper::toEntity)
	                .collect(Collectors.toList());

	        return jpa.saveAll(entities)
	                .stream()
	                .map(EmployeeMapper::toDomain)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public Employee save(Employee employee) {
	        EmployeeEntity entity = EmployeeMapper.toEntity(employee);
	        EmployeeEntity saved = jpa.save(entity);
	        return EmployeeMapper.toDomain(saved);
	    }

	    @Override
	    public void deleteById(Long id) {
	        jpa.deleteById(id);
	    }
}
