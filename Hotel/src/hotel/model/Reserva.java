package hotel.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_reserva")
    private Integer codReserva;

    @Column(name = "dta_reserva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaReserva;

    @Column(name = "dta_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaEntrada;

    @Column(name = "dta_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaSaida;

    @Column(name = "qtd_lugar")
    private int qtdLugar;

    @Column(name = "vlr_reserva")
    private BigDecimal vlrReserva;

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
