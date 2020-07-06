package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Specialty;
import com.akmal.sfpetclinic.repositories.SpecialtyRepository;
import com.akmal.sfpetclinic.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Profile("jpa")
@Transactional
public class SpecialtyJpaService extends AbstractJpaService<Specialty, SpecialtyRepository>
        implements SpecialtyService {

    @Autowired
    public SpecialtyJpaService(SpecialtyRepository repository) {
        super(repository);
    }
}
