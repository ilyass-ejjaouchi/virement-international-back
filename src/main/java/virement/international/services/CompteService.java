package virement.international.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virement.international.entities.Compte;
import virement.international.repositories.CompteRepository;

import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepo;

    //@Transactional
    public void virement(Long c1, Long c2, long montant){
       Compte compteDebite = compteRepo.findById(c1).get();
       Compte compteCrediter = compteRepo.findById(c2).get();
       if (compteDebite.getSolde() > montant){
           compteDebite.setSolde(compteDebite.getSolde() - montant);
           compteCrediter.setSolde(compteCrediter.getSolde() + montant);
       }
    }
    public Set<Currency> getAllCurrencies(){
        Set<Currency> toret = new HashSet<>();
        Locale[] locs = Locale.getAvailableLocales();

        for(Locale loc : locs) {
            try {
                Currency currency = Currency.getInstance( loc );
                if ( currency != null ) {
                    toret.add( currency );
                }
            } catch(Exception exc)
            { }
        }
        return toret;
    }
}
