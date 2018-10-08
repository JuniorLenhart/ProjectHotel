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

    @Column(name = "qtd_consumivel", updatable = false)
    private int qtdConsumivel;

    @Column(name = "vlr_consumivel", updatable = false)
    private BigDecimal vlrConsumivel;

    @JoinColumn(name = "cod_consumivel", referencedColumnName = "cod_consumivel")
    @ManyToOne(optional = false)
    private Consumivel consumivel;

    @JoinColumn(name = "cod_locacao", referencedColumnName = "cod_locacao")
    @ManyToOne(optional = false)
    private Locacao locacao;

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

    public Consumivel getConsumivel() {
        return consumivel;
    }

    public void setConsumivel(Consumivel consumivel) {
        this.consumivel = consumivel;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
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