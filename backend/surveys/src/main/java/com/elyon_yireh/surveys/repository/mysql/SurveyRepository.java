package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SurveyRepository extends JpaRepository<SurveyEntity, UUID> {
}
