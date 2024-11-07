package com.fenix.projecto.repository;

import com.fenix.projecto.model.Subscription;
import com.fenix.projecto.model.SubscriptionPK;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class SubscriptionRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;
    
    public List<Subscription> findAll() {
        return em.createNamedQuery("Subscription.findAll", Subscription.class)
                .getResultList();
    }
    
    public Optional<Subscription> findById(SubscriptionPK inscricaoPK) {
        return findAll().stream()
                .filter((Subscription inscricao) -> inscricao.getSubscriptionPK().equals(inscricaoPK))
                .findFirst();
    }
    
    public Subscription save(Subscription e) {
        em.persist(e);
        return e;
    }
    
    public void saveAll(Collection<Subscription> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Subscription update(Subscription e) {
        return em.merge(e);
    }

}
