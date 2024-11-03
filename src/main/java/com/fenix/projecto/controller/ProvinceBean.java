package com.fenix.projecto.controller;

import com.fenix.projecto.model.Province;
import com.fenix.projecto.repository.ProvinceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Named(value = "provinceBean")
@SessionScoped
public class ProvinceBean implements Serializable {

    @Inject
    private ProvinceRepository repo;
    private List<Province> provincias;

    /**
     * Creates a new instance of ProvinciaBean
     */
    public ProvinceBean() {
    }

    @PostConstruct
    public void init() {
        provincias = repo.findAll();
    }

    public ProvinceRepository getRepo() {
        return repo;
    }

    public void setRepo(ProvinceRepository repo) {
        this.repo = repo;
    }

    public List<Province> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Province> provincias) {
        this.provincias = provincias;
    }

}
