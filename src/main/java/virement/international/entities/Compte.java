package virement.international.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCompte;
    private Long solde;
    private Long IBAN;
}
