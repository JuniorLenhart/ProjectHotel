/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "permissao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissao.findAll", query = "SELECT p FROM Permissao p")
    , @NamedQuery(name = "Permissao.findByCodPermissao", query = "SELECT p FROM Permissao p WHERE p.codPermissao = :codPermissao")
    , @NamedQuery(name = "Permissao.findByIndLeitura", query = "SELECT p FROM Permissao p WHERE p.indLeitura = :indLeitura")
    , @NamedQuery(name = "Permissao.findByIndEscrita", query = "SELECT p FROM Permissao p WHERE p.indEscrita = :indEscrita")})
public class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_permissao")
    private Integer codPermissao;
    @Basic(optional = false)
    @Column(name = "ind_leitura")
    private String indLeitura;
    @Basic(optional = false)
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
    
}
