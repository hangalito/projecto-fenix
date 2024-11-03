package com.fenix.projecto.repository;

import com.fenix.projecto.model.Province;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProvinceRepository {

    @PersistenceContext(unitName = "projecto_fenix_pu")
    EntityManager em;

    public List<Province> findAll() {
        return em.createNamedQuery("Province.findAll", Province.class)
                .getResultList();
    }

    public Optional<Province> findById(Integer codigo) {
        return em.createNamedQuery("Province.findByCode", Province.class)
                .setParameter("code", codigo)
                .getResultStream()
                .findFirst();
    }

    public Optional<Province> findByNome(String nome) {
        return em.createNamedQuery("Province.findByName", Province.class)
                .setParameter("name", nome)
                .getResultStream()
                .findFirst();
    }
}
