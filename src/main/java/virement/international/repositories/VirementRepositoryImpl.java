package virement.international.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import virement.international.entities.Etat;
import virement.international.entities.Virement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VirementRepositoryImpl implements VirementRepoCustom  {
    @PersistenceContext
    EntityManager em;

    @Override
    public Page<Virement> findVirementByMultiCritere(Etat etat, Long montantMax, Long montantMin, LocalDate dateMin, LocalDate dateMax, Pageable page) {
        CriteriaBuilder cv = em.getCriteriaBuilder();
        CriteriaQuery<Virement> cq = cv.createQuery(Virement.class);
        Root<Virement> virement = cq.from(Virement.class);
        List<Predicate> predicates = new ArrayList<>();
        if (etat != null) {
            predicates.add(cv.equal(virement.get("etat"), etat));
        }
        if (montantMax != null) {
            predicates.add(cv.greaterThan(virement.get("montant"), montantMax));
        }
        if (montantMin != null) {
            predicates.add(cv.lessThan(virement.get("montant"), montantMin));
        }
        if (dateMin != null) {
            predicates.add(cv.greaterThan(virement.get("dateExecution"), dateMin));
        }
        if (dateMax != null) {
            predicates.add(cv.lessThan(virement.get("dateExecution"), dateMax));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Virement> query = em.createQuery(cq);
        int totalRows = query.getResultList().size();
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        Page<Virement> result = new PageImpl<>(query.getResultList(), page, totalRows);

        return result;
    }
}
