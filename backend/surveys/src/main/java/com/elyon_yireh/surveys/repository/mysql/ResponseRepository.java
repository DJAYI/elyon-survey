package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseEntity, Long> {
}
