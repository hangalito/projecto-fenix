package com.fenix.projecto.repository;

import com.fenix.projecto.model.School;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class SchoolRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;


    public List<School> findAll() {
        return em.createNamedQuery("School.findAll", School.class)
                .getResultList();
    }

    public Optional<School> findById(Integer id) {
        return em.createNamedQuery("School.findByCode", School.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }

    public Optional<School> findByName(String name) {
        return em.createNamedQuery("School.findByName", School.class)
                .setParameter("name",name)
                .getResultStream()
                .findFirst();
    }

    public School save(School e) {
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<School> e) {
        e.forEach(this::save);
    }

    @Transactional
    public School update(School e) {
        return em.merge(e);
    }

    public void delete(School e) {
        e.setDeleted(true);
        update(e);
    }

}
