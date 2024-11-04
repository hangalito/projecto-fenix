package com.fenix.projecto.repository;

import com.fenix.projecto.model.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Stateless
public class StudentRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Student> findAll() {
        return em.createNamedQuery("Student.findAll", Student.class)
                .getResultList();
    }

    public List<Student> findAllDeleted() {
        return em.createNamedQuery("Student.findAllDeleted", Student.class)
                .getResultList();
    }

    public Optional<Student> findById(Integer id) {
        return em.createNamedQuery("Student.findByCode", Student.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }

    public Student save(Student e) {
        e.setDeleted(false);
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Student> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Student update(Student e) {
        return em.merge(e);
    }

    public void delete(Student e) {
        e.setDeleted(true);
        update(e);
    }

    public void restore(Student e) {
        e.setDeleted(false);
        update(e);
    }

}
