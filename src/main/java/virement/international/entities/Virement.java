package virement.international.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private LocalDate dateExecution;
    private String devise;
    private Long montant;
    private Long contreValeur;
    private String motif;
    private String instructionCient;
    private String modeImputation;
    private String retenue;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Beneficiare beneficiare;

    public Virement(String type, LocalDate dateExecution, String devise, long montant, long contreValeur, String motif, String instructionCient, String modeImputation, String retenue) {
    this.type = type;
    this.dateExecution = dateExecution;
    this.devise = devise;
    this.montant = montant;
    this.contreValeur = contreValeur;
    this.motif = motif;
    this.instructionCient = instructionCient;
    this.modeImputation = modeImputation;
    this.retenue = retenue;
    }
}
