package com.akmal.sfpetclinic.services.map;

import com.akmal.sfpetclinic.model.Specialty;
import com.akmal.sfpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    VetMapService vetMapService;
    final long id = 1l;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialtiesMapService());
        Vet testVet = new Vet();
        testVet.setId(id);
        vetMapService.save(testVet);
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = vetMapService.findAll();

        assertEquals(1, vetSet.size());
    }

    @Test
    void findById() {
        Vet foundVet = vetMapService.findById(id);

        assertNotNull(foundVet);
        assertNotNull(foundVet.getId());

        assertEquals(id, foundVet.getId());
    }

    @Test
    void save() {
        long testId = 2l;
        Vet testVet2 = new Vet();
        testVet2.setId(testId);
        Vet savedVet = vetMapService.save(testVet2);

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
        assertEquals(testId, savedVet.getId());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(id));

        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(id);

        assertEquals(0, vetMapService.findAll().size());
    }
}