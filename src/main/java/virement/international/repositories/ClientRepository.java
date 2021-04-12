package virement.international.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import virement.international.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
