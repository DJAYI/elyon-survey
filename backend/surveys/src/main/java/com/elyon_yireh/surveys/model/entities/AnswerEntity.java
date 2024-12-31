package com.elyon_yireh.surveys.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_answers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ans_enunciado")
    String statement;

    @Column(name = "ans_valor")
    Integer value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    QuestionEntity questionEntity;
}