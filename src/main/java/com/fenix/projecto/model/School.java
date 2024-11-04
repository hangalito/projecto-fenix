package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "escola")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s WHERE s.deleted = false"),
    @NamedQuery(name = "School.findByCode", query = "SELECT s FROM School s WHERE s.code = :code and s.deleted = false"),
    @NamedQuery(name = "School.findByName", query = "SELECT s FROM School s WHERE s.name = :name and s.deleted = false"),
    @NamedQuery(name = "School.findAllDeleted", query = "SELECT s FROM School s WHERE s.deleted = true")
})
public class School implements Serializable, Comparable<School> {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_escola")
    private Integer code;

    @Size(max = 70)
    @Column(name = "nome_escola", nullable = false, unique = true)
    private String name;

    @Size(max = 45)
    @Column(name = "endereco_escola")
    private String address;

    @Size(max = 45)
    @Column(name = "bairro_escola")
    private String neighbourhood;

    @Size(max = 45)
    @Column(name = "distrito_escola")
    private String district;

    @Size(max = 45)
    @Column(name = "municipio_escola")
    private String state;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Province province;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    @JoinColumn(name = "codigo_escola", referencedColumnName = "codigo_escola")
    private List<Student> students;

    @Column(name = "eliminada", nullable = false)
    private Boolean deleted;

    public School() {
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.code);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.neighbourhood);
        hash = 89 * hash + Objects.hashCode(this.district);
        hash = 89 * hash + Objects.hashCode(this.state);
        hash = 89 * hash + Objects.hashCode(this.province);
        hash = 89 * hash + Objects.hashCode(this.deleted);
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
        final School other = (School) obj;
        if (!Objects.equals(this.name, other.name)) {
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
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        return Objects.equals(this.deleted, other.deleted);
    }

    @Override
    public String toString() {
        return "School{" + "code=" + code + ", name=" + name + ", address=" + address + ", neighbourhood=" + neighbourhood + ", district=" + district + ", state=" + state + ", province=" + province + ", deleted=" + deleted + '}';
    }

    @Override
    public int compareTo(School o) {
        return name.compareTo(o.name);
    }

}
