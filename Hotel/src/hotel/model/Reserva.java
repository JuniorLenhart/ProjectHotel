/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByCodReserva", query = "SELECT r FROM Reserva r WHERE r.codReserva = :codReserva")
    , @NamedQuery(name = "Reserva.findByDtaReserva", query = "SELECT r FROM Reserva r WHERE r.dtaReserva = :dtaReserva")
    , @NamedQuery(name = "Reserva.findByDtaEntrada", query = "SELECT r FROM Reserva r WHERE r.dtaEntrada = :dtaEntrada")
    , @NamedQuery(name = "Reserva.findByDtaSaida", query = "SELECT r FROM Reserva r WHERE r.dtaSaida = :dtaSaida")
    , @NamedQuery(name = "Reserva.findByQtdLugar", query = "SELECT r FROM Reserva r WHERE r.qtdLugar = :qtdLugar")
    , @NamedQuery(name = "Reserva.findByVlrReserva", query = "SELECT r FROM Reserva r WHERE r.vlrReserva = :vlrReserva")
    , @NamedQuery(name = "Reserva.findByIndSituacao", query = "SELECT r FROM Reserva r WHERE r.indSituacao = :indSituacao")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_reserva")
    private Integer codReserva;
    @Basic(optional = false)
    @Column(name = "dta_reserva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaReserva;
    @Basic(optional = false)
    @Column(name = "dta_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaEntrada;
    @Basic(optional = false)
    @Column(name = "dta_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaSaida;
    @Basic(optional = false)
    @Column(name = "qtd_lugar")
    private int qtdLugar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "vlr_reserva")
    private BigDecimal vlrReserva;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa")
    @ManyToOne(optional = false)
    private Pessoa codPessoa;
    @JoinColumn(name = "cod_quarto", referencedColumnName = "cod_quarto")
    @ManyToOne(optional = false)
    private Quarto codQuarto;
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;

    public Reserva() {
    }

    public Reserva(Integer codReserva) {
        this.codReserva = codReserva;
    }

    public Reserva(Integer codReserva, Date dtaReserva, Date dtaEntrada, Date dtaSaida, int qtdLugar, BigDecimal vlrReserva, String indSituacao) {
        this.codReserva = codReserva;
        this.dtaReserva = dtaReserva;
        this.dtaEntrada = dtaEntrada;
        this.dtaSaida = dtaSaida;
        this.qtdLugar = qtdLugar;
        this.vlrReserva = vlrReserva;
        this.indSituacao = indSituacao;
    }

    public Integer getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(Integer codReserva) {
        this.codReserva = codReserva;
    }

    public Date getDtaReserva() {
        return dtaReserva;
    }

    public void setDtaReserva(Date dtaReserva) {
        this.dtaReserva = dtaReserva;
    }

    public Date getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(Date dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    public Date getDtaSaida() {
        return dtaSaida;
    }

    public void setDtaSaida(Date dtaSaida) {
        this.dtaSaida = dtaSaida;
    }

    public int getQtdLugar() {
        return qtdLugar;
    }

    public void setQtdLugar(int qtdLugar) {
        this.qtdLugar = qtdLugar;
    }

    public BigDecimal getVlrReserva() {
        return vlrReserva;
    }

    public void setVlrReserva(BigDecimal vlrReserva) {
        this.vlrReserva = vlrReserva;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    public Pessoa getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Pessoa codPessoa) {
        this.codPessoa = codPessoa;
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
        hash += (codReserva != null ? codReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codReserva == null && other.codReserva != null) || (this.codReserva != null && !this.codReserva.equals(other.codReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Reserva[ codReserva=" + codReserva + " ]";
    }
    
}
