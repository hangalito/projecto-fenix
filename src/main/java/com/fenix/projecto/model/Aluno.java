package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bartolomeu Hangalo
 */
@Entity
@Table(name = "aluno")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a where a.deleted = false"),
        @NamedQuery(name = "Aluno.findByCodigo", query = "SELECT a FROM Aluno a WHERE a.codigo = :codigo and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByNome", query = "SELECT a FROM Aluno a WHERE a.nome = :nome"),
        @NamedQuery(name = "Aluno.findBySobrenome", query = "SELECT a FROM Aluno a WHERE a.sobrenome = :sobrenome and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByDataNasc", query = "SELECT a FROM Aluno a WHERE a.dataDeNascimento = :dataDeNascimento and  a.deleted = false"),
        @NamedQuery(name = "Aluno.findByTelefone", query = "SELECT a FROM Aluno a WHERE a.telefone = :telefone and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByEmail", query = "SELECT a FROM Aluno a WHERE a.email = :email and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByEndereco", query = "SELECT a FROM Aluno a WHERE a.endereco = :endereco and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByBairro", query = "SELECT a FROM Aluno a WHERE a.bairro = :bairro and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByDistrito", query = "SELECT a FROM Aluno a WHERE a.distrito = :distrito and a.deleted = false"),
        @NamedQuery(name = "Aluno.findByMunicipio", query = "SELECT a FROM Aluno a WHERE a.municipio = :municipio and a.deleted = false")})
public class Aluno implements Serializable, Comparable<Aluno> {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_aluno")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "nome_aluno")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "sobrenome_aluno")
    private String sobrenome;

    @Column(name = "data_nasc_aluno")
    @Temporal(TemporalType.DATE)
    private Date dataDeNascimento;

    @Size(max = 16)
    @Column(name = "tel_aluno", unique = true)
    private String telefone;

    @Size(max = 70)
    @Column(name = "email_aluno", unique = true)
    private String email;

    @Size(max = 30)
    @Column(name = "endereco_aluno")
    private String endereco;

    @Size(max = 30)
    @Column(name = "bairro_aluno")
    private String bairro;

    @Size(max = 30)
    @Column(name = "distrito_aluno")
    private String distrito;

    @Size(max = 30)
    @Column(name = "municipio_aluno")
    private String municipio;

    @JoinColumn(name = "codigo_escola", referencedColumnName = "codigo_escola")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Escola escola;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Provincia provincia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno", fetch = FetchType.LAZY)
    private List<Inscricao> inscricoes;

    @Column(name = "eliminado", nullable = false)
    private Boolean deleted;

    public Aluno() {
    }

    public Aluno(Integer codigoAluno) {
        this.codigo = codigoAluno;
    }

    public Aluno(Integer codigo, String nome, String sobrenome) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
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

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Aluno{" + "codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataDeNascimento=" + dataDeNascimento + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco + ", bairro=" + bairro + ", distrito=" + distrito + ", municipio=" + municipio + ", escola=" + escola + ", provincia=" + provincia + ", inscricoes=" + inscricoes + '}';
    }

    @Override
    public int compareTo(Aluno o) {
        return nome.compareTo(o.nome);
    }

}
