package virement.international.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import virement.international.entities.Compte;
import virement.international.entities.Etat;
import virement.international.entities.Virement;

import java.time.LocalDate;

public interface VirementRepoCustom  {
    Page<Virement> findVirementByMultiCritere(Etat etat, Long montantMax, Long montantMin, LocalDate dateMin, LocalDate dateMax, Long numeroCompte, Pageable pageable);
}
