package virement.international.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virement.international.entities.Compte;
import virement.international.entities.Virement;
import virement.international.exceptions.BadRequestException;
import virement.international.repositories.BeneficiareRepository;
import virement.international.repositories.ClientRepository;
import virement.international.repositories.CompteRepository;
import virement.international.repositories.VirementRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class VirementService {
    @Autowired VirementRepository virementRepo;
    @Autowired CompteRepository compteRepo;

    public void creeVirement(String type, String dateExecution, String devise,
                             long montant, long contreValeur, String motif,
                             String instructionClient, String modeImputation,
                             String retenue, long idCompteDebiter, long idCompteCrediter){
        Compte compteDebiter = compteRepo.findById(idCompteDebiter).get();
        Compte compteCrediter = compteRepo.findById(idCompteCrediter).get();
        Virement v = new Virement(type,LocalDate.parse(dateExecution),devise,montant,contreValeur,motif,instructionClient,modeImputation,retenue);
        v.setBeneficiare(compteCrediter.getBeneficiare());
        v.setClient(compteCrediter.getClient());

       if (compteDebiter.getSolde() > montant){
           compteDebiter.setSolde(compteDebiter.getSolde() - montant);
           compteCrediter.setSolde(compteCrediter.getSolde() + montant);
           virementRepo.save(v);
       }else{
           throw new BadRequestException("solde insuffisant");
       }
    }
}
