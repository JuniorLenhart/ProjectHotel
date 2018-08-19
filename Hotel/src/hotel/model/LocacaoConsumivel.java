package hotel.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locacao_consumivel")
public class LocacaoConsumivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_locacao_consumivel")
    private Integer codLocacaoConsumivel;

    @Column(name = "qtd_consumivel")
    private int qtdConsumivel;

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
