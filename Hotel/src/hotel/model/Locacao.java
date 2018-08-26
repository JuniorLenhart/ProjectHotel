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
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_locacao")
    private Integer codLocacao;

    @Column(name = "dta_locacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaLocacao;

    @Column(name = "dta_entrada_prevista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaEntradaPrevista;

    @Column(name = "dta_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaEntrada;

    @Column(name = "dta_saida_prevista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaSaidaPrevista;

    @Column(name = "dta_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaSaida;

    @Column(name = "vlr_locacao")
    private BigDecimal vlrLocacao;

    @Column(name = "ind_situacao")
    private String indSituacao;

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
    
    public String auditoriaFormat() {
        return "ID: " + codLocacao + " ID Quarto: " + codQuarto.getCodQuarto() + " ID Usuário: " + codUsuario.getCodUsuario() + " Data locação: " + dtaLocacao
                + " Data entrada prevista: " + dtaEntradaPrevista + " Data entrada: " + dtaEntrada + " Data saida prevista: " + dtaSaidaPrevista + " Data saida: " + dtaSaida
                + " Valor locação: " + vlrLocacao + " Situação: " + indSituacao;
    }
}
