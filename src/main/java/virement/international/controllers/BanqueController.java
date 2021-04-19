package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import virement.international.entities.Banque;
import virement.international.entities.Compte;
import virement.international.exceptions.NotFoundException;
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
    @GetMapping("/banques/{id}")
    public Banque getCompte(@PathVariable long id) {
       if (banqueRepo.findById(id).isPresent()) return banqueRepo.findById(id).get();
       else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la banque n'existe pas");
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
