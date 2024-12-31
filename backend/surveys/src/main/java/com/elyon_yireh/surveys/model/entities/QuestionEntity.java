package com.elyon_yireh.surveys.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "tb_questions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter

public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "quest_enunciado")
    String statement;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<AnswerEntity> answerEntities;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id")
    SurveyEntity surveyEntity;
}
