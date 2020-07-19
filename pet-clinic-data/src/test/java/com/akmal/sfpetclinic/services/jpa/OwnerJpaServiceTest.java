package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService ownerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Owner returnOwner = new Owner();
        long id = 1l;
        String lastName = "Smith";
        returnOwner.setId(id);
        returnOwner.setLastName(lastName);

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = ownerService.findByLastName("Smith");

        assertEquals(lastName, smith.getLastName());
        assertEquals(id, smith.getId());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findAllByLastNameLike() {
        //given
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owner1.setId(2L);
        owner2.setId(1L);

        List<Owner> owners = new ArrayList<>();
        owners.add(owner1);
        owners.add(owner2);

        //when
        when(ownerRepository.findAllByLastNameLike(anyString())).thenReturn(owners);

        //then
        List<Owner> returnedOwners = ownerService.findAllByLastNameLike("test");
        assertEquals(2, returnedOwners.size());
        verify(ownerRepository, times(1)).findAllByLastNameLike(anyString());
    }
}