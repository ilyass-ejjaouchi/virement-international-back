package virement.international.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Beneficiare;

@Repository
public interface BeneficiareRepository extends JpaRepository<Beneficiare, Long> {
    Page<Beneficiare> findAllBy(Pageable pageable);
}
