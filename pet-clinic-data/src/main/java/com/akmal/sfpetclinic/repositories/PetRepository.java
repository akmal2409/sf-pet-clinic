package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetRepository extends JpaRepository<Pet, Long> {
}
