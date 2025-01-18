package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveDepartmentDto;
import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;
import com.elyon_yireh.surveys.service.implementations.department.DepartmentManagementImpl;
import com.elyon_yireh.surveys.service.implementations.department.DepartmentQueryImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@PreAuthorize("denyAll()")
public class DepartmentController {
    @Autowired
    private DepartmentManagementImpl departmentManagement;

    @Autowired
    private DepartmentQueryImpl departmentQuery;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public HttpResponseListDto<DepartmentEntity> getDepartments() {
        return departmentQuery.getDepartments();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public HttpResponseDto<DepartmentEntity> getDepartment(@Valid @PathVariable Long id) {
        return departmentQuery.getDepartment(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public HttpResponseDto<DepartmentEntity> createDepartment(@Valid @RequestBody SaveDepartmentDto saveDepartmentDto) {
        return departmentManagement.createDepartment(saveDepartmentDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public HttpResponseDto<DepartmentEntity> updateDepartment(@Valid @RequestBody SaveDepartmentDto saveDepartmentDto, @Valid @PathVariable Long id) {
        return departmentManagement.updateDepartment(saveDepartmentDto, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpResponseDto<DepartmentEntity> deleteDepartment(@Valid @PathVariable Long id) {
        return departmentManagement.deleteDepartment(id);
    }
}
