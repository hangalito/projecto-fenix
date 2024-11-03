package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u WHERE u.deleted = false"),
    @NamedQuery(name = "User.findByCode", query = "SELECT u FROM User u WHERE u.code = :code AND u.deleted = false"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username AND u.deleted = false"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email AND u.deleted = false"),
    @NamedQuery(name = "User.findAdmins", query = "SELECT u FROM User u WHERE u.admin = true AND u.deleted = false"),
    @NamedQuery(name = "User.findViewers", query = "SELECT u FROM User u  WHERE u.viewer = true AND u.deleted = false")})
public class User implements Serializable, Comparable<User> {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_usuario")
    private Integer code;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "nome_usuario")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "sobrenome_usuario")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    private String username;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email inválido")
    @Size(max = 255)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    private String password;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "admin")
    private Boolean admin;

    @Column(name = "viewer")
    private Boolean viewer;

    @Column(name = "eliminado")
    private Boolean deleted;

    public User() {
    }

    public User(Integer codigoUser) {
        this.code = codigoUser;
    }

    public User(Integer codigoUser, String nomeUser, String sobrenomeUser, String username, String password) {
        this.code = codigoUser;
        this.name = nomeUser;
        this.surname = sobrenomeUser;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getViewer() {
        return viewer;
    }

    public void setViewer(Boolean viewer) {
        this.viewer = viewer;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code)));
    }

    @Override
    public String toString() {
        return "User{" + "code=" + code + ", name=" + name + ", surname=" + surname + ", username=" + username + ", email=" + email + ", password=" + password + ", createTime=" + createTime + ", admin=" + admin + ", viewer=" + viewer + ", deleted=" + deleted + '}';
    }

    @Override
    public int compareTo(User o) {
        return name.compareTo(o.name);
    }

}
