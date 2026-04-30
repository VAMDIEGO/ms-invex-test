package com.msvc.invex.entrypoints.controller;

import com.msvc.invex.application.usecase.EmployeeUseCase;
import com.msvc.invex.domain.model.Employee;
import com.msvc.invex.entrypoints.dto.EmployeeRequest;
import com.msvc.invex.entrypoints.dto.EmployeeResponse;
import com.msvc.invex.entrypoints.mapper.EmployeeMapper;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Operaciones de gestión de empleados")
public class EmployeeController {

    private final EmployeeUseCase service;

    public EmployeeController(EmployeeUseCase service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los empleados")
    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida correctamente")
    public ResponseEntity<List<EmployeeResponse>> getAll() {

        List<EmployeeResponse> response = service.getAll()
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

        EmployeeResponse response = EmployeeMapper.toResponse(service.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Crear empleados (lista)")
    @ApiResponse(responseCode = "201", description = "Empleados creados correctamente")
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
    @Operation(summary = "Actualizar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EmployeeResponse> update(
            @Parameter(description = "ID del empleado", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = EmployeeMapper.toDomain(request);

        EmployeeResponse response = EmployeeMapper.toResponse(
                service.update(id, employee)
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

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar empleados por nombre")
    @ApiResponse(responseCode = "200", description = "Resultados de búsqueda")
    public ResponseEntity<List<EmployeeResponse>> search(
            @Parameter(description = "Nombre o parte del nombre", example = "Juan")
            @RequestParam String name) {

        List<EmployeeResponse> response = service.search(name)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}