package com.fenix.projecto.repository;

import com.fenix.projecto.model.Usuario;
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
public class UsuarioRepository implements Repository<Usuario, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Usuario> findAll() {
        return em.createNamedQuery("Usuario.findAll", Usuario.class)
                .getResultList();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return em.createNamedQuery("Usuario.findByCodigo", Usuario.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Usuario save(Usuario e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<Usuario> e) {
        e.forEach(this::save);
    }

    @Transactional
    @Override
    public Usuario update(Usuario e) {
        return em.merge(e);
    }

    public Optional<Usuario> findByUsername(String username) {
        return em.createNamedQuery("Usuario.findByUsername", Usuario.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public Optional<Usuario> findByEmail(String email) {
        return em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public List<Usuario> findViewersOnly() {
        return em.createNamedQuery("Usuario.findViewers", Usuario.class)
                .getResultList();
    }
}
