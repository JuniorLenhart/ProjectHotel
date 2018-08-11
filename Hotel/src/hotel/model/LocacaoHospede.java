/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
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
@Table(name = "locacao_hospede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocacaoHospede.findAll", query = "SELECT l FROM LocacaoHospede l")
    , @NamedQuery(name = "LocacaoHospede.findByCodLocacaoHospede", query = "SELECT l FROM LocacaoHospede l WHERE l.codLocacaoHospede = :codLocacaoHospede")
    , @NamedQuery(name = "LocacaoHospede.findByIndResponsavel", query = "SELECT l FROM LocacaoHospede l WHERE l.indResponsavel = :indResponsavel")})
public class LocacaoHospede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_locacao_hospede")
    private Integer codLocacaoHospede;
    @Basic(optional = false)
    @Column(name = "ind_responsavel")
    private String indResponsavel;
    @JoinColumn(name = "cod_locacao", referencedColumnName = "cod_locacao")
    @ManyToOne(optional = false)
    private Locacao codLocacao;
    @JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa")
    @ManyToOne(optional = false)
    private Pessoa codPessoa;

    public LocacaoHospede() {
    }

    public LocacaoHospede(Integer codLocacaoHospede) {
        this.codLocacaoHospede = codLocacaoHospede;
    }

    public LocacaoHospede(Integer codLocacaoHospede, String indResponsavel) {
        this.codLocacaoHospede = codLocacaoHospede;
        this.indResponsavel = indResponsavel;
    }

    public Integer getCodLocacaoHospede() {
        return codLocacaoHospede;
    }

    public void setCodLocacaoHospede(Integer codLocacaoHospede) {
        this.codLocacaoHospede = codLocacaoHospede;
    }

    public String getIndResponsavel() {
        return indResponsavel;
    }

    public void setIndResponsavel(String indResponsavel) {
        this.indResponsavel = indResponsavel;
    }

    public Locacao getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(Locacao codLocacao) {
        this.codLocacao = codLocacao;
    }

    public Pessoa getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Pessoa codPessoa) {
        this.codPessoa = codPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLocacaoHospede != null ? codLocacaoHospede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocacaoHospede)) {
            return false;
        }
        LocacaoHospede other = (LocacaoHospede) object;
        if ((this.codLocacaoHospede == null && other.codLocacaoHospede != null) || (this.codLocacaoHospede != null && !this.codLocacaoHospede.equals(other.codLocacaoHospede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.LocacaoHospede[ codLocacaoHospede=" + codLocacaoHospede + " ]";
    }
    
}
