package com.elyon_yireh.surveys.service.interfaces.department;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveDepartmentDto;
import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;

public interface DepartmentManagementService {
    public HttpResponseDto<DepartmentEntity> createDepartment(SaveDepartmentDto saveDepartmentDto);

    public HttpResponseDto<DepartmentEntity> updateDepartment(SaveDepartmentDto saveDepartmentDto, Long departmentId);

    public HttpResponseDto<DepartmentEntity> deleteDepartment(Long departmentId);

}
