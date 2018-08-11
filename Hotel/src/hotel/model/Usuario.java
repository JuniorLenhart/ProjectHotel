/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodUsuario", query = "SELECT u FROM Usuario u WHERE u.codUsuario = :codUsuario")
    , @NamedQuery(name = "Usuario.findByDesLogin", query = "SELECT u FROM Usuario u WHERE u.desLogin = :desLogin")
    , @NamedQuery(name = "Usuario.findByDesSenha", query = "SELECT u FROM Usuario u WHERE u.desSenha = :desSenha")
    , @NamedQuery(name = "Usuario.findByIndTipo", query = "SELECT u FROM Usuario u WHERE u.indTipo = :indTipo")
    , @NamedQuery(name = "Usuario.findByIndSituacao", query = "SELECT u FROM Usuario u WHERE u.indSituacao = :indSituacao")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_usuario")
    private Integer codUsuario;
    @Basic(optional = false)
    @Column(name = "des_login")
    private String desLogin;
    @Basic(optional = false)
    @Column(name = "des_senha")
    private String desSenha;
    @Basic(optional = false)
    @Column(name = "ind_tipo")
    private String indTipo;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    private Collection<Permissao> permissaoCollection;
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    private Collection<Locacao> locacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    private Collection<Auditoria> auditoriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    private Collection<Reserva> reservaCollection;

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

    @XmlTransient
    public Collection<Permissao> getPermissaoCollection() {
        return permissaoCollection;
    }

    public void setPermissaoCollection(Collection<Permissao> permissaoCollection) {
        this.permissaoCollection = permissaoCollection;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @XmlTransient
    public Collection<Locacao> getLocacaoCollection() {
        return locacaoCollection;
    }

    public void setLocacaoCollection(Collection<Locacao> locacaoCollection) {
        this.locacaoCollection = locacaoCollection;
    }

    @XmlTransient
    public Collection<Auditoria> getAuditoriaCollection() {
        return auditoriaCollection;
    }

    public void setAuditoriaCollection(Collection<Auditoria> auditoriaCollection) {
        this.auditoriaCollection = auditoriaCollection;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
