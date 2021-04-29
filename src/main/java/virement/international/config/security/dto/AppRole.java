package virement.international.config.security.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import virement.international.config.security.Roles;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
	public class AppRole {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Enumerated(EnumType.STRING)
		private Roles role;
	}
