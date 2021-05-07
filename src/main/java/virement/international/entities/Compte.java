package virement.international.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data @Getter @Setter @NoArgsConstructor
@Entity
public class Compte {
    @Id
    private Long numeroCompte;
    private Long solde;
    @Column(unique = true)
    private String IBAN;
    private String devise;
    private String routing;

    public Compte(Long numeroCompte, Long solde, String IBAN, String devise) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.IBAN = IBAN;
        this.devise = devise;
    }

    @ManyToOne @JsonIgnore
    private Client client;
    @ManyToOne @JsonIgnore
    private Beneficiare beneficiare;
    @ManyToOne
    private Banque banque;

}
