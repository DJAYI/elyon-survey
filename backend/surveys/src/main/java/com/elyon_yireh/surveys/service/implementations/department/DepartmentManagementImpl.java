package com.elyon_yireh.surveys.service.implementations.department;

import com.elyon_yireh.surveys.domain.dao.mysql.DepartmentDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveDepartmentDto;
import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;
import com.elyon_yireh.surveys.service.interfaces.department.DepartmentManagementService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentManagementImpl implements DepartmentManagementService {

    private DepartmentDao departmentDao;

    public DepartmentManagementImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public HttpResponseDto<DepartmentEntity> createDepartment(SaveDepartmentDto saveDepartmentDto) {
        if (saveDepartmentDto == null) {
            return new HttpResponseDto<>("Department details are required", "DEPARTMENT_DETAILS_REQUIRED", "error", null);
        }

        if (saveDepartmentDto.name() == null || saveDepartmentDto.name().isEmpty()) {
            return new HttpResponseDto<>("Department name is required", "DEPARTMENT_NAME_REQUIRED", "error", null);
        }

        if (saveDepartmentDto.code() == null || saveDepartmentDto.code().isEmpty()) {
            return new HttpResponseDto<>("Department code is required", "DEPARTMENT_CODE_REQUIRED", "error", null);
        }

        DepartmentEntity departmentEntity = DepartmentEntity.builder()
                .name(saveDepartmentDto.name())
                .code(saveDepartmentDto.code())
                .build();

        return new HttpResponseDto<>("Department created successfully", "DEPARTMENT_CREATED", "success", departmentDao.save(departmentEntity));
    }

    @Override
    public HttpResponseDto<DepartmentEntity> updateDepartment(SaveDepartmentDto saveDepartmentDto, Long departmentId) {
        if (saveDepartmentDto == null) {
            return new HttpResponseDto<>("Department details are required", "DEPARTMENT_DETAILS_REQUIRED", "error", null);
        }

        if (saveDepartmentDto.name() == null || saveDepartmentDto.name().isEmpty()) {
            return new HttpResponseDto<>("Department name is required", "DEPARTMENT_NAME_REQUIRED", "error", null);
        }

        if (saveDepartmentDto.code() == null || saveDepartmentDto.code().isEmpty()) {
            return new HttpResponseDto<>("Department code is required", "DEPARTMENT_CODE_REQUIRED", "error", null);
        }

        DepartmentEntity departmentEntity = departmentDao.findById(departmentId).orElse(null);

        if (departmentEntity == null) {
            return new HttpResponseDto<>("Department not found", "DEPARTMENT_NOT_FOUND", "error", null);
        }

        departmentEntity.setName(saveDepartmentDto.name());
        departmentEntity.setCode(saveDepartmentDto.code());

        return new HttpResponseDto<>("Department updated successfully", "DEPARTMENT_UPDATED", "success", departmentDao.save(departmentEntity));

    }

    @Override
    public HttpResponseDto<DepartmentEntity> deleteDepartment(Long departmentId) {
        if (departmentId == null) {
            return new HttpResponseDto<>("Department ID is required", "DEPARTMENT_ID_REQUIRED", "error", null);
        }

        DepartmentEntity departmentEntity = departmentDao.findById(departmentId).orElse(null);

        if (departmentEntity == null) {
            return new HttpResponseDto<>("Department not found", "DEPARTMENT_NOT_FOUND", "error", null);
        }

        departmentDao.delete(departmentEntity);

        return new HttpResponseDto<>("Department deleted successfully", "DEPARTMENT_DELETED", "success", null);
    }
}
