/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "tipo_cama")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCama.findAll", query = "SELECT t FROM TipoCama t")
    , @NamedQuery(name = "TipoCama.findByCodTipoCama", query = "SELECT t FROM TipoCama t WHERE t.codTipoCama = :codTipoCama")
    , @NamedQuery(name = "TipoCama.findByDesTipoCama", query = "SELECT t FROM TipoCama t WHERE t.desTipoCama = :desTipoCama")
    , @NamedQuery(name = "TipoCama.findByQtdLugarTipoCama", query = "SELECT t FROM TipoCama t WHERE t.qtdLugarTipoCama = :qtdLugarTipoCama")
    , @NamedQuery(name = "TipoCama.findByIndSituacao", query = "SELECT t FROM TipoCama t WHERE t.indSituacao = :indSituacao")})
public class TipoCama implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_tipo_cama")
    private Integer codTipoCama;
    @Basic(optional = false)
    @Column(name = "des_tipo_cama")
    private String desTipoCama;
    @Basic(optional = false)
    @Column(name = "qtd_lugar_tipo_cama")
    private int qtdLugarTipoCama;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @ManyToMany(mappedBy = "tipoCamaCollection")
    private Collection<Quarto> quartoCollection;

    public TipoCama() {
    }

    public TipoCama(Integer codTipoCama) {
        this.codTipoCama = codTipoCama;
    }

    public TipoCama(Integer codTipoCama, String desTipoCama, int qtdLugarTipoCama, String indSituacao) {
        this.codTipoCama = codTipoCama;
        this.desTipoCama = desTipoCama;
        this.qtdLugarTipoCama = qtdLugarTipoCama;
        this.indSituacao = indSituacao;
    }

    public Integer getCodTipoCama() {
        return codTipoCama;
    }

    public void setCodTipoCama(Integer codTipoCama) {
        this.codTipoCama = codTipoCama;
    }

    public String getDesTipoCama() {
        return desTipoCama;
    }

    public void setDesTipoCama(String desTipoCama) {
        this.desTipoCama = desTipoCama;
    }

    public int getQtdLugarTipoCama() {
        return qtdLugarTipoCama;
    }

    public void setQtdLugarTipoCama(int qtdLugarTipoCama) {
        this.qtdLugarTipoCama = qtdLugarTipoCama;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public Collection<Quarto> getQuartoCollection() {
        return quartoCollection;
    }

    public void setQuartoCollection(Collection<Quarto> quartoCollection) {
        this.quartoCollection = quartoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoCama != null ? codTipoCama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCama)) {
            return false;
        }
        TipoCama other = (TipoCama) object;
        if ((this.codTipoCama == null && other.codTipoCama != null) || (this.codTipoCama != null && !this.codTipoCama.equals(other.codTipoCama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.TipoCama[ codTipoCama=" + codTipoCama + " ]";
    }
    
}
