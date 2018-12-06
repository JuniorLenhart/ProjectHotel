package hotel.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "novidade")
public class Novidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_novidade")
    private Integer codNovidade;

    @Column(name = "dta_novidade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaNovidade;

    @Column(name = "ver_novidade")
    private String verNovidade;

    @Column(name = "des_novidade")
    private String desNovidade;

    public Novidade() {
    }

    public Integer getCodNovidade() {
        return codNovidade;
    }

    public void setCodNovidade(Integer codNovidade) {
        this.codNovidade = codNovidade;
    }

    public Date getDtaNovidade() {
        return dtaNovidade;
    }

    public void setDtaNovidade(Date dtaNovidade) {
        this.dtaNovidade = dtaNovidade;
    }

    public String getVerNovidade() {
        return verNovidade;
    }

    public void setVerNovidade(String verNovidade) {
        this.verNovidade = verNovidade;
    }

    public String getDesNovidade() {
        return desNovidade;
    }

    public void setDesNovidade(String desNovidade) {
        this.desNovidade = desNovidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codNovidade != null ? codNovidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Novidade)) {
            return false;
        }
        Novidade other = (Novidade) object;
        if ((this.codNovidade == null && other.codNovidade != null) || (this.codNovidade != null && !this.codNovidade.equals(other.codNovidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Novidade[ codNovidade=" + codNovidade + " ]";
    }
}
