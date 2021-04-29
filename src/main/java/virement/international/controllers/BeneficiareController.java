package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import virement.international.entities.Beneficiare;
import virement.international.entities.Compte;
import virement.international.entities.Etat;
import virement.international.entities.Virement;
import virement.international.repositories.BeneficiareRepository;
import virement.international.repositories.CompteRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BeneficiareController {
    @Autowired public BeneficiareRepository beneficiareRepo;
    @Autowired public CompteRepository compteRepo;

    @GetMapping("/beneficiares")
    public Page<Beneficiare> getBeneficiares(@RequestParam(name = "page") int p,
                                             @RequestParam(name = "size") int size) {
        Pageable page = PageRequest.of(p, size);
        return beneficiareRepo.findAll(page);
    }
    @PostMapping("/beneficiares")
    public Beneficiare addBeneficiare(
            @RequestParam Long numeroDeCompte,
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
        Compte c;
        if (compteRepo.findById(numeroDeCompte).isPresent()){
            c = compteRepo.findById(numeroDeCompte).get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "l'iban que vous avez saisi est invalid ou n'existe pas dans la base");
        /*if (c.getBeneficiare() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "le compte que vous avez choisi a deja un beneficiare");*/
       Beneficiare b = new Beneficiare(nature,type,devise,etat,libelle,pays,adresse1,adresse2,adresse3);
       b.setId(id);
       b.setCompte(c);
       beneficiareRepo.save(b);
       c.setBeneficiare(b);
       compteRepo.save(c);
        return b;
    }

    @DeleteMapping(path = "/beneficiares/{id}")
    public List<Beneficiare> deletedemande(@PathVariable Long id) {
        Beneficiare b = beneficiareRepo.findById(id).get();
        switch (b.getEtat()){
            case ENREGISTRÉ:
            case EN_COURS_DE_SIGNATURE:
                b.setEtat(Etat.ABANDONNÉ); break;
            case SIGNÉ: b.setEtat(Etat.ANNULÉ); break;
            case ABANDONNÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LE VIREMENT, LE VIREMENT EST DEJA ABANDONNÉ");
            case ANNULÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LE VIREMENT, LE VIREMENT EST DEJA ANNULÉ");
        }
        beneficiareRepo.save(b);
        return beneficiareRepo.findAll();
    }

    @PutMapping("/beneficiares/{id}")
    public Beneficiare updateVirement(
            @PathVariable Long id,
            @RequestParam String etat) {
        Beneficiare beneficiare = beneficiareRepo.findById(id).get();
        beneficiare.setEtat(Etat.fromEtat(etat));
        return beneficiareRepo.save(beneficiare);
    }
}

