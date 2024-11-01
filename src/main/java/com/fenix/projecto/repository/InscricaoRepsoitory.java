package com.fenix.projecto.repository;

import com.fenix.projecto.model.Inscricao;
import com.fenix.projecto.model.InscricaoPK;
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
public class InscricaoRepsoitory implements Repository<Inscricao, InscricaoPK> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Inscricao> findAll() {
        return em.createNamedQuery("Inscricao.findAll", Inscricao.class)
                .getResultList();
    }

    @Override
    public Optional<Inscricao> findById(InscricaoPK inscricaoPK) {
        return findAll().stream()
                .filter((Inscricao inscricao) -> inscricao.getInscricaoPK().equals(inscricaoPK))
                .findFirst();
    }

    @Override
    public Inscricao save(Inscricao e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<Inscricao> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public Inscricao update(Inscricao e) {
        return em.merge(e);
    }

    @Override
    public void delete(Inscricao e) {
        e.setDeleted(true);
        update(e);
    }

}
