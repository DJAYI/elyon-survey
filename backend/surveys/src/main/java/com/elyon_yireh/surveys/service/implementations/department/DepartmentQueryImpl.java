package com.elyon_yireh.surveys.service.implementations.department;

import com.elyon_yireh.surveys.domain.dao.mysql.DepartmentDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;
import com.elyon_yireh.surveys.service.interfaces.department.DepartmentQueryService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentQueryImpl implements DepartmentQueryService {
    DepartmentDao departmentDao;

    public DepartmentQueryImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public HttpResponseDto<DepartmentEntity> getDepartment(Long departmentId) {
        if (departmentId == null) {
            return new HttpResponseDto<>("Department ID is required", "DEPARTMENT_ID_REQUIRED", "error", null);
        }

        return departmentDao.findById(departmentId)
                .map(departmentEntity -> new HttpResponseDto<>("Department retrieved", "DEPARTMENT_FOUND", "success", departmentEntity))
                .orElseGet(() -> new HttpResponseDto<>("Department not found", "DEPARTMENT_NOT_FOUND", "error", null));
    }

    @Override
    public HttpResponseListDto<DepartmentEntity> getDepartments() {
        return new HttpResponseListDto<>("Departments retrieved", "DEPARTMENTS_FOUND", "success", departmentDao.findAll());
    }
}
