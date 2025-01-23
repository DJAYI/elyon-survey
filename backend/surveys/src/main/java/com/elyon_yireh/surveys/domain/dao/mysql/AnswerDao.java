package com.elyon_yireh.surveys.domain.dao.mysql;

import com.elyon_yireh.surveys.domain.entities.AnswerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends CrudRepository<AnswerEntity, Long> {
}
