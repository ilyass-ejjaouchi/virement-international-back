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

    public Compte(Long numeroCompte, Long solde, String IBAN) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.IBAN = IBAN;
    }

    @ManyToOne
    private Client client;
    @ManyToOne
    private Banque banque;

}
