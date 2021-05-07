package virement.international.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import virement.international.config.security.dto.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Client{
    @Id
    private String referenceClient;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private TypeClient type;
    private String pays;
    private String adresse1;
    private String adresse2;
    private String adresse3;

    @OneToMany
    private List<Compte> comptes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.EAGER)
    private List<Beneficiare> beneficiares;

    public void addBeneficiare(Beneficiare beneficiare){
        if (beneficiares == null)
            beneficiares = new ArrayList<>();
        beneficiares.add(beneficiare);
    }
    public void addCompte(Compte compte){
        if (comptes == null)
            comptes = new ArrayList<>();
        comptes.add(compte);
    }
}
