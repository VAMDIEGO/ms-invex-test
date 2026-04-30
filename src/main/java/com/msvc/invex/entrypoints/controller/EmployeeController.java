package com.msvc.invex.entrypoints.controller;

import com.msvc.invex.application.usecase.EmployeeUseCase;
import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.entrypoints.dto.EmployeeRequest;
import com.msvc.invex.entrypoints.dto.EmployeeResponse;
import com.msvc.invex.entrypoints.mapper.EmployeeMapper;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeUseCase service;

    public EmployeeController(EmployeeUseCase service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        List<EmployeeResponse> response = service.getAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        EmployeeResponse response = EmployeeMapper.toResponse(service.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<EmployeeResponse>> create(
            @Valid @RequestBody List<EmployeeRequest> request) {

        List<Employee> employees = request.stream()
                .map(EmployeeMapper::toDomain)
                .toList();

        List<EmployeeResponse> response = service.create(employees)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = EmployeeMapper.toDomain(request);

        EmployeeResponse response = EmployeeMapper.toResponse(
                service.update(id, employee)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> search(@RequestParam String name) {

        List<EmployeeResponse> response = service.search(name)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}