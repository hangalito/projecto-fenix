package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bartolomeu Hangalo
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo"),
        @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
        @NamedQuery(name = "Usuario.findBySobrenome", query = "SELECT u FROM Usuario u WHERE u.sobrenome = :sobrenome"),
        @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
        @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
        @NamedQuery(name = "Usuario.findByCreateTime", query = "SELECT u FROM Usuario u WHERE u.createTime = :createTime"),
        @NamedQuery(name = "Usuario.findAdmins", query = "SELECT u FROM Usuario u WHERE u.admin = true"),
        @NamedQuery(name = "Usuario.findViewers", query = "SELECT u FROM Usuario u  WHERE u.viewer = true")})
public class Usuario implements Serializable, Comparable<Usuario> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_usuario")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "nome_usuario")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "sobrenome_usuario")
    private String sobrenome;
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

    public Usuario() {
    }

    public Usuario(Integer codigoUsuario) {
        this.codigo = codigoUsuario;
    }

    public Usuario(Integer codigoUsuario, String nomeUsuario, String sobrenomeUsuario, String username, String password) {
        this.codigo = codigoUsuario;
        this.nome = nomeUsuario;
        this.sobrenome = sobrenomeUsuario;
        this.username = username;
        this.password = password;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", username=" + username + ", email=" + email + ", password=" + password + ", createTime=" + createTime + ", admin=" + admin + ", viewer=" + viewer + '}';
    }

    @Override
    public int compareTo(Usuario o) {
        return nome.compareTo(o.nome);
    }

}
