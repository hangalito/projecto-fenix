package com.fenix.projecto.repository;

import com.fenix.projecto.model.Provincia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Stateless
public class ProvinciaRepsoitory {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    EntityManager em;

    public List<Provincia> findAll() {
        return em.createNamedQuery("Provincia.findAll", Provincia.class)
                .getResultList();
    }

    public Optional<Provincia> findById(Integer codigo) {
        return em.createNamedQuery("Provincia.findByCodigo", Provincia.class)
                .setParameter("codigo", codigo)
                .getResultStream()
                .findFirst();
    }

    public Optional<Provincia> findByNome(String nome) {
        return em.createNamedQuery("Provincia.findByNome", Provincia.class)
                .setParameter("nome", nome)
                .getResultStream()
                .findFirst();
    }
}
