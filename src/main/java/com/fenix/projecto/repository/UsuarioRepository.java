package com.fenix.projecto.repository;

import com.fenix.projecto.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class UsuarioRepository{

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;
    
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }
    
    public Optional<User> findById(Integer id) {
        return em.createNamedQuery("User.findByCode", User.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }
    
    public User save(User e) {
        em.persist(e);
        return e;
    }
    
    public void saveAll(Collection<User> e) {
        e.forEach(this::save);
    }

    @Transactional
    public User update(User e) {
        return em.merge(e);
    }

    public Optional<User> findByUsername(String username) {
        return em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public List<User> findViewersOnly() {
        return em.createNamedQuery("User.findViewers", User.class)
                .getResultList();
    }
    
    public void delete(User e) {
        e.setDeleted(true);
        update(e);
    }

}
