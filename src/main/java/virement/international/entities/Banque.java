package virement.international.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Banque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String BIC;

    public Banque(String nom, String bic) {
         this.nom = nom;
         this.BIC = bic;
    }
}
