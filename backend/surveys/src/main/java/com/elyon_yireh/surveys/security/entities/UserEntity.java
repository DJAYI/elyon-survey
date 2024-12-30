package com.elyon_yireh.surveys.security.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "user_username")
    String username;

    @Column(name = "user_password")
    String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<RoleEntity> roleEntities;

    Boolean isEnabled;

    Boolean isAccountNoExpired;

    Boolean isCredentialNoExpired;

    Boolean isAccountNoLocked;
}
