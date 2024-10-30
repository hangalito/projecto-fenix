package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCodigo", query = "SELECT c FROM Curso c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Curso.findByNome", query = "SELECT c FROM Curso c WHERE c.nome = :nome"),
    @NamedQuery(name = "Curso.findByPreco", query = "SELECT c FROM Curso c WHERE c.preco = :preco")})
public class Curso implements Serializable, Comparable<Curso> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_curso")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nome_curso", unique = true)
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_curso")
    private Double preco;

    @Column(name = "eliminado")
    private Boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Inscricao> inscricoes;

    public Curso() {
    }

    public Curso(Integer codigoCurso) {
        this.codigo = codigoCurso;
    }

    public Curso(Integer codigoCurso, String nomeCurso, double precoCurso) {
        this.codigo = codigoCurso;
        this.nome = nomeCurso;
        this.preco = precoCurso;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + ", inscricoes=" + inscricoes + '}';
    }

    @Override
    public int compareTo(Curso o) {
        return preco.compareTo(o.preco);
    }

}
