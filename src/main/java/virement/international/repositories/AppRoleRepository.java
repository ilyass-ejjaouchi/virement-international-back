package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virement.international.config.security.Roles;
import virement.international.config.security.dto.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRole(Roles roleName);
}
