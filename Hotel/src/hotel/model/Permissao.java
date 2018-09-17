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
@Table(name = "permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_permissao")
    private Integer codPermissao;

    @JoinColumn(name = "cod_aplicacao_botao", referencedColumnName = "cod_aplicacao_botao")
    @ManyToOne(optional = false)
    private AplicacaoBotao codAplicacaoBotao;

    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;

    public Permissao() {
    }

    public Permissao(Integer codPermissao) {
        this.codPermissao = codPermissao;
    }

    public Permissao(Integer codPermissao, AplicacaoBotao codAplicacaoBotao, Usuario codUsuario) {
        this.codPermissao = codPermissao;
        this.codAplicacaoBotao = codAplicacaoBotao;
        this.codUsuario = codUsuario;
    }

    public AplicacaoBotao getCodAplicacaoBotao() {
        return codAplicacaoBotao;
    }

    public void setCodAplicacaoBotao(AplicacaoBotao codAplicacaoBotao) {
        this.codAplicacaoBotao = codAplicacaoBotao;
    }

    public Integer getCodPermissao() {
        return codPermissao;
    }

    public void setCodPermissao(Integer codPermissao) {
        this.codPermissao = codPermissao;
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
        hash += (codPermissao != null ? codPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
