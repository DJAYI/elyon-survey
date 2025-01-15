package com.elyon_yireh.surveys.domain.entities;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_questions_answers", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
    Set<AnswerEntity> answerEntities;

}
