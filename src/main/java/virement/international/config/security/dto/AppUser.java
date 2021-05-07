package virement.international.config.security.dto;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import virement.international.entities.Beneficiare;
import virement.international.entities.Client;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String identifiant;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();

	public void addRole(AppRole approle) {
		if (this.roles == null) {
			this.roles = new ArrayList<AppRole>();
			this.roles.add(approle);
		}
		this.roles.add(approle);
	}

	@OneToOne
	private Client client;
	@OneToOne
	private Beneficiare beneficiare;
}
