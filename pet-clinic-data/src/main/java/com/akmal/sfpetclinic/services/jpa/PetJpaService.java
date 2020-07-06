package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Pet;
import com.akmal.sfpetclinic.repositories.PetRepository;
import com.akmal.sfpetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Profile("jpa")
@Transactional
public class PetJpaService extends AbstractJpaService<Pet, PetRepository>
        implements PetService {

    @Autowired
    public PetJpaService(PetRepository repository) {
        super(repository);
    }
}
