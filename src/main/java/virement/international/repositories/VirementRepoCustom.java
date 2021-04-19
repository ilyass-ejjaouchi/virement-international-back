package virement.international.repositories;

import virement.international.entities.EtatVirement;
import virement.international.entities.Virement;

import java.time.LocalDate;
import java.util.List;

public interface VirementRepoCustom  {
    List<Virement> findVirementByMultiCritere(EtatVirement etat, Long montantMax, Long montantMin,LocalDate dateMin, LocalDate dateMax);
}
