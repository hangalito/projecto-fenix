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
 *
 * @author Bartolomeu Hangalo
 */
@Stateless
public class AlunoRepository implements Repository<Aluno, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Aluno> findAll() {
        return em.createNamedQuery("Aluno.findAll", Aluno.class)
                .getResultList();
    }

    @Override
    public Optional<Aluno> findById(Integer id) {
        return em.createNamedQuery("Aluno.findByCodigo", Aluno.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Aluno save(Aluno e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<Aluno> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public Aluno update(Aluno e) {
        return em.merge(e);
    }

    @Override
    public void delete(Aluno e) {
        e.setDeleted(true);
        update(e);
    }

}
