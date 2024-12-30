package com.elyon_yireh.surveys.security.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "tb_roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "rol_nombre")
    String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<PermissionEntity> permissionEntities;
}
