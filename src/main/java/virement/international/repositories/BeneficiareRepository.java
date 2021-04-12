package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Beneficiare;

@Repository
public interface BeneficiareRepository extends JpaRepository<Beneficiare, Long> {
}
