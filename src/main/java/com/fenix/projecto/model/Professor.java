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
@Table(name = "professor")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p"),
        @NamedQuery(name = "Professor.findByCodgo", query = "SELECT p FROM Professor p WHERE p.codigo = :codigo"),
        @NamedQuery(name = "Professor.findByNome", query = "SELECT p FROM Professor p WHERE p.nome = :nome"),
        @NamedQuery(name = "Professor.findBySobrenome", query = "SELECT p FROM Professor p WHERE p.sobrenome = :sobrenome"),
        @NamedQuery(name = "Professor.findByDataNasc", query = "SELECT p FROM Professor p WHERE p.dataDeNascimento = :dataDeNascimento"),
        @NamedQuery(name = "Professor.findByEndereco", query = "SELECT p FROM Professor p WHERE p.endereco = :endereco"),
        @NamedQuery(name = "Professor.findByBairro", query = "SELECT p FROM Professor p WHERE p.bairro = :bairro"),
        @NamedQuery(name = "Professor.findByDistrito", query = "SELECT p FROM Professor p WHERE p.distrito = :distrito"),
        @NamedQuery(name = "Professor.findByMunicipio", query = "SELECT p FROM Professor p WHERE p.municipio = :municipio")})
public class Professor implements Serializable, Comparable<Professor> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codgo_professor")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "nome_professor")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "sobrenome_professor")
    private String sobrenome;

    @Column(name = "data_nasc_professor")
    @Temporal(TemporalType.DATE)
    private Date dataDeNascimento;

    @Size(max = 30)
    @Column(name = "endereco_professor")
    private String endereco;

    @Size(max = 30)
    @Column(name = "bairro_professor")
    private String bairro;

    @Size(max = 30)
    @Column(name = "distrito_professor")
    private String distrito;

    @Size(max = 30)
    @Column(name = "municipio_professor")
    private String municipio;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Provincia provincia;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_professor", referencedColumnName = "codigo_professor")
    private List<Turma> turmas;

    public Professor() {
    }

    public Professor(Integer codigo) {
        this.codigo = codigo;
    }

    public Professor(Integer codigo, String nome, String sobrenome) {
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Professor{" + "codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataDeNascimento=" + dataDeNascimento + ", endereco=" + endereco + ", bairro=" + bairro + ", distrito=" + distrito + ", municipio=" + municipio + ", provincia=" + provincia + '}';
    }

    @Override
    public int compareTo(Professor o) {
        return nome.compareTo(o.nome);
    }

}
