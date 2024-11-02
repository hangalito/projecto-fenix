package com.fenix.projecto.controller;

import com.fenix.projecto.model.Professor;
import com.fenix.projecto.repository.ProfessorRepository;
import com.fenix.projecto.util.DateTimeConverter;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("teacherBean")
@SessionScoped
public class TeacherBean implements Serializable {
    @Inject
    private ProfessorRepository repository;
    private List<Professor> teachers;
    private List<Professor> selectedTeachers;
    private Professor selectedTeacher;

    private String birthdate;

    @PostConstruct
    public void init() {
        teachers = repository.findAll();
    }

    public List<Professor> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Professor> teachers) {
        this.teachers = teachers;
    }

    public List<Professor> getSelectedTeachers() {
        return selectedTeachers;
    }

    public void setSelectedTeachers(List<Professor> selectedTeachers) {
        this.selectedTeachers = selectedTeachers;
    }

    public Professor getSelectedTeacher() {
        return selectedTeacher;
    }

    public void setSelectedTeacher(Professor selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void loadTeachers() {
        teachers = repository.findAll();
    }

    public void openNew() {
        selectedTeacher = new Professor();
    }

    public void refreshPage() {
        loadTeachers();
        PrimeFaces.current().ajax().update(":main:dt-teachers");
    }

    public void saveTeacher() {
        selectedTeacher.setDataDeNascimento(DateTimeConverter.convertToDate(birthdate));
        String msg;
        if (selectedTeacher.getCodigo() == null) {
            repository.save(selectedTeacher);
            msg = "Novo professor cadastrado";
        } else {
            repository.update(selectedTeacher);
            msg = "Dados do professor actualizados";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        loadTeachers();
        PrimeFaces.current().executeScript("PR('manageTeachersDialog').hide()");
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-teachers");
    }

    public void deleteTeacher() {
        selectedTeachers.remove(selectedTeacher);
        repository.delete(selectedTeacher);
        loadTeachers();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Professor eliminado com sucesso"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-teachers");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedTeachers()) {
            int size = selectedTeachers.size();
            return size > 1 ? size + " professores selecionados" : "1 profeessor selecionado";
        }
        return "Eliminar";
    }

    public boolean hasSelectedTeachers() {
        return selectedTeacher != null && !selectedTeachers.isEmpty();
    }

    public void deleteSelectedStudents() {
        selectedTeachers.forEach(repository::delete);
        teachers.removeAll(selectedTeachers);
        selectedTeachers = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Professores eliminados"));
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-teachers");
        PrimeFaces.current().executeScript("PF('dtTeachers).clearFilters()");
    }

    //<editor-fold des="Auto-Complete Methods">
    public List<String> completeTextNeighbourhood(String query) {
        return teachers.stream().map(Professor::getBairro)
                .filter((String n) -> n.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> completeTextDistrict(String query) {
        return teachers.stream().map(Professor::getDistrito)
                .filter((String d) -> d.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> completeTextState(String query) {
        return teachers.stream().map(Professor::getMunicipio)
                .filter((String s) -> s.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    //</editor-fold>
}
