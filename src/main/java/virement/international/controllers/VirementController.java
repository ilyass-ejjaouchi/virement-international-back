package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import virement.international.entities.Etat;
import virement.international.entities.Virement;
import virement.international.repositories.ClientRepository;
import virement.international.repositories.VirementRepository;
import virement.international.services.VirementService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class VirementController {
    @Autowired public VirementRepository virementRepo;
    @Autowired public VirementService virementService;
    @Autowired public ClientRepository clientRepo;

    @GetMapping("/virements")
    public Page<Virement> getVirements(
            @RequestParam(name = "etat", required = false)  String etat,
            @RequestParam(name = "montantMin", required = false)  Long montantMax,
            @RequestParam(name = "montantMax", required = false)  Long montantMin,
            @RequestParam(name = "dateMin", required = false)  String dateMin,
            @RequestParam(name = "dateMax", required = false) String dateMax,
            @RequestParam(name = "numeroCompte", required = false) Long numeroCompte,
            @RequestParam(name = "page") int p,
            @RequestParam(name = "size") int size
    ) {
        LocalDate dateDebut = null, dateFin = null;
        if (dateMin != null) dateDebut = LocalDate.parse(dateMin);
        if (dateMax != null) dateFin = LocalDate.parse(dateMax);
        Pageable page = PageRequest.of(p, size);
        return virementRepo.findVirementByMultiCritere(Etat.fromEtat(etat), montantMax, montantMin, dateDebut, dateFin, numeroCompte,page);

    }
    @PostMapping("/virements")
    public Virement addVirement(
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
        @RequestParam(required=false) String retenue,
        @RequestParam Etat etat,
        @RequestParam (required=false) Long id
    ) {
        return virementService.creeVirement(id,type, LocalDate.parse(dateExecution),devise,montant,contreValeur,motif,instructionClient, modeImputation,false ,retenue,etat,idCompteDebiter,idCompteCrediter);
    }

    @PutMapping("/virements/{idVirement}")
    public Virement updateVirement(
            @PathVariable Long idVirement,
            @RequestParam String etatVirement) {
        Virement virement = virementRepo.findById(idVirement).get();
        virement.setEtat(Etat.fromEtat(etatVirement));
        return virementRepo.save(virement);
    }

    @PostMapping(path = "/deleteVirements/{id}")
    public Page<Virement> deleteVirement(@PathVariable Long id) {
        Virement v = virementRepo.findById(id).get();
        switch (v.getEtat()){
            case ENREGISTRÉ:
            case EN_COURS_DE_SIGNATURE:
                v.setEtat(Etat.ABANDONNÉ); break;
            case SIGNÉ: v.setEtat(Etat.ANNULÉ); break;
            case EN_COURS_DE_TRAITEMENT: v.setEtat(Etat.NON_VALIDÉ);break;
            case ABANDONNÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LE VIREMENT, LE VIREMENT EST DEJA ABANDONNÉ");
            case ANNULÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LE VIREMENT, LE VIREMENT EST DEJA ANNULÉ");
            case TRAITÉ: throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "VOUS NE POUVEZ PAS SUPPRIMER LE VIREMENT, LE VIREMENT EST DEJA TRAITÉ");
        }
        virementRepo.save(v);
        return virementRepo.findAll(PageRequest.of(0, 5));
    }

    @GetMapping(path = "/virements/{id}")
    public Virement getVirement(@PathVariable Long id) {
        if (virementRepo.findById(id).isPresent()) return virementRepo.findById(id).get();
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"le virements n'existe pas");
    }
    @GetMapping(path = "/virementsByCriteria")
    public List<Virement> getVirementsByCriteria(
            @RequestParam(name = "etat", required = false)  String etat
    ) {
       return virementRepo.getViremtsByCriteria(Etat.fromEtat(etat));

    }
}
