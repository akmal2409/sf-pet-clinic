package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
