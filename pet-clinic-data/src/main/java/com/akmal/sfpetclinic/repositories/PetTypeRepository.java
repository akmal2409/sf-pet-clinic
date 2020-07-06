package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
