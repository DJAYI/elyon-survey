package com.elyon_yireh.surveys.domain.dao.mysql;

import com.elyon_yireh.surveys.domain.entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends CrudRepository<QuestionEntity, Long> {
}
