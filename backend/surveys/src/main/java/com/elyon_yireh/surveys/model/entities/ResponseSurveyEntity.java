package com.elyon_yireh.surveys.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_responses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter

public class ResponseSurveyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "respondent_id", nullable = false)
    private RespondentEntity respondentEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity surveyEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity questionEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id", nullable = false)
    private AnswerEntity answerEntity;
}
