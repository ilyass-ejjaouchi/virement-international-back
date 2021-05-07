package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import virement.international.entities.Beneficiare;
import virement.international.entities.Client;
import virement.international.entities.Compte;
import virement.international.entities.Etat;
import virement.international.exceptions.BadRequestException;
import virement.international.repositories.AppUserRepository;
import virement.international.repositories.BeneficiareRepository;
import virement.international.repositories.ClientRepository;
import virement.international.repositories.CompteRepository;

@RestController
@CrossOrigin(origins = "*")
public class BeneficiareController {
    @Autowired public BeneficiareRepository beneficiareRepo;
    @Autowired public CompteRepository compteRepo;
    @Autowired public AppUserRepository userRepo;
    @Autowired public ClientRepository clientRepo;

    @GetMapping("/beneficiares")
    public Page<Beneficiare> getBeneficiares(@RequestParam(name = "page") int p,
                                             @RequestParam(name = "size") int size) {
        Pageable page = PageRequest.of(p, size);
        return beneficiareRepo.findAll(page);
    }
    @PostMapping("/beneficiares")
    public Beneficiare addBeneficiare(
            @RequestParam String IBAN,
            @RequestParam(required = false) Long id,
            @RequestParam String nature,
            @RequestParam String type,
            @RequestParam String libelle,
            @RequestParam String devise,
            @RequestParam String pays,
            @RequestParam String adresse1,
            @RequestParam(required = false) String adresse2,
            @RequestParam(required = false) String adresse3,
            @RequestParam Etat etat
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Client client = userRepo.findByIdentifiant(authentication.getName()).getClient();
        Compte compte = compteRepo.findByIBAN(IBAN);
        if (compte == null){
            throw new BadRequestException("l'iban que vous avez saisi est invalid ou n'existe pas dans la base");
        }else if (compte.getClient() != null || compte.getBeneficiare() != null){
            throw new BadRequestException("le code iban que vous avez saisi appartient à un client, choisissez un autre");
        }
       Beneficiare benef = new Beneficiare(nature,type,devise,etat,libelle,pays,adresse1,adresse2,adresse3);
        benef.setId(id);
        benef.setCompte(compte);
        benef.setClient(client);
        benef.setEtat(Etat.ENREGISTRÉ);
       beneficiareRepo.save(benef);

       compte.setBeneficiare(benef);
       compteRepo.save(compte);

       return benef;
    }

    @PostMapping(path = "/deleteBeneficiares/{id}")
    public Page<Beneficiare> deletedemande(@PathVariable Long id) {
        Beneficiare b = beneficiareRepo.findById(id).get();
        switch (b.getEtat()){
            case ENREGISTRÉ:
            case EN_COURS_DE_SIGNATURE:
                b.setEtat(Etat.ABANDONNÉ); break;
            case SIGNÉ: b.setEtat(Etat.ANNULÉ); break;
            case ABANDONNÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LA DEMANDE, LA DEMANDE EST DEJA ABANDONNÉ");
            case ANNULÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LA DEMANDE, LA DEMANDE EST DEJA ANNULÉ");
        }
        beneficiareRepo.save(b);
        return beneficiareRepo.findAll(PageRequest.of(0,5));
    }

    @PostMapping("/signerBeneficiare/{id}")
    public Page<Beneficiare> signerDemande(
            @PathVariable Long id) {
        Beneficiare beneficiare = beneficiareRepo.findById(id).get();
        switch (beneficiare.getEtat()){
            case ABANDONNÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SIGNER LA DEMANDE, LA DEMANDE EST ABANDONNÉ");
            case ANNULÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ  PAS SIGNER LA DEMANDE, LA DEMANDE EST ANNULÉ");
        }
        beneficiare.setEtat(Etat.SIGNÉ);
        beneficiareRepo.save(beneficiare);
        return beneficiareRepo.findAll(PageRequest.of(0,5));
    }
}

