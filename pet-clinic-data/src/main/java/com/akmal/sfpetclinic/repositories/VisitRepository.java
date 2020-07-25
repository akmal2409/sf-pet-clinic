package com.akmal.sfpetclinic.repositories;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface VisitRepository extends JpaRepository<Visit, Long> {


}
