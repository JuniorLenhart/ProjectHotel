package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_forma_pgto")
    private Integer codFormaPgto;

    @Column(name = "des_forma_pgto")
    private String desFormaPgto;

    @Column(name = "ind_situacao")
    private String indSituacao;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFormaPgto != null ? codFormaPgto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
    
    public String auditoriaFormat() {
        return "ID: "+codFormaPgto+" Descrição: "+desFormaPgto+" Situação: "+indSituacao;
    }
}
