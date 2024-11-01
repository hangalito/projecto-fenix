package com.fenix.projecto.repository;

import com.fenix.projecto.model.Curso;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Stateless
public class CursoRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Curso> findAll() {
        return em.createNamedQuery("Curso.findAll", Curso.class)
                .getResultStream()
                .filter((Curso curso) -> !curso.isDeleted())
                .collect(Collectors.toList());
    }

    public Optional<Curso> findById(Integer id) {
        return em.createNamedQuery("Curso.findByCodigo", Curso.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    public Curso save(Curso e) {
        e.setDeleted(false);
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Curso> e) {
        e.forEach(this::save);
    }

    public Curso update(Curso e) {
        return em.merge(e);
    }

    public void delete(Curso curso) {
        curso.setDeleted(true);
        update(curso);
    }

}
