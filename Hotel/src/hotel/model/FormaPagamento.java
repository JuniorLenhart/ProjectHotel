/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
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
@Table(name = "forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f")
    , @NamedQuery(name = "FormaPagamento.findByCodFormaPgto", query = "SELECT f FROM FormaPagamento f WHERE f.codFormaPgto = :codFormaPgto")
    , @NamedQuery(name = "FormaPagamento.findByDesFormaPgto", query = "SELECT f FROM FormaPagamento f WHERE f.desFormaPgto = :desFormaPgto")
    , @NamedQuery(name = "FormaPagamento.findByIndSituacao", query = "SELECT f FROM FormaPagamento f WHERE f.indSituacao = :indSituacao")})
public class FormaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_forma_pgto")
    private Integer codFormaPgto;
    @Basic(optional = false)
    @Column(name = "des_forma_pgto")
    private String desFormaPgto;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFormaPgto")
    private Collection<Financeiro> financeiroCollection;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }

    public FormaPagamento(Integer codFormaPgto, String desFormaPgto, String indSituacao) {
        this.codFormaPgto = codFormaPgto;
        this.desFormaPgto = desFormaPgto;
        this.indSituacao = indSituacao;
    }

    public Integer getCodFormaPgto() {
        return codFormaPgto;
    }

    public void setCodFormaPgto(Integer codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }

    public String getDesFormaPgto() {
        return desFormaPgto;
    }

    public void setDesFormaPgto(String desFormaPgto) {
        this.desFormaPgto = desFormaPgto;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public Collection<Financeiro> getFinanceiroCollection() {
        return financeiroCollection;
    }

    public void setFinanceiroCollection(Collection<Financeiro> financeiroCollection) {
        this.financeiroCollection = financeiroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFormaPgto != null ? codFormaPgto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.codFormaPgto == null && other.codFormaPgto != null) || (this.codFormaPgto != null && !this.codFormaPgto.equals(other.codFormaPgto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.FormaPagamento[ codFormaPgto=" + codFormaPgto + " ]";
    }
    
}
