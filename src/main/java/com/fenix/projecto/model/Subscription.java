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

    @Column(name = "eliminado")
    private Boolean deleted;

    public Subscription() {
    }

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.subscriptionPk);
        hash = 31 * hash + Objects.hashCode(this.date);
        hash = 31 * hash + Objects.hashCode(this.payment);
        hash = 31 * hash + Objects.hashCode(this.pending);
        hash = 31 * hash + Objects.hashCode(this.student);
        hash = 31 * hash + Objects.hashCode(this.course);
        hash = 31 * hash + Objects.hashCode(this.classRoom);
        hash = 31 * hash + Objects.hashCode(this.deleted);
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
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        if (!Objects.equals(this.pending, other.pending)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.classRoom, other.classRoom)) {
            return false;
        }
        return Objects.equals(this.deleted, other.deleted);
    }

    @Override
    public String toString() {
        return "Subscription{" + "date=" + date + ", payment=" + payment + ", pending=" + pending + ", student=" + student + ", course=" + course + ", classRoom=" + classRoom + ", deleted=" + deleted + '}';
    }

    @Override
    public int compareTo(Subscription o) {
        return date.compareTo(o.date);
    }

}
