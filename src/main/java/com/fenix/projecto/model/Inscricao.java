package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Bartolomeu Hangalo
 */
@Entity
@Table(name = "inscricao")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Inscricao.findAll", query = "SELECT i FROM Inscricao i"),
        @NamedQuery(name = "Inscricao.findByCodigoAluno", query = "SELECT i FROM Inscricao i WHERE i.inscricaoPK.codigoAluno = :codigoAluno"),
        @NamedQuery(name = "Inscricao.findByCodigoCurso", query = "SELECT i FROM Inscricao i WHERE i.inscricaoPK.codigoCurso = :codigoCurso"),
        @NamedQuery(name = "Inscricao.findByDataDeInscricao", query = "SELECT i FROM Inscricao i WHERE i.data = :data"),
        @NamedQuery(name = "Inscricao.findByValorPago", query = "SELECT i FROM Inscricao i WHERE i.valorPago = :valorPago"),
        @NamedQuery(name = "Inscricao.findPagamentoPendente", query = "SELECT i FROM Inscricao i WHERE i.pendente = :pendente")})
public class Inscricao implements Serializable, Comparable<Inscricao> {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private InscricaoPK inscricaoPK;

    @Column(name = "data_inscricao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "pagamento_pendente")
    private Boolean pendente;

    @JoinColumn(name = "codigo_aluno", referencedColumnName = "codigo_aluno", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aluno aluno;

    @JoinColumn(name = "codigo_curso", referencedColumnName = "codigo_curso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;

    @JoinColumn(name = "codigo_turma", referencedColumnName = "codigo_turma")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Turma turma;

    public Inscricao() {
    }

    public Inscricao(InscricaoPK inscricaoPK) {
        this.inscricaoPK = inscricaoPK;
    }

    public Inscricao(int codigoAluno, int codigoCurso) {
        this.inscricaoPK = new InscricaoPK(codigoAluno, codigoCurso);
    }

    public InscricaoPK getInscricaoPK() {
        return inscricaoPK;
    }

    public void setInscricaoPK(InscricaoPK inscricaoPK) {
        this.inscricaoPK = inscricaoPK;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Boolean getPendente() {
        return pendente;
    }

    public void setPendente(Boolean pendente) {
        this.pendente = pendente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscricaoPK != null ? inscricaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Inscricao)) {
            return false;
        }
        Inscricao other = (Inscricao) object;
        return !((this.inscricaoPK == null && other.inscricaoPK != null) || (this.inscricaoPK != null && !this.inscricaoPK.equals(other.inscricaoPK)));
    }

    @Override
    public String toString() {
        return "Inscricao{" + "inscricaoPK=" + inscricaoPK + ", data=" + data + ", valorPago=" + valorPago + ", pendente=" + pendente + ", aluno=" + aluno + ", curso=" + curso + ", turma=" + turma + '}';
    }

    @Override
    public int compareTo(Inscricao o) {
        return data.compareTo(o.data);
    }


}
