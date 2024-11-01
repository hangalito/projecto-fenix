package com.fenix.projecto.repository;

import com.fenix.projecto.model.FormacaoAluno;
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
public class FormacaoRepository implements Repository<FormacaoAluno, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<FormacaoAluno> findAll() {
        return em.createNamedQuery("FormacaoAluno.findAll", FormacaoAluno.class)
                .getResultList();
    }

    @Override
    public Optional<FormacaoAluno> findById(Integer id) {
        return em.createNamedQuery("FormacaoAluno.findByCodigo", FormacaoAluno.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public FormacaoAluno save(FormacaoAluno e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<FormacaoAluno> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public FormacaoAluno update(FormacaoAluno e) {
        return em.merge(e);
    }

    @Override
    public void delete(FormacaoAluno e) {
        e.setDeleted(true);
        update(e);
    }

}
