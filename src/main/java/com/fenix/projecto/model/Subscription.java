package com.fenix.projecto.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "inscricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscription.findAll", query = "SELECT i FROM Subscription i"),
    @NamedQuery(name = "Subscription.findByDate", query = "SELECT s FROM Subscription s WHERE s.date = :date"),
    @NamedQuery(name = "Subscription.findPendingPayments", query = "SELECT s FROM Subscription s WHERE s.pending = true")
})
public class Subscription implements Serializable, Comparable<Subscription> {

    @Serial
    private static final long serialVersionUID = 2L;

    @EmbeddedId
    private SubscriptionPK subscriptionPk;

    @Column(name = "data_inscricao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "valor_pago")
    private Double payment;

    @Column(name = "pagamento_pendente")
    private Boolean pending;

    @JoinColumn(name = "codigo_aluno", referencedColumnName = "codigo_aluno", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Student student;

    @JoinColumn(name = "codigo_curso", referencedColumnName = "codigo_curso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "codigo_turma", referencedColumnName = "codigo_turma")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Class classRoom;

    public Subscription() {
    }

    //<editor-fold desc="Getters and Setters">
    public SubscriptionPK getSubscriptionPK() {
        return subscriptionPk;
    }

    public void setSubscriptionPK(SubscriptionPK inscricaoPK) {
        this.subscriptionPk = inscricaoPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Class getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(Class classRoom) {
        this.classRoom = classRoom;
    }
    //</editor-fold>

    //<editor-fold desc="HashCode, Equals, toString, and other implementations">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.subscriptionPk);
        hash = 71 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subscription other = (Subscription) obj;
        if (!Objects.equals(this.subscriptionPk, other.subscriptionPk)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

    @Override
    public String toString() {
        return "Subscription{" + "subscriptionPk=" + subscriptionPk + ", date=" + date + ", payment=" + payment + ", pending=" + pending + ", student=" + student + ", course=" + course + ", classRoom=" + classRoom + '}';
    }

    @Override
    public int compareTo(Subscription o) {
        return date.compareTo(o.date);
    }
    //</editor-fold>

}
