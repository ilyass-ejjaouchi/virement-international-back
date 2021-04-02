package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import virement.international.entities.Banque;
import virement.international.entities.Compte;
import virement.international.repositories.BanqueRepository;
import virement.international.repositories.CompteRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BanqueController {
    @Autowired
    public BanqueRepository banqueRepo;

    @GetMapping("/banques")
    public List<Banque> getComptes() {
        return banqueRepo.findAll();
    }
    @PostMapping("/banques")
    public void addBanque(
            @RequestParam  String nom,
            @RequestParam  String BIC
    ) {
       Banque b = new Banque(nom,BIC);
       banqueRepo.save(b);
    }
}
