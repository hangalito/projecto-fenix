package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "turma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findByCode", query = "SELECT c FROM Class c WHERE c.code = :code")
})
public class Class implements Serializable, Comparable<Class> {

    @Serial
    private static final long serialVersionUID = 2L;

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

    public Class() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.code);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.startDate);
        hash = 29 * hash + Objects.hashCode(this.endDate);
        hash = 29 * hash + Objects.hashCode(this.complete);
        hash = 29 * hash + Objects.hashCode(this.teacher);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.complete, other.complete)) {
            return false;
        }
        return Objects.equals(this.teacher, other.teacher);
    }

    @Override
    public String toString() {
        return "Class{" + "code=" + code + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", complete=" + complete + ", teacher=" + teacher + '}';
    }

    @Override
    public int compareTo(Class o) {
        return this.startDate.compareTo(o.startDate);
    }

}
