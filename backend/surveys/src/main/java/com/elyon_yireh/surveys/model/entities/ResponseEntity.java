package com.elyon_yireh.surveys.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "tb_responses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter

public class ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<RespondentEntity> respondentEntity;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<AnswerEntity> answerEntities;

}
