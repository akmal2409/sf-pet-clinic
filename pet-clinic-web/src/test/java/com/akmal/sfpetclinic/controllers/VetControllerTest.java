package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Vet;
import com.akmal.sfpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    MockMvc mockMvc;

    @Mock
    VetService vetService;

    @InjectMocks
    VetController controller;

    Set<Vet> vets;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Vet testVet1 = new Vet();
        Vet testVet2 = new Vet();
        testVet1.setId(1l);
        testVet2.setId(2l);
        vets = new HashSet<>();
        vets.add(testVet1);
        vets.add(testVet2);
    }

    @Test
    void listVets() throws Exception{
        when(vetService.findAll()).thenReturn(vets);
        mockMvc.perform(org.springframework.test.web.servlet.request
                .MockMvcRequestBuilders.get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }
}