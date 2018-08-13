package hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "quarto")
public class Quarto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_quarto")
    private Integer codQuarto;

    @Basic(optional = false)
    @Column(name = "num_quarto")
    private String numQuarto;

    @Basic(optional = false)
    @Column(name = "vlr_quarto")
    private BigDecimal vlrQuarto;

    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;

    @JoinTable(name = "quarto_tipo_cama", joinColumns = {
        @JoinColumn(name = "cod_quarto", referencedColumnName = "cod_quarto")}, inverseJoinColumns = {
        @JoinColumn(name = "cod_tipo_cama", referencedColumnName = "cod_tipo_cama")})
    @ManyToMany
    private List<TipoCama> tipoCama;

    public Quarto() {
    }

    public Quarto(Integer codQuarto) {
        this.codQuarto = codQuarto;
    }

    public Quarto(Integer codQuarto, String numQuarto, BigDecimal vlrQuarto, String indSituacao) {
        this.codQuarto = codQuarto;
        this.numQuarto = numQuarto;
        this.vlrQuarto = vlrQuarto;
        this.indSituacao = indSituacao;
    }

    public Integer getCodQuarto() {
        return codQuarto;
    }

    public void setCodQuarto(Integer codQuarto) {
        this.codQuarto = codQuarto;
    }

    public String getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(String numQuarto) {
        this.numQuarto = numQuarto;
    }

    public BigDecimal getVlrQuarto() {
        return vlrQuarto;
    }

    public void setVlrQuarto(BigDecimal vlrQuarto) {
        this.vlrQuarto = vlrQuarto;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public List<TipoCama> getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(List<TipoCama> tipoCama) {
        this.tipoCama = tipoCama;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codQuarto != null ? codQuarto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.codQuarto == null && other.codQuarto != null) || (this.codQuarto != null && !this.codQuarto.equals(other.codQuarto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Quarto[ codQuarto=" + codQuarto + " ]";
    }
}