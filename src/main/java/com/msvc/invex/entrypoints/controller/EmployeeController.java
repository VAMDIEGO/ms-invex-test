package com.msvc.invex.entrypoints.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msvc.invex.application.usecase.EmployeeUseCase;
import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.entrypoints.dto.EmployeeRequest;
import com.msvc.invex.entrypoints.dto.EmployeeResponse;
import com.msvc.invex.entrypoints.mapper.EmployeeMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Operaciones de gestión de empleados")
@Validated
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    public EmployeeController(EmployeeUseCase service) {
        this.employeeUseCase = service;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los empleados")
    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida correctamente")
    public ResponseEntity<List<EmployeeResponse>> getAll() {

        List<EmployeeResponse> response = employeeUseCase.getAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EmployeeResponse> getById(
            @Parameter(description = "ID del empleado", example = "1")
            @PathVariable Long id) {

        EmployeeResponse response = EmployeeMapper.toResponse(employeeUseCase.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Crear empleados (lista)")
    @ApiResponse(responseCode = "201", description = "Empleados creados correctamente")
    public ResponseEntity<List<EmployeeResponse>> create(
            @Valid @RequestBody List<@Valid EmployeeRequest> request) {

        List<Employee> employees = request.stream()
                .map(EmployeeMapper::toDomain)
                .toList();

        List<EmployeeResponse> response = employeeUseCase.create(employees)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = EmployeeMapper.toDomain(request);

        EmployeeResponse response = EmployeeMapper.toResponse(
                employeeUseCase.update(id, employee)
        );

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado eliminado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del empleado", example = "1")
            @PathVariable Long id) {

        employeeUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar empleados por nombre")
    @ApiResponse(responseCode = "200", description = "Resultados de búsqueda")
    public ResponseEntity<List<EmployeeResponse>> search(
            @Parameter(description = "Nombre o parte del nombre", example = "Juan")
            @RequestParam String name) {

        List<EmployeeResponse> response = employeeUseCase.search(name)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
    
}

  