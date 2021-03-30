package virement.international.entities;



import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @Data @Getter @Setter @NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceClient;
    private Long nom;

    @Enumerated(EnumType.STRING)
    private TypeClient type;

    private String civilite;
    private String adresse1;
    private String adresse2;
    private String adresse3;
}
