package com.elyon_yireh.surveys.domain.dao;


import com.elyon_yireh.surveys.domain.entities.ResponseSurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseSurveyDao extends CrudRepository<ResponseSurveyEntity, Long> {

}
