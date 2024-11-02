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
 * @author Bartolomeu Hangalo
 */
@Stateless
public class EscolaRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;


    public List<Escola> findAll() {
        return em.createNamedQuery("Escola.findAll", Escola.class)
                .getResultList();
    }

    public Optional<Escola> findById(Integer id) {
        return em.createNamedQuery("Escola.findByCodigo", Escola.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    public Optional<Escola> findByName(String name) {
        return em.createNamedQuery("Escola.findByNome", Escola.class)
                .setParameter("nome",name)
                .getResultStream()
                .findFirst();
    }

    public Escola save(Escola e) {
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Escola> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Escola update(Escola e) {
        return em.merge(e);
    }

    public void delete(Escola e) {
        e.setDeleted(true);
        update(e);
    }

}
