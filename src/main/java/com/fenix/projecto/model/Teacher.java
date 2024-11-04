package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "professor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t WHERE NOT t.deleted"),
    @NamedQuery(name = "Teacher.findByCode", query = "SELECT t FROM Teacher t WHERE t.code = :code"),
    @NamedQuery(name = "Teacher.findByName", query = "SELECT t FROM Teacher t WHERE t.name = :name"),
    @NamedQuery(name = "Teacher.findBySurname", query = "SELECT t FROM Teacher t WHERE t.surname = :surname"),
    @NamedQuery(name = "Teacher.findByBirthdate", query = "SELECT t FROM Teacher t WHERE t.birthdate = :birthdate")
})
public class Teacher implements Serializable, Comparable<Teacher> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_professor")
    private Integer code;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "nome_professor")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "sobrenome_professor")
    private String surname;

    @Column(name = "data_nasc_professor")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Size(max = 30)
    @Column(name = "endereco_professor")
    private String address;

    @Size(max = 30)
    @Column(name = "bairro_professor")
    private String neighbourhood;

    @Size(max = 30)
    @Column(name = "distrito_professor")
    private String district;

    @Size(max = 30)
    @Column(name = "municipio_professor")
    private String state;

    @Size(max = 16)
    @Column(name = "tel_unitel_professor")
    private String telUnitel;

    @Size(max = 16)
    @Column(name = "tel_movicel_professor")
    private String telMovicel;

    @Size(max = 16)
    @Column(name = "tel_africell_professor")
    private String telAfricell;

    @Size(max = 70)
    @Column(name = "email_professor")
    private String email;

    @Column(name = "eliminado")
    private Boolean deleted;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;

    public Teacher() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelUnitel() {
        return telUnitel;
    }

    public void setTelUnitel(String telUnitel) {
        this.telUnitel = telUnitel;
    }

    public String getTelMovicel() {
        return telMovicel;
    }

    public void setTelMovicel(String telMovicel) {
        this.telMovicel = telMovicel;
    }

    public String getTelAfricell() {
        return telAfricell;
    }

    public void setTelAfricell(String telAfricell) {
        this.telAfricell = telAfricell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
    //</editor-fold>

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.code);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.birthdate);
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
        final Teacher other = (Teacher) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.birthdate, other.birthdate);
    }

    @Override
    public String toString() {
        return "Teacher{" + "code=" + code + ", name=" + name + ", surname=" + surname + ", birthdate=" + birthdate + ", address=" + address + ", neighbourhood=" + neighbourhood + ", district=" + district + ", state=" + state + ", telUnitel=" + telUnitel + ", telMovicel=" + telMovicel + ", telAfricell=" + telAfricell + ", deleted=" + deleted + ", province=" + province + '}';
    }

    @Override
    public int compareTo(Teacher o) {
        return name.compareTo(o.name);
    }

}
