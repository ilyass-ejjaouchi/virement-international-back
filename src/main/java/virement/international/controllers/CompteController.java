package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import virement.international.entities.Compte;
import virement.international.repositories.CompteRepository;

import java.util.List;

@RestController
public class CompteController {
    @Autowired
    public CompteRepository compteRepo;

    @GetMapping("/comptes")
    public List<Compte> getComptes() {
        return compteRepo.findAll();
    }
}
