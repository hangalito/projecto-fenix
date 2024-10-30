package com.fenix.projecto.repository;

import com.fenix.projecto.model.Professor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Bartolomeu Hangalo
 */
@Stateless
public class ProfessorRepository<E, ID> implements Repository<Professor, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Professor> findAll() {
        return em.createNamedQuery("Professor.findAll", Professor.class)
                .getResultList();
    }

    @Override
    public Optional<Professor> findById(Integer id) {
        return em.createNamedQuery("Professor.findByCodgo", Professor.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Professor save(Professor e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<Professor> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public Professor update(Professor e) {
        return em.merge(e);
    }
}
