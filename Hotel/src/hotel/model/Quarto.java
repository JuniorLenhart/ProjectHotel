/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "quarto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quarto.findAll", query = "SELECT q FROM Quarto q")
    , @NamedQuery(name = "Quarto.findByCodQuarto", query = "SELECT q FROM Quarto q WHERE q.codQuarto = :codQuarto")
    , @NamedQuery(name = "Quarto.findByNumQuarto", query = "SELECT q FROM Quarto q WHERE q.numQuarto = :numQuarto")
    , @NamedQuery(name = "Quarto.findByVlrQuarto", query = "SELECT q FROM Quarto q WHERE q.vlrQuarto = :vlrQuarto")
    , @NamedQuery(name = "Quarto.findByIndSituacao", query = "SELECT q FROM Quarto q WHERE q.indSituacao = :indSituacao")})
public class Quarto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_quarto")
    private Integer codQuarto;
    @Basic(optional = false)
    @Column(name = "num_quarto")
    private String numQuarto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "vlr_quarto")
    private BigDecimal vlrQuarto;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @JoinTable(name = "quarto_tipo_cama", joinColumns = {
        @JoinColumn(name = "cod_quarto", referencedColumnName = "cod_quarto")}, inverseJoinColumns = {
        @JoinColumn(name = "cod_tipo_cama", referencedColumnName = "cod_tipo_cama")})
    @ManyToMany
    private Collection<TipoCama> tipoCamaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codQuarto")
    private Collection<Locacao> locacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codQuarto")
    private Collection<Reserva> reservaCollection;

    public Quarto() {
    }

    public Quarto(Integer codQuarto) {
        this.codQuarto = codQuarto;
    }

    public Quarto(Integer codQuarto, String numQuarto, BigDecimal vlrQuarto, String indSituacao) {
        this.codQuarto = codQuarto;
        this.numQuarto = numQuarto;
        this.vlrQuarto = vlrQuarto;
        this.indSituacao = indSituacao;
    }

    public Integer getCodQuarto() {
        return codQuarto;
    }

    public void setCodQuarto(Integer codQuarto) {
        this.codQuarto = codQuarto;
    }

    public String getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(String numQuarto) {
        this.numQuarto = numQuarto;
    }

    public BigDecimal getVlrQuarto() {
        return vlrQuarto;
    }

    public void setVlrQuarto(BigDecimal vlrQuarto) {
        this.vlrQuarto = vlrQuarto;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public Collection<TipoCama> getTipoCamaCollection() {
        return tipoCamaCollection;
    }

    public void setTipoCamaCollection(Collection<TipoCama> tipoCamaCollection) {
        this.tipoCamaCollection = tipoCamaCollection;
    }

    @XmlTransient
    public Collection<Locacao> getLocacaoCollection() {
        return locacaoCollection;
    }

    public void setLocacaoCollection(Collection<Locacao> locacaoCollection) {
        this.locacaoCollection = locacaoCollection;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codQuarto != null ? codQuarto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.codQuarto == null && other.codQuarto != null) || (this.codQuarto != null && !this.codQuarto.equals(other.codQuarto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Quarto[ codQuarto=" + codQuarto + " ]";
    }
    
}
