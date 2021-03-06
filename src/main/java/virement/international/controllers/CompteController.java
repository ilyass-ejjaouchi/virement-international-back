package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import virement.international.config.security.dto.AppUser;
import virement.international.entities.Compte;
import virement.international.repositories.AppUserRepository;
import virement.international.repositories.CompteRepository;
import virement.international.services.CompteService;

import java.util.Currency;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class CompteController {

    @Autowired public CompteRepository compteRepo;
    @Autowired public CompteService compteService;
    @Autowired public AppUserRepository userRepository;

    @GetMapping("/currentUserComptes")
    public List<Compte> getCurrentUserComptes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userRepository.findByIdentifiant(authentication.getName());
        return compteRepo.findByClient(appUser.getClient());
    }
    @GetMapping("/comptes")
    public List<Compte> getComptes() {
        return compteRepo.findAll();
    }
    @PostMapping("/comptes")
    public void addCompte(
            @RequestParam  Long numeroCompte,
            @RequestParam  Long solde,
            @RequestParam  String IBAN,
            @RequestParam  String devise
    ) {
       Compte c = new Compte(numeroCompte,solde,IBAN,devise);
       compteRepo.save(c);
    }

    @GetMapping("comptes/{id}")
    public Compte getCompte(@PathVariable Long id) {
        return compteRepo.findById(id).get();
    }

    @GetMapping("comptes/currencies")
    public Set<Currency> getCurrencies() {
        return compteService.getAllCurrencies();
    }
}
