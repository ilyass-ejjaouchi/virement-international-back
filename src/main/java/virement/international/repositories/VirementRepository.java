package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Virement;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {

}
