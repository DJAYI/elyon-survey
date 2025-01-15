package com.elyon_yireh.surveys.service.interfaces.department;

import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveDepartmentDto;

public interface DepartmentManagementService {
    public HttpResponseDto createDepartment(SaveDepartmentDto saveDepartmentDto);

    public HttpResponseDto updateDepartment(SaveDepartmentDto saveDepartmentDto);

    public HttpResponseDto deleteDepartment(Long departmentId);

}
