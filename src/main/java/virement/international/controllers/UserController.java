package virement.international.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import virement.international.config.security.Roles;
import virement.international.config.security.dto.AppRole;
import virement.international.config.security.dto.AppUser;
import virement.international.config.security.dto.RegistrationForm;
import virement.international.services.AccountService;

@org.springframework.web.bind.annotation.RestController
public class UserController {
	@Autowired
	private AccountService accountService;

@PostMapping("/users")
public AppUser signUp(@RequestBody RegistrationForm data) {
	return accountService.signUp(data);
	}

@PostMapping("/roles")
public AppRole addRole(@RequestBody AppRole  role) {
	return accountService.saveRole(role);
	}

@PreAuthorize("hasAuthority('ADMIN')")
@PostMapping("/userRoles")
public void associateUserWithRole(@RequestParam String username,@RequestParam Roles role) {
	accountService.addRoleToUser(username, role);
	}
}
