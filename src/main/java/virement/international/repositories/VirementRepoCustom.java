package virement.international.repositories;

import virement.international.entities.Etat;
import virement.international.entities.Virement;

import java.time.LocalDate;
import java.util.List;

public interface VirementRepoCustom  {
    List<Virement> findVirementByMultiCritere(Etat etat, Long montantMax, Long montantMin, LocalDate dateMin, LocalDate dateMax);
}
