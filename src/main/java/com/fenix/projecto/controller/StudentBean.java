package com.fenix.projecto.controller;

import com.fenix.projecto.model.Aluno;
import com.fenix.projecto.model.Escola;
import com.fenix.projecto.repository.AlunoRepository;
import com.fenix.projecto.repository.EscolaRepository;
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

/**
 * @author hangalo
 */
@Named(value = "studentBean")
@SessionScoped
public class StudentBean implements Serializable {

    @Inject
    private AlunoRepository repo;
    @Inject
    private EscolaRepository escolaRepository;

    private List<Aluno> students;
    private List<Aluno> selectedStudents;
    private Aluno selectedStudent;
    private String birthdate;

    @PostConstruct
    public void init() {
        students = repo.findAll();
    }

    public List<Aluno> getStudents() {
        return students;
    }

    public void setStudents(List<Aluno> students) {
        this.students = students;
    }

    public List<Aluno> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Aluno> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public Aluno getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Aluno selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void openNew() {
        selectedStudent = new Aluno();
    }

    public void refreshPage() {
        loadStudents();
        PrimeFaces.current().ajax().update(":main:dt-students");
    }

    public void loadStudents() {
        students = repo.findAll();
    }

    public void saveStudent() {
        String msg;
        selectedStudent.setDataDeNascimento(DateTimeConverter.convertToDate(birthdate));
        if (selectedStudent.getCodigo() == null) {
            repo.save(selectedStudent);
            msg = "Aluno cadastrado";
        } else {
            msg = "Dados actualizados";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        loadStudents();
        PrimeFaces.current().executeScript("PF('manageStudentsDialog').hide()");
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-students");
    }

    public void deleteStudent() {
        selectedStudents.remove(selectedStudent);
        repo.delete(selectedStudent);
        loadStudents();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno eliminado"));
        PrimeFaces.current().ajax().update("main:messages", "main:dt-students");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedStudents()) {
            int size = selectedStudents.size();
            return size > 1 ? size + " alunos selecionados" : "1 aluno selecionado";
        }
        return "Eliminar";
    }

    public boolean hasSelectedStudents() {
        return selectedStudents != null && !selectedStudents.isEmpty();
    }

    public void deleteSelectedStudents() {
        selectedStudents.forEach(repo::delete);
        this.students.removeAll(selectedStudents);
        selectedStudents = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alunos removidos"));
        PrimeFaces.current().ajax().update("main:messages", "main:dt-students");
        PrimeFaces.current().executeScript("PF('dtStudents').clearFilters()");
    }

    public void exportUnavailable() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                "Exportar dados",
                "Esta funcionalidade ainda não está disponivel."));
    }

    public List<Escola> getSchools() {
        return escolaRepository.findAll();
    }

    public List<String> completeTextStates(String query) {
        return students.stream()
                .map(Aluno::getMunicipio)
                .filter((String state) -> state.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> completeTextDistricts(String query) {
        return students.stream().map(Aluno::getDistrito)
                .filter((String d) -> d.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> completeTextNeighbourhoods(String query) {
        return students.stream().map(Aluno::getBairro)
                .filter((String n) -> n.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

}
