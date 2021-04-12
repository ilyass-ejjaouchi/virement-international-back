package virement.international.entities;


import lombok.*;

import javax.persistence.*;

@Data @Getter @Setter @NoArgsConstructor
@Entity
public class Compte {
    @Id
    private Long numeroCompte;
    private Long solde;
    private String IBAN;
    private String devise;
    private String routing;

    public Compte(Long numeroCompte, Long solde, String IBAN, String devise) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.IBAN = IBAN;
        this.devise = devise;
    }

    @ManyToOne
    private Client client;
    @ManyToOne
    private Beneficiare beneficiare;
    @ManyToOne
    private Banque banque;

}
