package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Pet;
import com.akmal.sfpetclinic.model.Visit;
import com.akmal.sfpetclinic.services.PetService;
import com.akmal.sfpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    MockMvc mockMvc;

    @InjectMocks
    VisitController controller;

    //Mock models
    Pet pet;
    Owner owner;
    Visit visit;

    @BeforeEach
    void setUp() {

        controller = new VisitController(petService, visitService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //given
        visit = new Visit();
        owner = new Owner();
        pet = new Pet();

        visit.setId(2L);
        owner.setId(1L);
        pet.setId(1L);

        pet.setOwner(owner);
        pet.getVisits().add(visit);
        visit.setPet(pet);
    }

    @Test
    void createVisitForm() throws Exception{
        //when
        when(petService.findById(anyLong())).thenReturn(pet);

        //then
        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

        verify(petService, times(1)).findById(anyLong());

    }

    @Test
    void processCreateForm() throws Exception{
        //when
        when(petService.findById(anyLong())).thenReturn(pet);

        //then
        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2000-06-06")
                .param("description", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void updateVisitForm() throws Exception{
        //when
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.findById(anyLong())).thenReturn(visit);


        //then
        mockMvc.perform(get("/owners/1/pets/1/visits/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void processUpdateForm() throws Exception{

        //when
        when(petService.findById(anyLong())).thenReturn(pet);

        //then
        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2000-06-06")
                .param("description", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).findById(anyLong());
    }
}