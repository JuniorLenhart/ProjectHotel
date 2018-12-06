package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visto")
public class Visto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_visto")
    private Integer codVisto;

    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @JoinColumn(name = "cod_novidade", referencedColumnName = "cod_novidade")
    @ManyToOne(optional = false)
    private Novidade novidade;

    @Column(name = "ind_visto")
    private String indVisto;

    public Visto() {
    }

    public Integer getCodVisto() {
        return codVisto;
    }

    public void setCodVisto(Integer codVisto) {
        this.codVisto = codVisto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Novidade getNovidade() {
        return novidade;
    }

    public void setNovidade(Novidade novidade) {
        this.novidade = novidade;
    }

    public String getIndVisto() {
        return indVisto;
    }

    public void setIndVisto(String indVisto) {
        this.indVisto = indVisto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVisto != null ? codVisto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Visto)) {
            return false;
        }
        Visto other = (Visto) object;
        if ((this.codVisto == null && other.codVisto != null) || (this.codVisto != null && !this.codVisto.equals(other.codVisto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Visto[ codVisto=" + codVisto + " ]";
    }
}
