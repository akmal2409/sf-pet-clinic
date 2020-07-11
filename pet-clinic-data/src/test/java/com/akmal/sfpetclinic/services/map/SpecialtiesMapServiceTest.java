package com.akmal.sfpetclinic.services.map;

import com.akmal.sfpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtiesMapServiceTest {

    SpecialtiesMapService specialtiesMapService;
    final long id = 1l;

    @BeforeEach
    void setUp() {
        specialtiesMapService = new SpecialtiesMapService();
        Specialty testSpecialty = new Specialty();
        testSpecialty.setId(id);
        specialtiesMapService.save(testSpecialty);
    }

    @Test
    void findAll() {
        Set<Specialty> specialtySet = specialtiesMapService.findAll();

        assertEquals(1, specialtySet.size());
    }

    @Test
    void findById() {
        Specialty foundSpecialty = specialtiesMapService.findById(id);

        assertNotNull(foundSpecialty);
        assertNotNull(foundSpecialty.getId());

        assertEquals(id, foundSpecialty.getId());
    }

    @Test
    void save() {
        Specialty savedSpecialty = specialtiesMapService.save(specialtiesMapService.findById(id));

        assertNotNull(savedSpecialty);
        assertNotNull(savedSpecialty.getId());

        assertEquals(id, savedSpecialty.getId());
    }

    @Test
    void delete() {
        specialtiesMapService.delete(specialtiesMapService.findById(id));

        assertEquals(0, specialtiesMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialtiesMapService.deleteById(id);

        assertEquals(0, specialtiesMapService.findAll().size());
    }
}