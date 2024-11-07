package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "turma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findByCode", query = "SELECT c FROM Class c WHERE c.code = :code")
})
public class Class implements Serializable, Comparable<Class> {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_turma")
    private Integer code;

    @Size(max = 45)
    @Column(name = "nome_turma")
    private String name;

    @Column(name = "data_inicio_turma")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "concluida")
    private Boolean complete;

    @JoinColumn(name = "codigo_professor", referencedColumnName = "codigo_professor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
        name = "turma_aluno",
        joinColumns = @JoinColumn(name = "codigo_turma"),
        inverseJoinColumns = @JoinColumn(name = "codigo_aluno")
    )
    private List<Student> students;

    public Class() {
    }

    //<editor-fold desc="Getters and Setters">
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    //</editor-fold>

    //<editor-fold desc="HasCode, Equals and other implementations">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.code);
        hash = 83 * hash + Objects.hashCode(this.startDate);
        hash = 83 * hash + Objects.hashCode(this.teacher);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Class other = (Class) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        return Objects.equals(this.teacher, other.teacher);
    }

    @Override
    public int compareTo(Class o) {
        return startDate.compareTo(o.startDate);
    }
    //</editor-fold>

}
