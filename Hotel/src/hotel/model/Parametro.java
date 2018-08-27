package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametro")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_parametro")
    private Integer codParametro;

    @Column(name = "num_dias_checkin")
    private Integer numDiasCheckin;

    @Column(name = "num_dias_pgto_reserva")
    private Integer numDiasPgtoReserva;

    @Column(name = "des_senha_default")
    private String desSenhaDefault;
    
    @Column(name = "cod_usuario")
    private Integer codUsuario;
    
    @Column(name = "auditoria_ativa")
    private boolean auditoriaAtiva;

    public static Integer NUM_DIAS_CHECKIN = 0;
    public static Integer NUM_DIAS_PGTO_RESERVA = 0;
    public static String DES_SENHA_DEFAULT = "";
    public static Integer COD_USUARIO = 0;
    public static boolean AUDITORIA_ATIVA = true;
    
    public Parametro() {
    }

    public Parametro(Parametro parametro) {
        DES_SENHA_DEFAULT = parametro.desSenhaDefault;
        NUM_DIAS_CHECKIN = parametro.numDiasCheckin;
        NUM_DIAS_PGTO_RESERVA = parametro.numDiasPgtoReserva;
        COD_USUARIO = parametro.codUsuario;
        AUDITORIA_ATIVA = parametro.auditoriaAtiva;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
        COD_USUARIO = codUsuario;
    }

    public boolean isAuditoriaAtiva() {
        return auditoriaAtiva;
    }

    public void setAuditoriaAtiva(boolean auditoriaAtiva) {
        this.auditoriaAtiva = auditoriaAtiva;
        AUDITORIA_ATIVA = auditoriaAtiva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codParametro != null ? codParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.codParametro == null && other.codParametro != null) || (this.codParametro != null && !this.codParametro.equals(other.codParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Parametro[ codParametro=" + codParametro + " ]";
    }
}
