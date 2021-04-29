package virement.international.repositories;

import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import virement.international.entities.Etat;
import virement.international.entities.Virement;

import java.util.List;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long>,VirementRepoCustom {
    @Query("select v from Virement as v where  (v.etat = :etat or v.etat is null) ")
    List<Virement> getViremtsByCriteria(@Nullable @Param("etat") Etat etat);
}
