package com.fenix.projecto.controller;

import com.fenix.projecto.model.Course;
import com.fenix.projecto.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import org.primefaces.PrimeFaces;

@Named(value = "courseBean")
@SessionScoped
public class CourseBean implements Serializable {

    @Inject
    private CourseRepository repo;
    private List<Course> courses;
    private Course selectedCourse;
    private List<Course> selectedCourses;

    @PostConstruct
    public void init() {
        courses = repo.findAll();
    }

    public CourseRepository getRepo() {
        return repo;
    }

    public void setRepo(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public List<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Course> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public void openNew() {
        this.selectedCourse = new Course();
    }

    public void refreshCourses() {
        loadCourses();
        PrimeFaces.current().ajax().update(":main:dt-courses");
    }

    private void loadCourses() {
        this.courses = repo.findAll();
    }

    public void saveCourse() {
        if (selectedCourse.getCode() == null) {
            repo.save(selectedCourse);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Curso adicionado"));
        } else {
            repo.update(selectedCourse);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Curso actualizado"));
        }

        loadCourses();
        PrimeFaces.current().executeScript("PF('manageCoursesDialog').hide()");
        PrimeFaces.current().ajax().update(":main:messages", ":main:dt-courses");
    }

    public void deleteCourse() {
        selectedCourses.remove(selectedCourse);
        repo.delete(selectedCourse);
        loadCourses();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Curso eliminado"));
        PrimeFaces.current().ajax().update("main:messages", "main:dt-courses");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedCourses()) {
            int tamanho = selectedCourses.size();
            return tamanho > 1 ? tamanho + " cursos selecionados" : "1 curso selecionado";
        }
        return "Eliminar";
    }

    public boolean hasSelectedCourses() {
        return this.selectedCourses != null && !this.selectedCourses.isEmpty();
    }

    public void deleteSelectedCourses() {
        this.selectedCourses.forEach(repo::delete);
        this.courses.removeAll(this.selectedCourses);
        this.selectedCourses = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cursos removidos"));
        PrimeFaces.current().ajax().update("main:messages", "main:dt-courses");
        PrimeFaces.current().executeScript("PF('dtCourses').clearFilters()");
    }

    public void exportUnavailable() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                "Exportar dados",
                "Esta funcionalidade ainda não está disponivel."));
    }

}
