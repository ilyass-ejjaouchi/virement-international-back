package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virement.international.config.security.dto.AppUser;


public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByIdentifiant(String identifiant);
}
