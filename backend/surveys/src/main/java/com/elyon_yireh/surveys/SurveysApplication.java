package com.elyon_yireh.surveys;

import com.elyon_yireh.surveys.security.entities.PermissionEntity;
import com.elyon_yireh.surveys.security.entities.RoleEntity;
import com.elyon_yireh.surveys.security.entities.UserEntity;
import com.elyon_yireh.surveys.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SurveysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveysApplication.class, args);
	}

	@Bean
	CommandLineRunner init (UserRepository userRepository) {
		return args -> {

			PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
			PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();
			PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();
			PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();

			RoleEntity roleAdministrator = RoleEntity.builder().name("ADMIN").permissionEntities(Set.of(createPermission, readPermission, updatePermission, deletePermission)).build();
			RoleEntity roleAuditor = RoleEntity.builder().name("AUDIT").permissionEntities(Set.of(readPermission)).build();

			UserEntity userAdmin = UserEntity.builder()
					.username("admin")
					.password("$2a$12$2t645uzqUm10hcfPb3VUkeiysvMpeNyzv4FMSmsXqUeZyvvMLUPzi")
					.isAccountNoExpired(true)
					.isAccountNoLocked(true)
					.isEnabled(true)
					.isCredentialNoExpired(true)
					.roleEntities(Set.of(roleAdministrator))
					.build();

			UserEntity userAuditor = UserEntity.builder()
					.username("auditor")
					.password("$2a$12$CznnFZaiLug1ZnAt3N7ns.kfh1oLrwGAA96RwzVAoky8TPTZ0LGta")
					.isAccountNoExpired(true)
					.isAccountNoLocked(true)
					.isEnabled(true)
					.isCredentialNoExpired(true)
					.roleEntities(Set.of(roleAuditor))
					.build();

			userRepository.saveAll(List.of(userAdmin, userAuditor));
		};
	}
}