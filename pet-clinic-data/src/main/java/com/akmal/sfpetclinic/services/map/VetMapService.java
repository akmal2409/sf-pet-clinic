package com.akmal.sfpetclinic.services.map;

import com.akmal.sfpetclinic.model.Vet;
import com.akmal.sfpetclinic.services.CrudService;
import com.akmal.sfpetclinic.services.PetService;
import com.akmal.sfpetclinic.services.VetService;

import java.util.Set;

public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet.getId(), vet);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
