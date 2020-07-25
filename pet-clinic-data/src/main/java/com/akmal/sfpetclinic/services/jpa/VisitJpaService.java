package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Visit;
import com.akmal.sfpetclinic.repositories.VisitRepository;
import com.akmal.sfpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class VisitJpaService extends AbstractJpaService<Visit, VisitRepository>
        implements VisitService {

    public VisitJpaService(VisitRepository repository) {
        super(repository);
    }


}
