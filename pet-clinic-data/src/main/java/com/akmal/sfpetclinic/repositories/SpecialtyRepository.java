package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
