package com.fenix.projecto.repository;

import com.fenix.projecto.model.Teacher;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class TeacherRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Teacher> findAll() {
        return em.createNamedQuery("Teacher.findAll", Teacher.class)
                .getResultList();
    }

    public List<Teacher> findAllDeleted() {
        return em.createNamedQuery("Teacher.findAllDeleted", Teacher.class)
                .getResultList();
    }

    public Optional<Teacher> findById(Integer id) {
        return em.createNamedQuery("Teacher.findByCode", Teacher.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }

    public Teacher save(Teacher e) {
        e.setDeleted(false);
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Teacher> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Teacher update(Teacher e) {
        return em.merge(e);
    }

    public void delete(Teacher e) {
        e.setDeleted(true);
        update(e);
    }

    public void restore(Teacher e) {
        e.setDeleted(false);
        update(e);
    }

}
