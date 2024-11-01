package com.fenix.projecto.repository;

import com.fenix.projecto.model.Escola;
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
public class EscolaRepository implements Repository<Escola, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Escola> findAll() {
        return em.createNamedQuery("Escola.findAll", Escola.class)
                .getResultList();
    }

    @Override
    public Optional<Escola> findById(Integer id) {
        return em.createNamedQuery("Escola.findByCodigo", Escola.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Escola save(Escola e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<Escola> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public Escola update(Escola e) {
        return em.merge(e);
    }

    @Override
    public void delete(Escola e) {
        
    }

}
