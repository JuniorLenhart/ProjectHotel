package hotel.model;

import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {

    public static Map<String, Set<String>> PERMISSAO = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_permissao")
    private Integer codPermissao;

    @JoinColumn(name = "cod_aplicacao_botao", referencedColumnName = "cod_aplicacao_botao")
    @ManyToOne(optional = false)
    private AplicacaoBotao aplicacaoBotao;

    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Permissao() {
    }

    public Permissao(Integer codPermissao) {
        this.codPermissao = codPermissao;
    }

    public Permissao(Integer codPermissao, AplicacaoBotao aplicacaoBotao, Usuario usuario) {
        this.codPermissao = codPermissao;
        this.aplicacaoBotao = aplicacaoBotao;
        this.usuario = usuario;
    }

    public AplicacaoBotao getAplicacaoBotao() {
        return aplicacaoBotao;
    }

    public void setAplicacaoBotao(AplicacaoBotao aplicacaoBotao) {
        this.aplicacaoBotao = aplicacaoBotao;
    }

    public Integer getCodPermissao() {
        return codPermissao;
    }

    public void setCodPermissao(Integer codPermissao) {
        this.codPermissao = codPermissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPermissao != null ? codPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        if ((this.codPermissao == null && other.codPermissao != null) || (this.codPermissao != null && !this.codPermissao.equals(other.codPermissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Permissao[ codPermissao=" + codPermissao + " ]";
    }
}
