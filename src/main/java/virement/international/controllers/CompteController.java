package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import virement.international.entities.Compte;
import virement.international.repositories.CompteRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CompteController {
    @Autowired
    public CompteRepository compteRepo;

    @GetMapping("/comptes")
    public List<Compte> getComptes() {
        return compteRepo.findAll();
    }
    @PostMapping("/comptes")
    public void addCompte(
            @RequestParam  Long numeroCompte,
            @RequestParam  Long solde,
            @RequestParam  String IBAN
    ) {
       Compte c = new Compte(numeroCompte,solde,IBAN);
       compteRepo.save(c);
    }
}
