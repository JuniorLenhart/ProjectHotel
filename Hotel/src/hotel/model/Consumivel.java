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
@Table(name = "consumivel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumivel.findAll", query = "SELECT c FROM Consumivel c")
    , @NamedQuery(name = "Consumivel.findByCodConsumivel", query = "SELECT c FROM Consumivel c WHERE c.codConsumivel = :codConsumivel")
    , @NamedQuery(name = "Consumivel.findByNomConsumivel", query = "SELECT c FROM Consumivel c WHERE c.nomConsumivel = :nomConsumivel")
    , @NamedQuery(name = "Consumivel.findByDesConsumivel", query = "SELECT c FROM Consumivel c WHERE c.desConsumivel = :desConsumivel")
    , @NamedQuery(name = "Consumivel.findByVlrConsumivel", query = "SELECT c FROM Consumivel c WHERE c.vlrConsumivel = :vlrConsumivel")
    , @NamedQuery(name = "Consumivel.findByTipConsumivel", query = "SELECT c FROM Consumivel c WHERE c.tipConsumivel = :tipConsumivel")
    , @NamedQuery(name = "Consumivel.findByIndSituacao", query = "SELECT c FROM Consumivel c WHERE c.indSituacao = :indSituacao")})
public class Consumivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_consumivel")
    private Integer codConsumivel;
    @Basic(optional = false)
    @Column(name = "nom_consumivel")
    private String nomConsumivel;
    @Basic(optional = false)
    @Column(name = "des_consumivel")
    private String desConsumivel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "vlr_consumivel")
    private BigDecimal vlrConsumivel;
    @Basic(optional = false)
    @Column(name = "tip_consumivel")
    private String tipConsumivel;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codConsumivel")
    private Collection<LocacaoConsumivel> locacaoConsumivelCollection;

    public Consumivel() {
    }

    public Consumivel(Integer codConsumivel) {
        this.codConsumivel = codConsumivel;
    }

    public Consumivel(Integer codConsumivel, String nomConsumivel, String desConsumivel, BigDecimal vlrConsumivel, String tipConsumivel, String indSituacao) {
        this.codConsumivel = codConsumivel;
        this.nomConsumivel = nomConsumivel;
        this.desConsumivel = desConsumivel;
        this.vlrConsumivel = vlrConsumivel;
        this.tipConsumivel = tipConsumivel;
        this.indSituacao = indSituacao;
    }

    public Integer getCodConsumivel() {
        return codConsumivel;
    }

    public void setCodConsumivel(Integer codConsumivel) {
        this.codConsumivel = codConsumivel;
    }

    public String getNomConsumivel() {
        return nomConsumivel;
    }

    public void setNomConsumivel(String nomConsumivel) {
        this.nomConsumivel = nomConsumivel;
    }

    public String getDesConsumivel() {
        return desConsumivel;
    }

    public void setDesConsumivel(String desConsumivel) {
        this.desConsumivel = desConsumivel;
    }

    public BigDecimal getVlrConsumivel() {
        return vlrConsumivel;
    }

    public void setVlrConsumivel(BigDecimal vlrConsumivel) {
        this.vlrConsumivel = vlrConsumivel;
    }

    public String getTipConsumivel() {
        return tipConsumivel;
    }

    public void setTipConsumivel(String tipConsumivel) {
        this.tipConsumivel = tipConsumivel;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public Collection<LocacaoConsumivel> getLocacaoConsumivelCollection() {
        return locacaoConsumivelCollection;
    }

    public void setLocacaoConsumivelCollection(Collection<LocacaoConsumivel> locacaoConsumivelCollection) {
        this.locacaoConsumivelCollection = locacaoConsumivelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codConsumivel != null ? codConsumivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumivel)) {
            return false;
        }
        Consumivel other = (Consumivel) object;
        if ((this.codConsumivel == null && other.codConsumivel != null) || (this.codConsumivel != null && !this.codConsumivel.equals(other.codConsumivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Consumivel[ codConsumivel=" + codConsumivel + " ]";
    }
    
}
