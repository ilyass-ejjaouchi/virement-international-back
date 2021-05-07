package virement.international.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virement.international.repositories.BeneficiareRepository;

@Service
public class BeneficiareService {
    @Autowired BeneficiareRepository beneficiareRepo;

}
