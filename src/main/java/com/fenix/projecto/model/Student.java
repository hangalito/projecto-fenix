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
@Table(name = "aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s where s.deleted = false"),
    @NamedQuery(name = "Student.findByCode", query = "SELECT s FROM Student s WHERE s.code = :code and s.deleted = false"),
    @NamedQuery(name = "Student.findAllDeleted", query = "SELECT s FROM Student s WHERE s.deleted = true")
})
public class Student implements Serializable, Comparable<Student> {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_aluno")
    private Integer code;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "nome_aluno")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "sobrenome_aluno")
    private String surname;

    @Column(name = "data_nasc_aluno")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Size(max = 16)
    @Column(name = "tel_aluno", unique = true)
    private String telephone;

    @Size(max = 70)
    @Column(name = "email_aluno", unique = true)
    private String email;

    @Size(max = 30)
    @Column(name = "endereco_aluno")
    private String address;

    @Size(max = 30)
    @Column(name = "bairro_aluno")
    private String neighbourhood;

    @Size(max = 30)
    @Column(name = "distrito_aluno")
    private String district;

    @Size(max = 30)
    @Column(name = "municipio_aluno")
    private String state;

    @JoinColumn(name = "codigo_escola", referencedColumnName = "codigo_escola")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private School school;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

    @Column(name = "eliminado", nullable = false)
    private Boolean deleted;

    @ManyToMany(mappedBy = "students")  // Relacionamento ManyToMany bidirecional
    private List<Class> classes;

    public Student() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
    //</editor-fold>

    //<editor-fold desc="HashCode, Equals and other implementations">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.code);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.surname);
        hash = 59 * hash + Objects.hashCode(this.birthdate);
        hash = 59 * hash + Objects.hashCode(this.telephone);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.address);
        hash = 59 * hash + Objects.hashCode(this.neighbourhood);
        hash = 59 * hash + Objects.hashCode(this.district);
        hash = 59 * hash + Objects.hashCode(this.state);
        hash = 59 * hash + Objects.hashCode(this.school);
        hash = 59 * hash + Objects.hashCode(this.province);
        hash = 59 * hash + Objects.hashCode(this.subscriptions);
        hash = 59 * hash + Objects.hashCode(this.deleted);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.neighbourhood, other.neighbourhood)) {
            return false;
        }
        if (!Objects.equals(this.district, other.district)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        if (!Objects.equals(this.school, other.school)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        if (!Objects.equals(this.subscriptions, other.subscriptions)) {
            return false;
        }
        return Objects.equals(this.deleted, other.deleted);
    }

    @Override
    public String toString() {
        return "Student{" + "code=" + code + ", name=" + name + ", surname=" + surname + ", birthdate=" + birthdate + ", telephone=" + telephone + ", email=" + email + ", address=" + address + ", neighbourhood=" + neighbourhood + ", district=" + district + ", state=" + state + ", school=" + school + ", province=" + province + ", subscriptions=" + subscriptions + ", deleted=" + deleted + '}';
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }
    //</editor-fold>

}
