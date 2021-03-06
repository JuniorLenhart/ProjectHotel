package hotel.model;

import hotel.socketclient.Client;
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

    @Column(name = "ind_auditoria")
    private boolean indAuditoria;

    @Column(name = "per_juros")
    private Integer perJuros;

    @Column(name = "dir_financeiro")
    private String dirFinanceiro;

    public static Integer NUM_DIAS_CHECKIN = 0;
    public static Integer NUM_DIAS_PGTO_RESERVA = 0;
    public static String DES_SENHA_DEFAULT = "";
    public static Usuario USUARIO = null;
    public static boolean AUDITORIA_ATIVA = true;
    public static Integer PER_JUROS = 0;
    public static String DIR_FINANCEIRO = "";
    public static Client CLIENT = null;

    public Parametro() {
    }

    public Parametro(Parametro parametro) {
        DES_SENHA_DEFAULT = parametro.desSenhaDefault;
        NUM_DIAS_CHECKIN = parametro.numDiasCheckin;
        NUM_DIAS_PGTO_RESERVA = parametro.numDiasPgtoReserva;
        AUDITORIA_ATIVA = parametro.indAuditoria;
        PER_JUROS = parametro.perJuros;
        DIR_FINANCEIRO = parametro.dirFinanceiro;
    }

    public static void setUser(Usuario usuario) {
        USUARIO = usuario;
    }

    public boolean isAuditoriaAtiva() {
        return indAuditoria;
    }

    public void setAuditoriaAtiva(boolean indAuditoria) {
        this.indAuditoria = indAuditoria;
        AUDITORIA_ATIVA = indAuditoria;
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
