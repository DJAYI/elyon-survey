package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
