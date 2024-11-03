package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "professor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t WHERE t.deleted = false"),
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

    @Column(name = "eliminado")
    private Boolean deleted;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;

    @JoinColumn(name = "codigo_professor", referencedColumnName = "codigo_professor")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Contact> contacts;

    public Teacher() {
    }

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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        return !((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code)));
    }

    @Override
    public String toString() {
        return "Teacher{" + "code=" + code + ", name=" + name + ", surname=" + surname + ", birthdate=" + birthdate + ", address=" + address + ", neighbourhood=" + neighbourhood + ", district=" + district + ", state=" + state + ", deleted=" + deleted + ", province=" + province.getName() + '}';
    }

    @Override
    public int compareTo(Teacher o) {
        return name.compareTo(o.name);
    }

}
