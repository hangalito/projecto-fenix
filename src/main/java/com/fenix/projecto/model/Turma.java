package com.fenix.projecto.model;

import jakarta.persistence.*;
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
        @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t"),
        @NamedQuery(name = "Turma.findByCodigo", query = "SELECT t FROM Turma t WHERE t.codigo = :codigo")
})
public class Turma implements Serializable, Comparable<Turma> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_turma")
    private Integer codigo;

    @Size(max = 45)
    @Column(name = "nome_turma")
    private String nome;

    @Column(name = "data_inicio_turma")
    @Temporal(TemporalType.DATE)
    private Date dataDeInicio;

    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataDeTermino;

    @Column(name = "concluida")
    private Boolean concluida;

    @JoinColumn(name = "codigo_professor", referencedColumnName = "codgo_professor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Professor professor;

    public Turma() {
    }

    public Turma(Integer codigoTurma) {
        this.codigo = codigoTurma;
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

    public Date getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(Date dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public Date getDataDeTermino() {
        return dataDeTermino;
    }

    public void setDataDeTermino(Date dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Turma{" + "codigo=" + codigo + ", nome=" + nome + ", dataDeInicio=" + dataDeInicio + ", dataDeTermino=" + dataDeTermino + ", concluida=" + concluida + ", professor=" + professor + '}';
    }

    @Override
    public int compareTo(Turma o) {
        return this.dataDeInicio.compareTo(o.dataDeInicio);
    }

}
