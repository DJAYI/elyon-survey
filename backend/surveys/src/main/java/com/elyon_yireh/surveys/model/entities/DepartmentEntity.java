package com.elyon_yireh.surveys.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_departments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "dep_codigo")
    String code;

    @Column(name = "dep_name")
    String name;
}
