package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
