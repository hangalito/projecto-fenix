package com.fenix.projecto.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author hangalo
 * @param <E> The entity class
 * @param <ID> The type of ID
 */
public interface Repository<E, ID> {

    List<E> findAll();

    Optional<E> findById(ID id);

    E save(E e);

    void saveAll(Collection<E> e);

    E update(E e);

}
