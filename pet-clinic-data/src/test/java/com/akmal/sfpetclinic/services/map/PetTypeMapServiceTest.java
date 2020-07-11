package com.akmal.sfpetclinic.services.map;

import com.akmal.sfpetclinic.model.Pet;
import com.akmal.sfpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    final long id = 1l;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        PetType petType = new PetType();
        petType.setId(id);
        petTypeMapService.save(petType);
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = petTypeMapService.findAll();

        assertEquals(1, petTypeSet.size());
    }

    @Test
    void findById() {
        PetType testType = petTypeMapService.findById(id);

        assertNotNull(testType.getId());
        assertEquals(id, testType.getId());
    }

    @Test
    void save() {
        PetType saved = petTypeMapService.save(petTypeMapService.findById(id));

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(id, saved.getId());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(id));

        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(id);

        assertEquals(0, petTypeMapService.findAll().size());
    }
}