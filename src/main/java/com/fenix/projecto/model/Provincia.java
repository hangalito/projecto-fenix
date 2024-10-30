package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author Bartolomeu Hangalo
 */
@Entity
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findByCodigo", query = "SELECT p FROM Provincia p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Provincia.findByNome", query = "SELECT p FROM Provincia p WHERE p.nome = :nome")})
public class Provincia implements Serializable, Comparable<Provincia> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_provincia")
    private Integer codigo;

    @Size(max = 20)
    @Column(name = "nome_provincia", nullable = false)
    private String nome;

    public Provincia() {
    }

    public Provincia(Integer codigoProvincia) {
        this.codigo = codigoProvincia;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Provincia{" + "codigo=" + codigo + ", nome=" + nome + '}';
    }

    @Override
    public int compareTo(Provincia o) {
        return nome.compareTo(o.nome);
    }

}
