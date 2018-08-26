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

    @Column(name = "ind_leitura")
    private String indLeitura;

    @Column(name = "ind_escrita")
    private String indEscrita;

    @JoinColumn(name = "cod_aplicacao", referencedColumnName = "cod_aplicacao")
    @ManyToOne(optional = false)
    private Aplicacao codAplicacao;

    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;

    public Permissao() {
    }

    public Permissao(Integer codPermissao) {
        this.codPermissao = codPermissao;
    }

    public Permissao(Integer codPermissao, String indLeitura, String indEscrita) {
        this.codPermissao = codPermissao;
        this.indLeitura = indLeitura;
        this.indEscrita = indEscrita;
    }

    public Integer getCodPermissao() {
        return codPermissao;
    }

    public void setCodPermissao(Integer codPermissao) {
        this.codPermissao = codPermissao;
    }

    public String getIndLeitura() {
        return indLeitura;
    }

    public void setIndLeitura(String indLeitura) {
        this.indLeitura = indLeitura;
    }

    public String getIndEscrita() {
        return indEscrita;
    }

    public void setIndEscrita(String indEscrita) {
        this.indEscrita = indEscrita;
    }

    public Aplicacao getCodAplicacao() {
        return codAplicacao;
    }

    public void setCodAplicacao(Aplicacao codAplicacao) {
        this.codAplicacao = codAplicacao;
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

    public String auditoriaFormat() {
        return "ID: " + codPermissao + " ID Aplicação: " + codAplicacao.getCodAplicacao() + " ID Usuário: " + codUsuario.getCodUsuario() + " Leitura: " + indLeitura
                + " Escrita: " + indEscrita;
    }
}
