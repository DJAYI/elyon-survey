package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.ResponseSurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseSurveyEntity, Long> {
}
