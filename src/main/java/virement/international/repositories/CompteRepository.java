package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

}
