package com.fenix.projecto.controller;

import com.fenix.projecto.model.School;
import com.fenix.projecto.repository.SchoolRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.PrimeFaces;

@Named(value = "schoolBean")
@ViewScoped
public class SchoolBean implements Serializable {

    @Inject
    private SchoolRepository repository;
    private School selectedSchool;
    private List<School> selectedSchools;
    private List<School> schools;
    private List<School> deletedSchools;

    public SchoolBean() {
    }

    @PostConstruct
    public void init() {
        schools = repository.findAll();
    }

    //<editor-fold desc="Getters and Setters">
    public SchoolRepository getRepository() {
        return repository;
    }

    public void setRepository(SchoolRepository repository) {
        this.repository = repository;
    }

    public School getSelectedSchool() {
        return selectedSchool;
    }

    public void setSelectedSchool(School selectedSchool) {
        this.selectedSchool = selectedSchool;
    }

    public List<School> getSelectedSchools() {
        return selectedSchools;
    }

    public void setSelectedSchools(List<School> selectedSchools) {
        this.selectedSchools = selectedSchools;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<School> getDeletedSchools() {
        return deletedSchools;
    }

    public void setDeletedSchools(List<School> deletedSchools) {
        this.deletedSchools = deletedSchools;
    }
    //</editor-fold>

    public void loadSchools() {
        schools = repository.findAll();
    }

    public void refreshPage() {
        loadSchools();
        PrimeFaces.current().ajax().update(":main:dt-schools");
    }

    public void openNew() {
        selectedSchool = new School();
    }

    public void save() {
        String msg;
        if (selectedSchool.getCode() == null) {
            repository.save(selectedSchool);
            msg = "Escola cadastrada com sucesso";
        } else {
            repository.update(selectedSchool);
            msg = "Dados actualizados com sucesso";
        }
        loadSchools();
        PrimeFaces.current().executeScript("PF('manageSchoolsDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-schools");
    }

    public int numOfStudents(School school) {
        return school.getStudents().size();
    }

    public boolean hasSeletedSchools() {
        return selectedSchools != null && !selectedSchools.isEmpty();
    }

    public String getDeleteMessage() {
        if (hasSeletedSchools()) {
            int size = selectedSchools.size();
            return size > 1 ? size + " escolas selecionadas" : "1 escola selecionada";
        }
        return "Eliminar";
    }

    public void deleteSelectedSchools() {
        selectedSchools.forEach(repository::delete);
        schools.removeAll(selectedSchools);
        selectedSchools = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Escolas eliminadas com sucesso"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-schools", ":main:delete-school-button");
        PrimeFaces.current().executeScript("PF('dtSchools').clearFilters()");
    }

    public void deleteSchool() {
        if (selectedSchools != null) {
            selectedSchools.remove(selectedSchool);
        }
        repository.delete(selectedSchool);
        loadSchools();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Escola eliminada"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-schools");
    }

    public void loadDeletedSchools() {
        deletedSchools = repository.findAllDeleted();
        selectedSchools = new ArrayList<>();
        selectedSchool = new School();
    }

    public void restoreSchools() {
        selectedSchools.forEach(repository::restore);
        loadSchools();
        selectedSchools.clear();
        selectedSchools = null;
        selectedSchool = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Escola restaurada com sucesso"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-schools", ":main:delete-school-button");
        PrimeFaces.current().executeScript("PF('manageDeletedSchoolsDialog').hide()");
    }

    public String getRestoreMessage() {
        if (hasSeletedSchools()) {
            return getDeleteMessage();
        }
        return "Restaurar";
    }

    //<editor-fold desc="Auto-complete methods">
    public List<String> autoCompleteNeighbourhoods(String query) {
        return schools.stream().map(School::getNeighbourhood)
                .filter((String n) -> n.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> autoCompleteDistricts(String query) {
        return schools.stream().map(School::getDistrict)
                .filter((String d) -> d.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> autoCompleteStates(String query) {
        return schools.stream().map(School::getState)
                .filter((String s) -> s.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    //</editor-fold>

}
