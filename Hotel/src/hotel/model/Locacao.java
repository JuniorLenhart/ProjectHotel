/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "locacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l")
    , @NamedQuery(name = "Locacao.findByCodLocacao", query = "SELECT l FROM Locacao l WHERE l.codLocacao = :codLocacao")
    , @NamedQuery(name = "Locacao.findByDtaLocacao", query = "SELECT l FROM Locacao l WHERE l.dtaLocacao = :dtaLocacao")
    , @NamedQuery(name = "Locacao.findByDtaEntradaPrevista", query = "SELECT l FROM Locacao l WHERE l.dtaEntradaPrevista = :dtaEntradaPrevista")
    , @NamedQuery(name = "Locacao.findByDtaEntrada", query = "SELECT l FROM Locacao l WHERE l.dtaEntrada = :dtaEntrada")
    , @NamedQuery(name = "Locacao.findByDtaSaidaPrevista", query = "SELECT l FROM Locacao l WHERE l.dtaSaidaPrevista = :dtaSaidaPrevista")
    , @NamedQuery(name = "Locacao.findByDtaSaida", query = "SELECT l FROM Locacao l WHERE l.dtaSaida = :dtaSaida")
    , @NamedQuery(name = "Locacao.findByVlrLocacao", query = "SELECT l FROM Locacao l WHERE l.vlrLocacao = :vlrLocacao")
    , @NamedQuery(name = "Locacao.findByIndSituacao", query = "SELECT l FROM Locacao l WHERE l.indSituacao = :indSituacao")})
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_locacao")
    private Integer codLocacao;
    @Basic(optional = false)
    @Column(name = "dta_locacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaLocacao;
    @Basic(optional = false)
    @Column(name = "dta_entrada_prevista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaEntradaPrevista;
    @Basic(optional = false)
    @Column(name = "dta_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaEntrada;
    @Basic(optional = false)
    @Column(name = "dta_saida_prevista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaSaidaPrevista;
    @Column(name = "dta_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaSaida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "vlr_locacao")
    private BigDecimal vlrLocacao;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codLocacao")
    private Collection<LocacaoHospede> locacaoHospedeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codLocacao")
    private Collection<Financeiro> financeiroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codLocacao")
    private Collection<LocacaoConsumivel> locacaoConsumivelCollection;
    @JoinColumn(name = "cod_quarto", referencedColumnName = "cod_quarto")
    @ManyToOne(optional = false)
    private Quarto codQuarto;
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;

    public Locacao() {
    }

    public Locacao(Integer codLocacao) {
        this.codLocacao = codLocacao;
    }

    public Locacao(Integer codLocacao, Date dtaLocacao, Date dtaEntradaPrevista, Date dtaEntrada, Date dtaSaidaPrevista, BigDecimal vlrLocacao, String indSituacao) {
        this.codLocacao = codLocacao;
        this.dtaLocacao = dtaLocacao;
        this.dtaEntradaPrevista = dtaEntradaPrevista;
        this.dtaEntrada = dtaEntrada;
        this.dtaSaidaPrevista = dtaSaidaPrevista;
        this.vlrLocacao = vlrLocacao;
        this.indSituacao = indSituacao;
    }

    public Integer getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(Integer codLocacao) {
        this.codLocacao = codLocacao;
    }

    public Date getDtaLocacao() {
        return dtaLocacao;
    }

    public void setDtaLocacao(Date dtaLocacao) {
        this.dtaLocacao = dtaLocacao;
    }

    public Date getDtaEntradaPrevista() {
        return dtaEntradaPrevista;
    }

    public void setDtaEntradaPrevista(Date dtaEntradaPrevista) {
        this.dtaEntradaPrevista = dtaEntradaPrevista;
    }

    public Date getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(Date dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    public Date getDtaSaidaPrevista() {
        return dtaSaidaPrevista;
    }

    public void setDtaSaidaPrevista(Date dtaSaidaPrevista) {
        this.dtaSaidaPrevista = dtaSaidaPrevista;
    }

    public Date getDtaSaida() {
        return dtaSaida;
    }

    public void setDtaSaida(Date dtaSaida) {
        this.dtaSaida = dtaSaida;
    }

    public BigDecimal getVlrLocacao() {
        return vlrLocacao;
    }

    public void setVlrLocacao(BigDecimal vlrLocacao) {
        this.vlrLocacao = vlrLocacao;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public Collection<LocacaoHospede> getLocacaoHospedeCollection() {
        return locacaoHospedeCollection;
    }

    public void setLocacaoHospedeCollection(Collection<LocacaoHospede> locacaoHospedeCollection) {
        this.locacaoHospedeCollection = locacaoHospedeCollection;
    }

    @XmlTransient
    public Collection<Financeiro> getFinanceiroCollection() {
        return financeiroCollection;
    }

    public void setFinanceiroCollection(Collection<Financeiro> financeiroCollection) {
        this.financeiroCollection = financeiroCollection;
    }

    @XmlTransient
    public Collection<LocacaoConsumivel> getLocacaoConsumivelCollection() {
        return locacaoConsumivelCollection;
    }

    public void setLocacaoConsumivelCollection(Collection<LocacaoConsumivel> locacaoConsumivelCollection) {
        this.locacaoConsumivelCollection = locacaoConsumivelCollection;
    }

    public Quarto getCodQuarto() {
        return codQuarto;
    }

    public void setCodQuarto(Quarto codQuarto) {
        this.codQuarto = codQuarto;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLocacao != null ? codLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) object;
        if ((this.codLocacao == null && other.codLocacao != null) || (this.codLocacao != null && !this.codLocacao.equals(other.codLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Locacao[ codLocacao=" + codLocacao + " ]";
    }
    
}
