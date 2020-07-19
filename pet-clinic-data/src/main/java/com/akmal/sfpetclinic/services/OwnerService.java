package com.akmal.sfpetclinic.services;

import com.akmal.sfpetclinic.model.Owner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
