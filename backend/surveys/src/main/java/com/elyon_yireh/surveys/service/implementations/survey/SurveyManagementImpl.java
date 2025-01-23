package com.elyon_yireh.surveys.service.implementations.survey;

import com.elyon_yireh.surveys.domain.dao.mysql.DepartmentDao;
import com.elyon_yireh.surveys.domain.dao.mysql.SurveyDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.request.SaveSurveyDto;
import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.interfaces.survey.SurveyManagementService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SurveyManagementImpl implements SurveyManagementService {
    private SurveyDao surveyDao;
    private DepartmentDao departmentDao;

    public SurveyManagementImpl(SurveyDao surveyDao, DepartmentDao departmentDao) {
        this.surveyDao = surveyDao;
        this.departmentDao = departmentDao;
    }

    @Override
    public HttpResponseDto<SurveyEntity> createSurvey(SaveSurveyDto saveSurveyDto) {

        if (saveSurveyDto.departmentId() == null) {
            return new HttpResponseDto<>("Department ID is required", "DEPARTMENT_ID_REQUIRED", "error", null);
        }

        DepartmentEntity departmentEntity = departmentDao.findById(saveSurveyDto.departmentId()).orElse(null);

        if (departmentEntity == null) {
            return new HttpResponseDto<>("Department not found", "DEPARTMENT_NOT_FOUND", "error", null);
        }

        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setDepartmentEntity(departmentEntity);


        return new HttpResponseDto<>("Survey created successfully", "SURVEY_CREATED", "success", surveyDao.save(surveyEntity));
    }

    @Override
    public HttpResponseDto<SurveyEntity> updateSurvey(SaveSurveyDto saveSurveyDto, UUID surveyId) {
        if (saveSurveyDto.departmentId() == null) {
            return new HttpResponseDto<>("Department ID is required", "DEPARTMENT_ID_REQUIRED", "error", null);
        }

        DepartmentEntity departmentEntity = departmentDao.findById(saveSurveyDto.departmentId()).orElse(null);

        if (departmentEntity == null) {
            return new HttpResponseDto<>("Department not found", "DEPARTMENT_NOT_FOUND", "error", null);
        }

        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        surveyEntity.setDepartmentEntity(departmentEntity);

        return new HttpResponseDto<>("Survey updated successfully", "SURVEY_UPDATED", "success", surveyDao.save(surveyEntity));
    }

    @Override
    public HttpResponseDto<SurveyEntity> deleteSurvey(UUID surveyId) {
        SurveyEntity surveyEntity = surveyDao.findById(surveyId).orElse(null);

        if (surveyEntity == null) {
            return new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null);
        }

        surveyEntity.setDepartmentEntity(null);
        surveyDao.delete(surveyEntity);
        return new HttpResponseDto<>("Survey deleted successfully", "SURVEY_DELETED", "success", null);
    }
}
