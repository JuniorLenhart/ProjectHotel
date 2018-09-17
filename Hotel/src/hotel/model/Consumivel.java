package hotel.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consumivel")
public class Consumivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_consumivel")
    private Integer codConsumivel;

    @Column(name = "nom_consumivel")
    private String nomConsumivel;

    @Column(name = "des_consumivel")
    private String desConsumivel;

    @Column(name = "vlr_consumivel")
    private BigDecimal vlrConsumivel;

    @Column(name = "tip_consumivel")
    private String tipConsumivel;

    @Column(name = "ind_situacao")
    private String indSituacao;

    public Consumivel() {
    }

    public Consumivel(Integer codConsumivel) {
        this.codConsumivel = codConsumivel;
    }

    public Consumivel(Integer codConsumivel, String nomConsumivel, String desConsumivel, BigDecimal vlrConsumivel, String tipConsumivel, String indSituacao) {
        this.codConsumivel = codConsumivel;
        this.nomConsumivel = nomConsumivel;
        this.desConsumivel = desConsumivel;
        this.vlrConsumivel = vlrConsumivel;
        this.tipConsumivel = tipConsumivel;
        this.indSituacao = indSituacao;
    }

    public Integer getCodConsumivel() {
        return codConsumivel;
    }

    public void setCodConsumivel(Integer codConsumivel) {
        this.codConsumivel = codConsumivel;
    }

    public String getNomConsumivel() {
        return nomConsumivel;
    }

    public void setNomConsumivel(String nomConsumivel) {
        this.nomConsumivel = nomConsumivel;
    }

    public String getDesConsumivel() {
        return desConsumivel;
    }

    public void setDesConsumivel(String desConsumivel) {
        this.desConsumivel = desConsumivel;
    }

    public BigDecimal getVlrConsumivel() {
        return vlrConsumivel;
    }

    public void setVlrConsumivel(BigDecimal vlrConsumivel) {
        this.vlrConsumivel = vlrConsumivel;
    }

    public String getTipConsumivel() {
        return tipConsumivel;
    }

    public void setTipConsumivel(String tipConsumivel) {
        this.tipConsumivel = tipConsumivel;
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
        hash += (codConsumivel != null ? codConsumivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Consumivel)) {
            return false;
        }
        Consumivel other = (Consumivel) object;
        if ((this.codConsumivel == null && other.codConsumivel != null) || (this.codConsumivel != null && !this.codConsumivel.equals(other.codConsumivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Consumivel[ codConsumivel=" + codConsumivel + " ]";
    }
}