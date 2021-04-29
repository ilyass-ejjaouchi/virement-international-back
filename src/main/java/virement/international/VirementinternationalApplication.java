package virement.international;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import virement.international.config.security.Roles;
import virement.international.config.security.dto.AppRole;
import virement.international.services.AccountService;

@SpringBootApplication
public class VirementinternationalApplication{

    public static void main(String[] args) {
        SpringApplication.run(VirementinternationalApplication.class, args);
    }

}

