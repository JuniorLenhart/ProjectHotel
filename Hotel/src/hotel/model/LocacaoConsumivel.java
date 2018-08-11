/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "locacao_consumivel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocacaoConsumivel.findAll", query = "SELECT l FROM LocacaoConsumivel l")
    , @NamedQuery(name = "LocacaoConsumivel.findByCodLocacaoConsumivel", query = "SELECT l FROM LocacaoConsumivel l WHERE l.codLocacaoConsumivel = :codLocacaoConsumivel")
    , @NamedQuery(name = "LocacaoConsumivel.findByQtdConsumivel", query = "SELECT l FROM LocacaoConsumivel l WHERE l.qtdConsumivel = :qtdConsumivel")
    , @NamedQuery(name = "LocacaoConsumivel.findByVlrConsumivel", query = "SELECT l FROM LocacaoConsumivel l WHERE l.vlrConsumivel = :vlrConsumivel")})
public class LocacaoConsumivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_locacao_consumivel")
    private Integer codLocacaoConsumivel;
    @Basic(optional = false)
    @Column(name = "qtd_consumivel")
    private int qtdConsumivel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "vlr_consumivel")
    private BigDecimal vlrConsumivel;
    @JoinColumn(name = "cod_consumivel", referencedColumnName = "cod_consumivel")
    @ManyToOne(optional = false)
    private Consumivel codConsumivel;
    @JoinColumn(name = "cod_locacao", referencedColumnName = "cod_locacao")
    @ManyToOne(optional = false)
    private Locacao codLocacao;

    public LocacaoConsumivel() {
    }

    public LocacaoConsumivel(Integer codLocacaoConsumivel) {
        this.codLocacaoConsumivel = codLocacaoConsumivel;
    }

    public LocacaoConsumivel(Integer codLocacaoConsumivel, int qtdConsumivel, BigDecimal vlrConsumivel) {
        this.codLocacaoConsumivel = codLocacaoConsumivel;
        this.qtdConsumivel = qtdConsumivel;
        this.vlrConsumivel = vlrConsumivel;
    }

    public Integer getCodLocacaoConsumivel() {
        return codLocacaoConsumivel;
    }

    public void setCodLocacaoConsumivel(Integer codLocacaoConsumivel) {
        this.codLocacaoConsumivel = codLocacaoConsumivel;
    }

    public int getQtdConsumivel() {
        return qtdConsumivel;
    }

    public void setQtdConsumivel(int qtdConsumivel) {
        this.qtdConsumivel = qtdConsumivel;
    }

    public BigDecimal getVlrConsumivel() {
        return vlrConsumivel;
    }

    public void setVlrConsumivel(BigDecimal vlrConsumivel) {
        this.vlrConsumivel = vlrConsumivel;
    }

    public Consumivel getCodConsumivel() {
        return codConsumivel;
    }

    public void setCodConsumivel(Consumivel codConsumivel) {
        this.codConsumivel = codConsumivel;
    }

    public Locacao getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(Locacao codLocacao) {
        this.codLocacao = codLocacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLocacaoConsumivel != null ? codLocacaoConsumivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocacaoConsumivel)) {
            return false;
        }
        LocacaoConsumivel other = (LocacaoConsumivel) object;
        if ((this.codLocacaoConsumivel == null && other.codLocacaoConsumivel != null) || (this.codLocacaoConsumivel != null && !this.codLocacaoConsumivel.equals(other.codLocacaoConsumivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.LocacaoConsumivel[ codLocacaoConsumivel=" + codLocacaoConsumivel + " ]";
    }
    
}
