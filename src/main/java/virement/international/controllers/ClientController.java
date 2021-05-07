package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import virement.international.config.security.dto.AppUser;
import virement.international.entities.Banque;
import virement.international.entities.Client;
import virement.international.entities.TypeClient;
import virement.international.repositories.AppUserRepository;
import virement.international.repositories.BanqueRepository;
import virement.international.repositories.ClientRepository;
import virement.international.repositories.CompteRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired public ClientRepository clientRepo;
    @Autowired public AppUserRepository userRepository;

    @GetMapping("/currentClient")
    public Client getClients() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userRepository.findByIdentifiant(authentication.getName());
        return appUser.getClient();
    }

    @PostMapping("/clients")
    public void addClient(
            @RequestParam  String referenceClient,
            @RequestParam  String nom,
            @RequestParam  String prenom,
            @RequestParam TypeClient type,
            @RequestParam  String pays,
            @RequestParam  String adresse1,
            @RequestParam  String adresse2,
            @RequestParam  String adresse3
    ) {
      /* Client c = new Client(referenceClient,nom,prenom,type,pays,adresse1,adresse2,adresse3);
       clientRepo.save(c);*/
    }
}
