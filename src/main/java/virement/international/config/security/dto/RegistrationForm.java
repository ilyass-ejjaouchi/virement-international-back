package virement.international.config.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import virement.international.config.security.Roles;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegistrationForm {

	private String identifiant;
	private String password;
	private String repassword;

}
