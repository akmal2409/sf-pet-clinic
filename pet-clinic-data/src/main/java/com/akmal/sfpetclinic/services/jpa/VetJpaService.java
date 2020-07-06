package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Vet;
import com.akmal.sfpetclinic.repositories.VetRepository;
import com.akmal.sfpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Profile("jpa")
@Transactional
public class VetJpaService extends AbstractJpaService<Vet, VetRepository>
        implements VetService {

    @Autowired
    public VetJpaService(VetRepository repository) {
        super(repository);
    }
}
