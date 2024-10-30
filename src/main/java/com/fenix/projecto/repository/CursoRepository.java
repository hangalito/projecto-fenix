package com.fenix.projecto.repository;

import com.fenix.projecto.model.Curso;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Stateless
public class CursoRepository implements Repository<Curso, Integer> {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    @Override
    public List<Curso> findAll() {
        return em.createNamedQuery("Curso.findAll", Curso.class)
                .getResultList();
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        return em.createNamedQuery("Curso.findByCodigo", Curso.class)
                .setParameter("codigo", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Curso save(Curso e) {
        em.persist(e);
        return e;
    }

    @Override
    public void saveAll(Collection<Curso> e) {
        e.forEach(this::save);
    }

    @Override
    public Curso update(Curso e) {
        findById(e.getCodigo()).ifPresent((Curso curso) -> {
            curso.setNome(e.getNome());
            curso.setPreco(e.getPreco());
            curso.setInscricoes(e.getInscricoes());
            em.merge(curso);
        });
        return e;
    }

}
