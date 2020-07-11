package com.akmal.sfpetclinic.services.jpa;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}