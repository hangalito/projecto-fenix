package com.fenix.projecto.repository;

import com.fenix.projecto.model.Class;
import com.fenix.projecto.model.Student;
import com.fenix.projecto.util.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ClassRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    private EntityManager em;

    public List<Class> findAll() {
        return em.createNamedQuery("Class.findAll", Class.class)
                .getResultList();
    }

    public Optional<Class> findById(Integer id) {
        return em.createNamedQuery("Class.findByCode", Class.class)
                .setParameter("code", id)
                .getResultStream()
                .findFirst();
    }

    public Class save(Class e) {
        em.persist(e);
        return e;
    }

    public void saveAll(Collection<Class> e) {
        e.forEach(this::save);
    }

    @Transactional
    public Class update(Class e) {
        return em.merge(e);
    }

    public void addStudent(Class entity, Student student) {
        findById(entity.getCode()).ifPresent((Class loaded) -> {
            if (loaded.getStudents() == null) {
                loaded.setStudents(new ArrayList<>());
            }
            loaded.getStudents().add(student);
            update(loaded);
        });
    }

    @Transactional
    public void addAllStudents(Class entity, Collection<Student> students) {
        findById(entity.getCode()).ifPresent((Class loaded) -> {
            if (loaded.getStudents() == null) {
                loaded.setStudents(new ArrayList<>());
            }

            for (Student student : students) {
                if (!loaded.getStudents().contains(student)) {
                    loaded.getStudents().add(student);
                }
            }

            update(loaded);
        });
    }

    @Transactional
    public void removeAllStudents(Class entity, Collection<Student> students) {
        findById(entity.getCode()).ifPresent((Class loaded) -> {
            for (Student student : students) {
                Logger.info("Removendo o estudante" + student.getName() + " da turma " + loaded.getName());
                loaded.getStudents().remove(student);
            }
            update(loaded);
        });
    }

}
