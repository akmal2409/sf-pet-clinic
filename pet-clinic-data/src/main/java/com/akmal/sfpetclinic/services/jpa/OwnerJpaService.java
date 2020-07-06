package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.repositories.OwnerRepository;
import com.akmal.sfpetclinic.repositories.PetRepository;
import com.akmal.sfpetclinic.repositories.PetTypeRepository;
import com.akmal.sfpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class OwnerJpaService extends AbstractJpaService<Owner, OwnerRepository>
        implements OwnerService {

    @Autowired
    public OwnerJpaService(OwnerRepository repository) {
        super(repository);
    }

    public Owner findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }
}