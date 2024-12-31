package com.elyon_yireh.surveys.repository.mysql;

import com.elyon_yireh.surveys.model.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
