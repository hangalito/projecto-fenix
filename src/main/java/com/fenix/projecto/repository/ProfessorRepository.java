package com.fenix.projecto.repository;

import com.fenix.projecto.model.Professor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProfessorRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Professor> findAll() {
        return em.createNamedQuery("Professor.findAll", Professor.class)
                .getResultList();
    }

    public Optional<Professor> findById(Integer id) {
        return em.createNamedQuery("Professor.findByCodgo", Professor.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    public Professor save(Professor e) {
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Professor> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Professor update(Professor e) {
        return em.merge(e);
    }

    public void delete(Professor e) {
        e.setDeleted(true);
        update(e);
    }

}
