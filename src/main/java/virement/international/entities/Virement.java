package virement.international.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Date dateExecution;
    private String devise;
    private Long montant;
    private Long contreValeur;
    private String motif;
    private Long refClient;
    private String instructionCient;
    private String modeImputation;
    private String retenue;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Beneficiare beneficiare;

}
