package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Banque;

@Repository
public interface BanqueRepository extends JpaRepository<Banque, Long> {

}
