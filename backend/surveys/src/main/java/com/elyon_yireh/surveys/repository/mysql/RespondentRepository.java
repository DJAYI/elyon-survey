package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.RespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RespondentRepository extends JpaRepository<RespondentEntity, UUID> {
}
