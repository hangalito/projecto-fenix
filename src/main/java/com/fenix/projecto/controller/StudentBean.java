package com.fenix.projecto.controller;

import com.fenix.projecto.model.Student;
import com.fenix.projecto.model.School;
import com.fenix.projecto.repository.StudentRepository;
import com.fenix.projecto.repository.SchoolRepository;
import com.fenix.projecto.util.DateTimeConverter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "studentBean")
@ViewScoped
public class StudentBean implements Serializable {

    @Inject
    private StudentRepository repo;
    @Inject
    private SchoolRepository escolaRepository;

    private List<Student> students;
    private List<Student> selectedStudents;
    private List<Student> deletedStudents;
    private Student selectedStudent;
    private String birthdate;

    @PostConstruct
    public void init() {
        students = repo.findAll();
    }

    //<editor-fold desc="Getters and Setters">
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public List<Student> getDeletedStudents() {
        return deletedStudents;
    }

    public void setDeletedStudents(List<Student> deletedStudents) {
        this.deletedStudents = deletedStudents;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    //</editor-fold>

    public void openNew() {
        selectedStudent = new Student();
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
        selectedStudent.setBirthdate(DateTimeConverter.convertToDate(birthdate));
        if (selectedStudent.getCode() == null) {
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
        if (selectedStudents != null) {
            selectedStudents.remove(selectedStudent);
        }
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

    public List<School> getSchools() {
        return escolaRepository.findAll();
    }

    public void loadDeletedStudents() {
        deletedStudents = repo.findAllDeleted();
        selectedStudent = null;
        selectedStudents.clear();
    }

    public void restoreStudent() {
        selectedStudents.forEach(repo::restore);
        selectedStudents.clear();
        selectedStudents = null;
        loadStudents();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aluno restaurado com sucesso"));
        PrimeFaces.current().executeScript("PF('manageDeletedStudentsDialog').hide()");
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-students", ":main:delete-student-button");
    }

    public String getRestoreMessage() {
        if (hasSelectedStudents()) {
            return getDeleteButtonMessage();
        }
        return "Restaurar";
    }

    //<editor-fold desc="Auto-complete text methods">
    public List<String> completeTextStates(String query) {
        return students.stream()
                .map(Student::getState)
                .filter((String state) -> state.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> completeTextDistricts(String query) {
        return students.stream().map(Student::getDistrict)
                .filter((String d) -> d.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> completeTextNeighbourhoods(String query) {
        return students.stream().map(Student::getNeighbourhood)
                .filter((String n) -> n.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    //</editor-fold>
}
