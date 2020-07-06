package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
