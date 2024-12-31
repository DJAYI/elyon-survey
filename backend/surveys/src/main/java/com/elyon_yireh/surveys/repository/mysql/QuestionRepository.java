package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
