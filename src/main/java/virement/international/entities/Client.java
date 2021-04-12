package virement.international.entities;



import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Client {
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

}
