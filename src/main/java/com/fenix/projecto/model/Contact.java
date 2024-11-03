package com.fenix.projecto.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contacto_professor", catalog = "projecto_fenix_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findByCodigoProfessor", query = "SELECT c FROM Contact c WHERE c.codigoProfessor = :codigoProfessor"),
    @NamedQuery(name = "Contact.findByTelUnitel", query = "SELECT c FROM Contact c WHERE c.telUnitel = :telUnitel"),
    @NamedQuery(name = "Contact.findByTelAfricell", query = "SELECT c FROM Contact c WHERE c.telAfricell = :telAfricell"),
    @NamedQuery(name = "Contact.findByTelMovicel", query = "SELECT c FROM Contact c WHERE c.telMovicel = :telMovicel"),
    @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email")})
public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_professor")
    private Integer codigoProfessor;

    @Size(max = 16)
    @Column(name = "tel_unitel")
    private String telUnitel;

    @Size(max = 16)
    @Column(name = "tel_africell")
    private String telAfricell;

    @Size(max = 16)
    @Column(name = "tel_movicel")
    private String telMovicel;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    private String email;

    @JoinColumn(name = "codigo_professor", referencedColumnName = "codigo_professor", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Teacher professor;

    public Contact() {
    }

    public Contact(Integer codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public Integer getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(Integer codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public String getTelUnitel() {
        return telUnitel;
    }

    public void setTelUnitel(String telUnitel) {
        this.telUnitel = telUnitel;
    }

    public String getTelAfricell() {
        return telAfricell;
    }

    public void setTelAfricell(String telAfricell) {
        this.telAfricell = telAfricell;
    }

    public String getTelMovicel() {
        return telMovicel;
    }

    public void setTelMovicel(String telMovicel) {
        this.telMovicel = telMovicel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher getProfessor() {
        return professor;
    }

    public void setProfessor(Teacher professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.codigoProfessor);
        hash = 11 * hash + Objects.hashCode(this.telUnitel);
        hash = 11 * hash + Objects.hashCode(this.telAfricell);
        hash = 11 * hash + Objects.hashCode(this.telMovicel);
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.professor);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.telUnitel, other.telUnitel)) {
            return false;
        }
        if (!Objects.equals(this.telAfricell, other.telAfricell)) {
            return false;
        }
        if (!Objects.equals(this.telMovicel, other.telMovicel)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.codigoProfessor, other.codigoProfessor)) {
            return false;
        }
        return Objects.equals(this.professor, other.professor);
    }

    @Override
    public String toString() {
        return "Contact{" + "codigoProfessor=" + codigoProfessor + ", telUnitel=" + telUnitel + ", telAfricell=" + telAfricell + ", telMovicel=" + telMovicel + ", email=" + email + ", professor=" + professor + '}';
    }

}
