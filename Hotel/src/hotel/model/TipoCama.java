package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_cama")
public class TipoCama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_tipo_cama")
    private Integer codTipoCama;

    @Column(name = "des_tipo_cama")
    private String desTipoCama;

    @Column(name = "qtd_lugar_tipo_cama")
    private int qtdLugarTipoCama;

    @Column(name = "ind_situacao")
    private String indSituacao;

    public TipoCama() {
    }

    public TipoCama(Integer codTipoCama) {
        this.codTipoCama = codTipoCama;
    }

    public TipoCama(Integer codTipoCama, String desTipoCama, int qtdLugarTipoCama, String indSituacao) {
        this.codTipoCama = codTipoCama;
        this.desTipoCama = desTipoCama;
        this.qtdLugarTipoCama = qtdLugarTipoCama;
        this.indSituacao = indSituacao;
    }

    public Integer getCodTipoCama() {
        return codTipoCama;
    }

    public void setCodTipoCama(Integer codTipoCama) {
        this.codTipoCama = codTipoCama;
    }

    public String getDesTipoCama() {
        return desTipoCama;
    }

    public void setDesTipoCama(String desTipoCama) {
        this.desTipoCama = desTipoCama;
    }

    public int getQtdLugarTipoCama() {
        return qtdLugarTipoCama;
    }

    public void setQtdLugarTipoCama(int qtdLugarTipoCama) {
        this.qtdLugarTipoCama = qtdLugarTipoCama;
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
        hash += (codTipoCama != null ? codTipoCama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoCama)) {
            return false;
        }
        TipoCama other = (TipoCama) object;
        if ((this.codTipoCama == null && other.codTipoCama != null) || (this.codTipoCama != null && !this.codTipoCama.equals(other.codTipoCama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.TipoCama[ codTipoCama=" + codTipoCama + " ]";
    }
}
