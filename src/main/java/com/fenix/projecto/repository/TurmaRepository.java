package com.fenix.projecto.repository;

import com.fenix.projecto.model.Class;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class TurmaRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Class> findAll() {
        return em.createNamedQuery("Class.findAll", Class.class)
                .getResultList();
    }

    public Optional<Class> findById(Integer id) {
        return em.createNamedQuery("Class.findByCodige", Class.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }
    
    public Class save(Class e) {
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Class> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Class update(Class e) {
        return em.merge(e);
    }

}
