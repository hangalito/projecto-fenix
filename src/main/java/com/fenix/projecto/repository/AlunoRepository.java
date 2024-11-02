package com.fenix.projecto.repository;

import com.fenix.projecto.model.Aluno;
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
public class AlunoRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Aluno> findAll() {
        return em.createNamedQuery("Aluno.findAll", Aluno.class)
                .getResultList();
    }

    public Optional<Aluno> findById(Integer id) {
        return em.createNamedQuery("Aluno.findByCodigo", Aluno.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    public Aluno save(Aluno e) {
        e.setDeleted(false);
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Aluno> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Aluno update(Aluno e) {
        return em.merge(e);
    }

    public void delete(Aluno e) {
        e.setDeleted(true);
        update(e);
    }

}
