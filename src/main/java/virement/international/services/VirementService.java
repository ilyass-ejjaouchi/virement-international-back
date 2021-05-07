package virement.international.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virement.international.entities.Compte;
import virement.international.entities.Etat;
import virement.international.entities.Virement;
import virement.international.exceptions.BadRequestException;
import virement.international.repositories.CompteRepository;
import virement.international.repositories.VirementRepository;

import java.time.LocalDate;

@Service
public class VirementService {
    @Autowired VirementRepository virementRepo;
    @Autowired CompteRepository compteRepo;

    public Virement creeVirement(Long id, String type, LocalDate dateExecution, String devise,
                                 long montant, long contreValeur, String motif, String instructionClient,
                                 String modeImputation,boolean justificatif, String retenue,
                                 Etat etat, long idCompteDebiter, long idCompteCrediter){

        Compte compteDebiter = compteRepo.findById(idCompteDebiter).get();
        Compte compteCrediter = compteRepo.findById(idCompteCrediter).get();
        Virement v = new Virement(type,dateExecution,devise,montant,contreValeur,motif,instructionClient,modeImputation,justificatif,retenue,etat);
        v.setCompteCredite(compteCrediter);
        v.setCompteDebite(compteDebiter);
        v.setId(id);

       if (compteDebiter.getSolde() > montant){
           return virementRepo.save(v);
       }else{
           throw new BadRequestException("solde insuffisant");
       }
    }
}
