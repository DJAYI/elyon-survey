package com.elyon_yireh.surveys.domain.dao.mysql;

import com.elyon_yireh.surveys.domain.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends CrudRepository<DepartmentEntity, Long> {

}
