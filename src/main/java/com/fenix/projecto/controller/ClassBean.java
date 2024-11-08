package com.fenix.projecto.controller;

import com.fenix.projecto.repository.ClassRepository;
import com.fenix.projecto.model.Class;
import com.fenix.projecto.model.Student;
import com.fenix.projecto.repository.StudentRepository;
import com.fenix.projecto.util.DateTimeConverter;
import com.fenix.projecto.util.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.primefaces.PrimeFaces;

@Named(value = "classBean")
@ViewScoped
public class ClassBean implements Serializable {

    @Inject
    private transient ClassRepository repository;
    @Inject
    private transient StudentRepository studentRepository;

    private Class selectedClass;
    private List<Class> classes;
    private List<Class> selectedClasses;
    private List<Class> deletedClasses;
    private List<Student> availableStudents;
    private List<Student> selectedStudents = new ArrayList<>();

    private List<Student> initialState = new ArrayList<>();
    private boolean changed = false;

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

    public List<Student> getAvailableStudents() {
        return availableStudents;
    }

    public void setAvailableStudents(List<Student> availableStudents) {
        this.availableStudents = availableStudents;
    }

    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
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

    public void loadStudents() {
        availableStudents = studentRepository.findAll();
        selectedStudents = new ArrayList<>(selectedClass.getStudents());
        initialState = selectedClass.getStudents().stream().collect(Collectors.toList());
        PrimeFaces.current().executeScript("PF('pickClassStudentsDialog').show()");
    }

    public void addStudents() {
        var removed = initialState.stream().collect(Collectors.toList());
        removed.removeAll(selectedStudents);

        Logger.info("Dados iniciais: " + initialState);
        Logger.info("Dados pendentes para actualização: " + selectedStudents);
        Logger.info("Dados a serem removidos: " + removed);

        repository.addAllStudents(selectedClass, selectedStudents);
        repository.removeAllStudents(selectedClass, removed);

        loadClasses();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Lista de alunos actualizada"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-classes");
        PrimeFaces.current().executeScript("PF('pickClassStudentsDialog').hide()");
    }

    public boolean hasSelectedStudents() {
        return selectedStudents != null && !selectedStudents.isEmpty();
    }

    public boolean isSelected(Student student) {
        if (selectedStudents == null) {
            return false;
        }
        return selectedStudents.contains(student);
    }

    public void selectStudent(Student student) {
        if (!selectedStudents.contains(student)) {
            selectedStudents.add(student);
            PrimeFaces.current().ajax().update(":dialogs:dt-pick-students");
        }
    }

    public void diselectStudent(Student student) {
        selectedStudents.remove(student);
        PrimeFaces.current().ajax().update(":dialogs:dt-pick-students");
    }

    public boolean canSave() {
        // Compara o estado atual com o estado inicial
        changed = !Objects.equals(new HashSet<>(selectedStudents), new HashSet<>(initialState));
        return changed;
    }
}
