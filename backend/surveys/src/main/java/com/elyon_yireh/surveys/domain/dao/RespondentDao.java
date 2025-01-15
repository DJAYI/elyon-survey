package com.elyon_yireh.surveys.domain.dao;

import com.elyon_yireh.surveys.domain.entities.RespondentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RespondentDao extends CrudRepository<RespondentEntity, UUID> {

}
