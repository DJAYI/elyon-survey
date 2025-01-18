package com.elyon_yireh.surveys.service.implementations.survey;

import com.elyon_yireh.surveys.domain.dao.SurveyDao;
import com.elyon_yireh.surveys.domain.dto.HttpResponseDto;
import com.elyon_yireh.surveys.domain.dto.HttpResponseListDto;
import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import com.elyon_yireh.surveys.service.interfaces.survey.SurveyQueryService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SurveyQueryImpl implements SurveyQueryService {
    private SurveyDao surveyDao;

    public SurveyQueryImpl(SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    @Override
    public HttpResponseDto<SurveyEntity> getSurvey(UUID surveyId) {
        if (surveyId == null) {
            return new HttpResponseDto<>("Invalid survey ID", "INVALID_ID", "error", null);
        }

        return surveyDao.findById(surveyId)
                .map(survey -> new HttpResponseDto<>("Survey retrieved successfully", "SURVEY_RETRIEVED", "success", survey))
                .orElseGet(() -> new HttpResponseDto<>("Survey not found", "SURVEY_NOT_FOUND", "error", null));
    }

    @Override
    public HttpResponseListDto<SurveyEntity> getSurveys() {
        return new HttpResponseListDto<SurveyEntity>("Surveys retrieved successfully", "SURVEYS_RETRIEVED", "success", surveyDao.findAll());
    }
}
