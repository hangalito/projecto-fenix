package com.fenix.projecto.controller;

import com.fenix.projecto.model.Curso;
import com.fenix.projecto.repository.CursoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Named(value = "cursoBean")
@SessionScoped
public class CursoBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private CursoRepository repo;
    private List<Curso> courses;
    private Curso selectedCourse;
    private List<Curso> selectedCourses;

    /**
     * Creates a new instance of CursoBean
     */
    public CursoBean() {
    }

    @PostConstruct
    public void init() {
        courses = repo.findAll();
    }

    public CursoRepository getRepo() {
        return repo;
    }

    public void setRepo(CursoRepository repo) {
        this.repo = repo;
    }

    public List<Curso> getCourses() {
        return courses;
    }

    public void setCourses(List<Curso> courses) {
        this.courses = courses;
    }

    public Curso getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Curso selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public List<Curso> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Curso> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public void openNew() {
        this.selectedCourse = new Curso();
    }

    public void refreshCourses() {
        loadCourses();
        PrimeFaces.current().ajax().update(":main:dt-courses");
    }

    private final void loadCourses() {
        this.courses = repo.findAll();
    }

    public void saveCourse() {
        if (selectedCourse.getCodigo() == null) {
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
