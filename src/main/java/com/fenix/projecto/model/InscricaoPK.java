package com.fenix.projecto.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author Bartolomeu Hangalo
 */
@Embeddable
public class InscricaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_aluno")
    private int codigoAluno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_curso")
    private int codigoCurso;

    public InscricaoPK() {
    }

    public InscricaoPK(int codigoAluno, int codigoCurso) {
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;
    }

    public int getCodigoAluno() {
        return codigoAluno;
    }

    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoAluno;
        hash += (int) codigoCurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InscricaoPK)) {
            return false;
        }
        InscricaoPK other = (InscricaoPK) object;
        if (this.codigoAluno != other.codigoAluno) {
            return false;
        }
        return this.codigoCurso == other.codigoCurso;
    }

    @Override
    public String toString() {
        return "com.fenix.projecto.model.InscricaoPK[ codigoAluno=" + codigoAluno + ", codigoCurso=" + codigoCurso + " ]";
    }

}
