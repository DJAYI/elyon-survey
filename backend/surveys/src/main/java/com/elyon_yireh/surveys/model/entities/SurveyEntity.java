package com.elyon_yireh.surveys.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Table(name = "tb_surveys")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class SurveyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    DepartmentEntity departmentEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<QuestionEntity> questionEntities;
}
