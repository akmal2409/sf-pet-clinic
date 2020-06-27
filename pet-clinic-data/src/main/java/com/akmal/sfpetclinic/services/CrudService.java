package com.akmal.sfpetclinic.services;

import java.util.Set;

// We mimic JPA CrudRepository

public interface CrudService<T, ID> {
    // Return Iterable of type T
    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);

}
