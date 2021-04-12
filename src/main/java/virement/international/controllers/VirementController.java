package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import virement.international.entities.Virement;
import virement.international.repositories.VirementRepository;
import virement.international.services.VirementService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class VirementController {
    @Autowired public VirementRepository virementRepo;
    @Autowired public VirementService virementService;

    @GetMapping("/virements")
    public List<Virement> getVirements() {
        return virementRepo.findAll();
    }

    @PostMapping("/virements")
    public void addVirement(
        @RequestParam(name = "typeVirement", required = false)  String type,
        @RequestParam(required=false, name = "date") String dateExecution,
        @RequestParam String devise,
        @RequestParam Long montant,
        @RequestParam Long contreValeur,
        @RequestParam String motif,
        @RequestParam(name = "compteDebite")  Long idCompteDebiter,
        @RequestParam(name = "compteCredite")  Long idCompteCrediter,
        @RequestParam(required=false)  String instructionClient,
        @RequestParam(required=false) String modeImputation,
        @RequestParam(required=false) String retenue
    ) {
        virementService.creeVirement(type,dateExecution,devise,montant,contreValeur,motif,instructionClient, modeImputation,retenue,idCompteCrediter,idCompteDebiter);
    }
}
