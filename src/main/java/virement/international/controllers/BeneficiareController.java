package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import virement.international.entities.Banque;
import virement.international.entities.Beneficiare;
import virement.international.repositories.BanqueRepository;
import virement.international.repositories.BeneficiareRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BeneficiareController {
    @Autowired
    public BeneficiareRepository beneficiareRepo;

    @GetMapping("/beneficiares")
    public List<Beneficiare> getBeneficiares() {
        return beneficiareRepo.findAll();
    }
    @PostMapping("/beneficiares")
    public void addBeneficiare(
            @RequestParam String nature,
            @RequestParam String type,
            @RequestParam String libelle,
            @RequestParam String pays,
            @RequestParam String adresse1,
            @RequestParam(required = false) String adresse2,
            @RequestParam(required = false) String adresse3
    ) {
       Beneficiare b = new Beneficiare(nature,type,libelle,pays,adresse1,adresse2,adresse3);
       beneficiareRepo.save(b);
    }
}

