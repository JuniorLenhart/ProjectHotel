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
@Table(name = "financeiro")
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_financeiro")
    private Integer codFinanceiro;

    @Column(name = "parcela")
    private int parcela;

    @Column(name = "dta_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaVencimento;

    @Column(name = "dta_pgto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaPgto;

    @Column(name = "vlr_financeiro")
    private BigDecimal vlrFinanceiro;

    @Column(name = "vlr_pago")
    private BigDecimal vlrPago;

    @JoinColumn(name = "cod_forma_pgto", referencedColumnName = "cod_forma_pgto")
    @ManyToOne(optional = false)
    private FormaPagamento codFormaPgto;

    @JoinColumn(name = "cod_locacao", referencedColumnName = "cod_locacao")
    @ManyToOne(optional = false)
    private Locacao codLocacao;

    public Financeiro() {
    }

    public Financeiro(Integer codFinanceiro) {
        this.codFinanceiro = codFinanceiro;
    }

    public Financeiro(Integer codFinanceiro, int parcela, Date dtaVencimento, BigDecimal vlrFinanceiro) {
        this.codFinanceiro = codFinanceiro;
        this.parcela = parcela;
        this.dtaVencimento = dtaVencimento;
        this.vlrFinanceiro = vlrFinanceiro;
    }

    public Integer getCodFinanceiro() {
        return codFinanceiro;
    }

    public void setCodFinanceiro(Integer codFinanceiro) {
        this.codFinanceiro = codFinanceiro;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public Date getDtaVencimento() {
        return dtaVencimento;
    }

    public void setDtaVencimento(Date dtaVencimento) {
        this.dtaVencimento = dtaVencimento;
    }

    public Date getDtaPgto() {
        return dtaPgto;
    }

    public void setDtaPgto(Date dtaPgto) {
        this.dtaPgto = dtaPgto;
    }

    public BigDecimal getVlrFinanceiro() {
        return vlrFinanceiro;
    }

    public void setVlrFinanceiro(BigDecimal vlrFinanceiro) {
        this.vlrFinanceiro = vlrFinanceiro;
    }

    public BigDecimal getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(BigDecimal vlrPago) {
        this.vlrPago = vlrPago;
    }

    public FormaPagamento getCodFormaPgto() {
        return codFormaPgto;
    }

    public void setCodFormaPgto(FormaPagamento codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
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
        hash += (codFinanceiro != null ? codFinanceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Financeiro)) {
            return false;
        }
        Financeiro other = (Financeiro) object;
        if ((this.codFinanceiro == null && other.codFinanceiro != null) || (this.codFinanceiro != null && !this.codFinanceiro.equals(other.codFinanceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Financeiro[ codFinanceiro=" + codFinanceiro + " ]";
    }
    
    public String auditoriaFormat() {
        return "ID: " + codFinanceiro + " ID Locação: " + codLocacao.getCodLocacao() + " ID FormaPgto: " + codFormaPgto.getCodFormaPgto() + " Parcela: " + parcela + " Data venc: " + dtaVencimento + " Data pgto: " + dtaPgto + " Valor financeiro: " + vlrFinanceiro + " Valor pago: " + vlrPago;
    }
}
