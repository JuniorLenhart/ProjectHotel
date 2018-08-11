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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "aplicacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aplicacao.findAll", query = "SELECT a FROM Aplicacao a")
    , @NamedQuery(name = "Aplicacao.findByCodAplicacao", query = "SELECT a FROM Aplicacao a WHERE a.codAplicacao = :codAplicacao")
    , @NamedQuery(name = "Aplicacao.findByNomAplicacao", query = "SELECT a FROM Aplicacao a WHERE a.nomAplicacao = :nomAplicacao")
    , @NamedQuery(name = "Aplicacao.findByNomArquivoJava", query = "SELECT a FROM Aplicacao a WHERE a.nomArquivoJava = :nomArquivoJava")})
public class Aplicacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_aplicacao")
    private Integer codAplicacao;
    @Basic(optional = false)
    @Column(name = "nom_aplicacao")
    private String nomAplicacao;
    @Basic(optional = false)
    @Column(name = "nom_arquivo_java")
    private String nomArquivoJava;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codAplicacao")
    private Collection<Permissao> permissaoCollection;

    public Aplicacao() {
    }

    public Aplicacao(Integer codAplicacao) {
        this.codAplicacao = codAplicacao;
    }

    public Aplicacao(Integer codAplicacao, String nomAplicacao, String nomArquivoJava) {
        this.codAplicacao = codAplicacao;
        this.nomAplicacao = nomAplicacao;
        this.nomArquivoJava = nomArquivoJava;
    }

    public Integer getCodAplicacao() {
        return codAplicacao;
    }

    public void setCodAplicacao(Integer codAplicacao) {
        this.codAplicacao = codAplicacao;
    }

    public String getNomAplicacao() {
        return nomAplicacao;
    }

    public void setNomAplicacao(String nomAplicacao) {
        this.nomAplicacao = nomAplicacao;
    }

    public String getNomArquivoJava() {
        return nomArquivoJava;
    }

    public void setNomArquivoJava(String nomArquivoJava) {
        this.nomArquivoJava = nomArquivoJava;
    }

    @XmlTransient
    public Collection<Permissao> getPermissaoCollection() {
        return permissaoCollection;
    }

    public void setPermissaoCollection(Collection<Permissao> permissaoCollection) {
        this.permissaoCollection = permissaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAplicacao != null ? codAplicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aplicacao)) {
            return false;
        }
        Aplicacao other = (Aplicacao) object;
        if ((this.codAplicacao == null && other.codAplicacao != null) || (this.codAplicacao != null && !this.codAplicacao.equals(other.codAplicacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Aplicacao[ codAplicacao=" + codAplicacao + " ]";
    }
    
}
