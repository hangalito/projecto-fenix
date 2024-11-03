package com.fenix.projecto.repository;

import com.fenix.projecto.model.Course;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CourseRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Course> findAll() {
        return em.createNamedQuery("Course.findAll", Course.class)
                .getResultStream()
                .filter((Course curso) -> !curso.isDeleted())
                .collect(Collectors.toList());
    }

    public Optional<Course> findById(Integer id) {
        return em.createNamedQuery("Course.findByCode", Course.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }

    public Course save(Course e) {
        e.setDeleted(false);
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Course> e) {
        e.forEach(this::save);
    }

    public Course update(Course e) {
        return em.merge(e);
    }

    public void delete(Course curso) {
        curso.setDeleted(true);
        update(curso);
    }

}
