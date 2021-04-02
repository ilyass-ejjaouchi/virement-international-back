package virement.international.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String iban;
    private String pays;
    private String adresse1;
    private String adresse2;
    private String adresse3;
    private String routing;

    public Beneficiare(String nom, String nature, String type, String libelle, String devise, String iban, String pays, String adresse1, String adresse2, String adresse3, String routing) {
        this.nature = nature;
        this.type = type;
        this.libelle = libelle;
        this.devise = devise;
        this.iban = iban;
        this.pays = pays;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.adresse3 = adresse3;
        this.routing = routing;
    }
}
