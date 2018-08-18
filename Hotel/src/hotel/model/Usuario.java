package hotel.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "cod_usuario")
    private Integer codUsuario;

    @Column(name = "des_login")
    private String desLogin;

    @Column(name = "des_senha")
    private String desSenha;

    @Column(name = "ind_tipo")
    private String indTipo;

    @Column(name = "ind_situacao")
    private String indSituacao;

    @OneToMany(mappedBy = "codUsuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Permissao> permissao;

    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public Usuario() {
    }

    public Usuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario(Integer codUsuario, String desLogin, String desSenha, String indTipo, String indSituacao) {
        this.codUsuario = codUsuario;
        this.desLogin = desLogin;
        this.desSenha = desSenha;
        this.indTipo = indTipo;
        this.indSituacao = indSituacao;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getDesLogin() {
        return desLogin;
    }

    public void setDesLogin(String desLogin) {
        this.desLogin = desLogin;
    }

    public String getDesSenha() {
        return desSenha;
    }

    public void setDesSenha(String desSenha) {
        this.desSenha = desSenha;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    public List<Permissao> getPermissao() {
        return permissao;
    }

    public void setPermissao(List<Permissao> permissao) {
        this.permissao = permissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Usuario[ codUsuario=" + codUsuario + " ]";
    }
}
