package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    MockMvc mockMvc;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        Owner testOwner1 = new Owner();
        Owner testOwner2 = new Owner();
        testOwner1.setId(1l);
        testOwner2.setId(2l);
        owners.add(testOwner1);
        owners.add(testOwner2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void testGetFindOwnersForm() throws Exception{
        //then
        mockMvc.perform(get("/owners/find"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(status().isOk());
    }

    @Test
    void testProcessFindFormMany() throws Exception{
        //given
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        owner1.setId(1L);
        owner2.setId(2l);
        List<Owner> ownerSet = new ArrayList<>();
        ownerSet.add(owner1);
        ownerSet.add(owner2);

        //when
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownerSet);

        //then
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(view().name("owners/ownersList"));
        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormOne() throws Exception{
        //given
        Owner owner = new Owner();
        owner.setId(2L);
        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner);

        //when
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownerList);

        //then
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"));

        verify(ownerService, times(1)).findAllByLastNameLike(anyString());

    }

    @Test
    void testShowOwner() throws Exception{
        //given
        Owner owner = new Owner();
        owner.setId(2L);

        //when
        when(ownerService.findById(anyLong())).thenReturn(owner);

        //then
        mockMvc.perform(get("/owners/2"))
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void testInitCreationForm() throws Exception{

        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processCreatingForm() throws Exception{
        //given
        Owner returnedOwner = new Owner();
        returnedOwner.setId(1L);
        returnedOwner.setLastName("Test");
        returnedOwner.setFirstName("Test");
        returnedOwner.setAddress("Test");
        returnedOwner.setTelephone("test");
        returnedOwner.setCity("Test city");

        //when
        when(ownerService.save(any())).thenReturn(returnedOwner);

        //then
        mockMvc.perform(post("/owners/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "test")
                .param("lastName", "test")
                .param("address", "test")
                .param("city", "test")
                .param("telephone", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(ownerService, times(1)).save(any());
    }

    @Test
    void testUpdateOwnerForm() throws Exception{
        //given
        Owner testOwner = new Owner();
        testOwner.setId(2L);

        //when
        when(ownerService.findById(anyLong())).thenReturn(testOwner);

        //then
        mockMvc.perform(get("/owners/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void testProcessUpdateForm() throws Exception{
        //given
        Owner testOwner = new Owner();
        testOwner.setId(1L);

        //when
        when(ownerService.save(any())).thenReturn(testOwner);

        //then
        mockMvc.perform(post("/owners/1/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "test")
                .param("lastName", "test")
                .param("address", "test")
                .param("city", "test")
                .param("telephone", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("redirect:/owners/1"));

        verify(ownerService, times(1)).save(any());
    }

    /*
    @Test
    void testEmptyLastNameFindOwner() throws Exception{
        //then
        mockMvc.perform(get("/owners")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(view().name("owners/ownersList"));

        verify(ownerService, times(1)).findAll();
    }
     */
}