package com.fenix.projecto.repository;

import com.fenix.projecto.model.Turma;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Stateless
public class TurmaRepository implements Repository<Turma, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Turma> findAll() {
        return em.createNamedQuery("Turma.findAll", Turma.class)
                .getResultList();
    }

    @Override
    public Optional<Turma> findById(Integer id) {
        return em.createNamedQuery("Turma.findByCodigo", Turma.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Turma save(Turma e) {
         em.persist(e);
         return e;
    }

    @Override
    public void saveAll(Collection<Turma> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public Turma update(Turma e) {
        return em.merge(e);
    }
}
