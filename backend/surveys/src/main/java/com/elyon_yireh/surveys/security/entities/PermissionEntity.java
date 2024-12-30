package com.elyon_yireh.surveys.security.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_permissions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "per_nombre")
    String name;
}
