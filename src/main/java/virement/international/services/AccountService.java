package virement.international.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import virement.international.config.security.Roles;
import virement.international.config.security.dto.AppRole;
import virement.international.config.security.dto.AppUser;
import virement.international.config.security.dto.RegistrationForm;
import virement.international.exceptions.BadRequestException;
import virement.international.repositories.AppRoleRepository;
import virement.international.repositories.AppUserRepository;


@Service
@Transactional
public class AccountService {
	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private AppRoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public AppUser saveUser(AppUser u) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		return userRepository.save(u);
	}

	public AppRole saveRole(AppRole r) {
		AppRole role =roleRepository.findByRole(r.getRole());
		if (role == null ) {
			return roleRepository.save(r);
		}
		else {
			throw new BadRequestException("le role existe déja");
		}
	}

	public AppUser findUserByUsername(String identifiant) {
		return userRepository.findByIdentifiant(identifiant);
	}

	public void addRoleToUser(String identifiant, Roles r) {
		AppUser user=userRepository.findByIdentifiant(identifiant);
		AppRole role =roleRepository.findByRole(r);
		user.addRole(role);
		userRepository.save(user);
		}

	public AppUser signUp(RegistrationForm data) {
		String identifiant = data.getIdentifiant();
		AppUser user = findUserByUsername(identifiant);
		if(user!=null) throw new BadRequestException("This user already exists, Try with an other username");
			String password = data.getPassword();
			String repassword = data.getRepassword();
		if(!password.equals(repassword))
			throw new RuntimeException("You must confirm your password");
			AppUser u = new AppUser();
			u.setIdentifiant(identifiant);
			u.setPassword(password);
			saveUser(u);
			/*if (data.getRole().equals(Role.AUTEUR)) {
				Auteur a = new Auteur(data.getUsername(),data.getNom(), data.getPrenom(), data.getAffiliation());
				auteurRepo.save(a);
				addRoleToUser(username, Roles.AUTEUR);
			}*/
			addRoleToUser(identifiant, Roles.ABONNÉ);
			return (u);
	}

}
