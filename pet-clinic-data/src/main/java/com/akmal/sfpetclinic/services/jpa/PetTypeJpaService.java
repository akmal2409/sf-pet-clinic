package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.PetType;
import com.akmal.sfpetclinic.repositories.PetTypeRepository;
import com.akmal.sfpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Profile("jpa")
@Transactional
public class PetTypeJpaService extends AbstractJpaService<PetType, PetTypeRepository>
        implements PetTypeService {

    @Autowired
    public PetTypeJpaService(PetTypeRepository repository) {
        super(repository);
    }
}
