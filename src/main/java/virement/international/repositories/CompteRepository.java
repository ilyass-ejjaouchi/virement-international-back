package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Beneficiare;
import virement.international.entities.Client;
import virement.international.entities.Compte;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
public Beneficiare findByBeneficiare(Beneficiare b);
public List<Compte> findByClient(Client c);
public Compte findByIBAN(String iban);

}
