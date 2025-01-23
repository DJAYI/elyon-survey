package com.elyon_yireh.surveys.domain.dao.mysql;

import com.elyon_yireh.surveys.domain.entities.SurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyDao extends CrudRepository<SurveyEntity, UUID> {
}
