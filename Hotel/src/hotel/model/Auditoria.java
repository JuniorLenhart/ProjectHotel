package hotel.model;

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
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_auditoria")
    private Integer codAuditoria;

    @Column(name = "tip_auditoria")
    private String tipAuditoria;

    @Column(name = "dta_auditoria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaAuditoria;

    @Column(name = "des_auditoria")
    private String desAuditoria;

    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;
    
    public static boolean auditoriaAtiva = true;

    public Auditoria() {
    }

    public Auditoria(Integer codAuditoria) {
        this.codAuditoria = codAuditoria;
    }

    public Auditoria(Integer codAuditoria, String tipAuditoria, Date dtaAuditoria, String desAuditoria) {
        this.codAuditoria = codAuditoria;
        this.tipAuditoria = tipAuditoria;
        this.dtaAuditoria = dtaAuditoria;
        this.desAuditoria = desAuditoria;
    }

    public Integer getCodAuditoria() {
        return codAuditoria;
    }

    public void setCodAuditoria(Integer codAuditoria) {
        this.codAuditoria = codAuditoria;
    }

    public String getTipAuditoria() {
        return tipAuditoria;
    }

    public void setTipAuditoria(String tipAuditoria) {
        this.tipAuditoria = tipAuditoria;
    }

    public Date getDtaAuditoria() {
        return dtaAuditoria;
    }

    public void setDtaAuditoria(Date dtaAuditoria) {
        this.dtaAuditoria = dtaAuditoria;
    }

    public String getDesAuditoria() {
        return desAuditoria;
    }

    public void setDesAuditoria(String desAuditoria) {
        this.desAuditoria = desAuditoria;
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
        hash += (codAuditoria != null ? codAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.codAuditoria == null && other.codAuditoria != null) || (this.codAuditoria != null && !this.codAuditoria.equals(other.codAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Auditoria[ codAuditoria=" + codAuditoria + " ]";
    }
}
