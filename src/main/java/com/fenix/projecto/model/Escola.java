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
@Table(name = "escola")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escola.findAll", query = "SELECT e FROM Escola e WHERE e.deleted = false"),
    @NamedQuery(name = "Escola.findByCodigo", query = "SELECT e FROM Escola e WHERE e.codigo = :codigo and e.deleted = false"),
    @NamedQuery(name = "Escola.findByNome", query = "SELECT e FROM Escola e WHERE e.nome = :nome and e.deleted = false"),
    @NamedQuery(name = "Escola.findByEndereco", query = "SELECT e FROM Escola e WHERE e.endereco = :endereco and e.deleted = false"),
    @NamedQuery(name = "Escola.findByBairro", query = "SELECT e FROM Escola e WHERE e.bairro = :bairro and e.deleted = false"),
    @NamedQuery(name = "Escola.findByDistrito", query = "SELECT e FROM Escola e WHERE e.distrito = :distrito and e.deleted = false"),
    @NamedQuery(name = "Escola.findByMunicipio", query = "SELECT e FROM Escola e WHERE e.municipio = :municipio and e.deleted = false")})
public class Escola implements Serializable, Comparable<Escola> {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_escola")
    private Integer codigo;

    @Size(max = 70)
    @Column(name = "nome_escola", nullable = false, unique = true)
    private String nome;

    @Size(max = 45)
    @Column(name = "endereco_escola")
    private String endereco;

    @Size(max = 45)
    @Column(name = "bairro_escola")
    private String bairro;

    @Size(max = 45)
    @Column(name = "distrito_escola")
    private String distrito;

    @Size(max = 45)
    @Column(name = "municipio_escola")
    private String municipio;

    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_provincia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Provincia provincias;

    @Column(name = "eliminado", nullable = false)
    private Boolean deleted;

    public Escola() {
    }

    public Escola(Integer codigoEscola, String nome) {
        this.codigo = codigoEscola;
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

    public Provincia getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincia provincias) {
        this.provincias = provincias;
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
        if (!(object instanceof Escola)) {
            return false;
        }
        Escola other = (Escola) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Escola{" + "codigo=" + codigo + ", nome=" + nome + ", endereco=" + endereco + ", bairro=" + bairro + ", distrito=" + distrito + ", municipio=" + municipio + ", provincias=" + provincias + '}';
    }

    @Override
    public int compareTo(Escola o) {
        return nome.compareTo(o.nome);
    }

}
