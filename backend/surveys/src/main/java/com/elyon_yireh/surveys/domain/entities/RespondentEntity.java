package com.elyon_yireh.surveys.domain.entities;

import com.elyon_yireh.surveys.utils.DocumentEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_respondents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RespondentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "resp_nombre")
    String name;

    @Column(name = "resp_apellido")
    String apellido;

    @Column(name = "resp_doc_tipo")
    @Enumerated(EnumType.STRING)
    DocumentEnum documentType;

    @Column(name = "resp_doc")
    String documentNumber;

    @Column(name = "resp_telefono")
    String phone;

    @Column(name = "resp_correo")
    String email;

    @Column(name = "resp_estudiente")
    Boolean student;
}
