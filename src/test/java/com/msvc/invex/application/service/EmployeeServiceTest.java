package com.msvc.invex.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.domain.port.EmployeeRepositoryPort;
import com.msvc.invex.infrastructure.exceptions.EmployeeNotFoundException;

class EmployeeServiceTest {


    private EmployeeRepositoryPort repository;
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryPort.class);
        service = new EmployeeService(repository);
    }

    @Test
    void shouldReturnAllEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(repository.findAll()).thenReturn(employees);

        List<Employee> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> service.getById(1L));

        verify(repository).findById(1L);
    }

    @Test
    void shouldCreateEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(repository.saveAll(employees)).thenReturn(employees);

        List<Employee> result = service.create(employees);

        assertEquals(2, result.size());
        verify(repository).saveAll(employees);
    }

    @Test
    void shouldUpdateEmployee() {
        Employee existing = new Employee();
        existing.setId(1L);
        existing.setFirstName("Old");
        existing.setLastName("Name");

        Employee updated = new Employee();
        updated.setFirstName("New");
        updated.setLastName("Name");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Employee result = service.update(1L, updated);

        assertEquals("New", result.getFirstName());
        verify(repository).findById(1L);
        verify(repository).save(existing);
    }

    @Test
    void shouldDeleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void shouldSearchEmployeesByName() {
        List<Employee> employees = Arrays.asList(new Employee());
        when(repository.findByName("Diego")).thenReturn(employees);

        List<Employee> result = service.search("Diego");

        assertEquals(1, result.size());
        verify(repository).findByName("Diego");
    }
}
