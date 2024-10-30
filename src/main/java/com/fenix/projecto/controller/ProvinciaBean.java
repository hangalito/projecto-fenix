package com.fenix.projecto.controller;

import com.fenix.projecto.model.Provincia;
import com.fenix.projecto.repository.ProvinciaRepsoitory;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hangalo
 */
@Named(value = "provinciaBean")
@SessionScoped
public class ProvinciaBean implements Serializable {

    @Inject
    private ProvinciaRepsoitory repo;
    private List<Provincia> provincias;

    /**
     * Creates a new instance of ProvinciaBean
     */
    public ProvinciaBean() {
    }

    @PostConstruct
    public void init() {
        provincias = repo.findAll();
    }

    public ProvinciaRepsoitory getRepo() {
        return repo;
    }

    public void setRepo(ProvinciaRepsoitory repo) {
        this.repo = repo;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

}
