package com.akmal.sfpetclinic.services.map;

import com.akmal.sfpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;
    final long petId = 1l;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        Pet pet = new Pet();
        pet.setId(petId);
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void findById() {
        Pet testPet = petMapService.findById(petId);

        assertEquals(petId, testPet.getId());
    }

    @Test
    void save() {
        long id = 2l;
        Pet testPet = new Pet();
        testPet.setId(id);
        Pet savedPet = petMapService.save(testPet);

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(id, savedPet.getId());
    }

    @Test
    void delete() {
        assertEquals(1, petMapService.findAll().size());

        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, petMapService.findAll().size());

        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }
}