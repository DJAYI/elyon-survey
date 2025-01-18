package com.elyon_yireh.surveys.service.interfaces.department;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;

public interface DepartmentQueryService {
    public HttpResponseDto<DepartmentEntity> getDepartment(Long departmentId);

    public HttpResponseListDto<DepartmentEntity> getDepartments();
}
