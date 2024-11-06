package com.fenix.projecto.controller;

import com.fenix.projecto.repository.ClassRepository;
import com.fenix.projecto.model.Class;
import com.fenix.projecto.model.Student;
import com.fenix.projecto.util.DateTimeConverter;
import com.fenix.projecto.util.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import org.primefaces.PrimeFaces;

@Named(value = "classBean")
@ViewScoped
public class ClassBean implements Serializable {

    @Inject
    private transient ClassRepository repository;
    private Class selectedClass;
    private List<Class> classes;
    private List<Class> selectedClasses;
    private List<Class> deletedClasses;

    private String startDate;
    private String endDate;

    @PostConstruct
    public void init() {
        classes = repository.findAll();
    }

    //<editor-fold desc="Getters and Setters">
    public Class getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(Class selectedClass) {
        this.selectedClass = selectedClass;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Class> getSelectedClasses() {
        return selectedClasses;
    }

    public void setSelectedClasses(List<Class> selectedClasses) {
        this.selectedClasses = selectedClasses;
    }

    public List<Class> getDeletedClasses() {
        return deletedClasses;
    }

    public void setDeletedClasses(List<Class> deletedClasses) {
        this.deletedClasses = deletedClasses;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    //</editor-fold>

    public int numberOfStudents(Class c) {
        c.getStudents().stream()
                .map(Student::toString)
                .forEach(Logger::info);
        return c.getStudents().size();
    }

    public void openNew() {
        selectedClass = new Class();
    }

    public void saveClass() {
        selectedClass.setStartDate(DateTimeConverter.convertToDate(startDate));
        selectedClass.setEndDate(DateTimeConverter.convertToDate(endDate));
        repository.save(selectedClass);
        loadClasses();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Turma criada com sucesso"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-classes");
        PrimeFaces.current().executeScript("PF('manageClassesDialog').hide()");
    }

    public void loadClasses() {
        classes = repository.findAll();
    }
}
