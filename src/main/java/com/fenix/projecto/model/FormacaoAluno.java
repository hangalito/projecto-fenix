package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Bartolomeu Hangalo
 */
@Entity
@Table(name = "formacao_aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormacaoAluno.findAll", query = "SELECT f FROM FormacaoAluno f WHERE f.deleted = false"),
    @NamedQuery(name = "FormacaoAluno.findByCodigo", query = "SELECT f FROM FormacaoAluno f WHERE f.codigo = :codigo and f.deleted = false"),
    @NamedQuery(name = "FormacaoAluno.findByNome", query = "SELECT f FROM FormacaoAluno f WHERE f.nome = :nome and f.deleted = false")})
public class FormacaoAluno implements Serializable, Comparable<FormacaoAluno> {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_formacao")
    private Integer codigo;

    @Size(max = 45)
    @Column(name = "nome_formacao")
    private String nome;

    @Column(name = "eliminado")
    private Boolean deleted;

    public FormacaoAluno() {
    }

    public FormacaoAluno(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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

    public Boolean getDeleted() {
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
        if (!(object instanceof FormacaoAluno)) {
            return false;
        }
        FormacaoAluno other = (FormacaoAluno) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "FormacaoAluno{" + "codigo=" + codigo + ", nome=" + nome + '}';
    }

    @Override
    public int compareTo(FormacaoAluno o) {
        return nome.compareTo(o.nome);
    }

}
