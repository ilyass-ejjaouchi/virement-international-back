package virement.international.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Beneficiare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nature;
    private String type;
    private String libelle;
    private String devise;
    private String pays;
    private String adresse1;
    private String adresse2;
    private String adresse3;

    @OneToOne
    private Compte compte;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    public Beneficiare(String nature, String type,String devise, Etat etat, String libelle,String pays, String adresse1, String adresse2, String adresse3) {
        this.nature = nature;
        this.type = type;
        this.libelle = libelle;
        this.pays = pays;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.adresse3 = adresse3;
        this.devise = devise;
        this.etat = etat;
    }

}
