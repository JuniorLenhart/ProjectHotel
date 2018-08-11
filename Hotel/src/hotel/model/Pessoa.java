/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Júnior
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findByCodPessoa", query = "SELECT p FROM Pessoa p WHERE p.codPessoa = :codPessoa")
    , @NamedQuery(name = "Pessoa.findByNomPessoa", query = "SELECT p FROM Pessoa p WHERE p.nomPessoa = :nomPessoa")
    , @NamedQuery(name = "Pessoa.findByNumCpf", query = "SELECT p FROM Pessoa p WHERE p.numCpf = :numCpf")
    , @NamedQuery(name = "Pessoa.findByDtaNasc", query = "SELECT p FROM Pessoa p WHERE p.dtaNasc = :dtaNasc")
    , @NamedQuery(name = "Pessoa.findByDesEndereco", query = "SELECT p FROM Pessoa p WHERE p.desEndereco = :desEndereco")
    , @NamedQuery(name = "Pessoa.findByDesEmail", query = "SELECT p FROM Pessoa p WHERE p.desEmail = :desEmail")
    , @NamedQuery(name = "Pessoa.findByNumTelefone", query = "SELECT p FROM Pessoa p WHERE p.numTelefone = :numTelefone")
    , @NamedQuery(name = "Pessoa.findByNumCelular", query = "SELECT p FROM Pessoa p WHERE p.numCelular = :numCelular")
    , @NamedQuery(name = "Pessoa.findByIndSituacao", query = "SELECT p FROM Pessoa p WHERE p.indSituacao = :indSituacao")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_pessoa")
    private Integer codPessoa;
    @Column(name = "nom_pessoa")
    private String nomPessoa;
    @Basic(optional = false)
    @Column(name = "num_cpf")
    private String numCpf;
    @Column(name = "dta_nasc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaNasc;
    @Column(name = "des_endereco")
    private String desEndereco;
    @Basic(optional = false)
    @Column(name = "des_email")
    private String desEmail;
    @Column(name = "num_telefone")
    private String numTelefone;
    @Column(name = "num_celular")
    private String numCelular;
    @Basic(optional = false)
    @Column(name = "ind_situacao")
    private String indSituacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPessoa")
    private Collection<LocacaoHospede> locacaoHospedeCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPessoa")
    private Collection<Reserva> reservaCollection;

    public Pessoa() {
    }

    public Pessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Pessoa(Integer codPessoa, String numCpf, String desEmail, String indSituacao) {
        this.codPessoa = codPessoa;
        this.numCpf = numCpf;
        this.desEmail = desEmail;
        this.indSituacao = indSituacao;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNomPessoa() {
        return nomPessoa;
    }

    public void setNomPessoa(String nomPessoa) {
        this.nomPessoa = nomPessoa;
    }

    public String getNumCpf() {
        return numCpf;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

    public Date getDtaNasc() {
        return dtaNasc;
    }

    public void setDtaNasc(Date dtaNasc) {
        this.dtaNasc = dtaNasc;
    }

    public String getDesEndereco() {
        return desEndereco;
    }

    public void setDesEndereco(String desEndereco) {
        this.desEndereco = desEndereco;
    }

    public String getDesEmail() {
        return desEmail;
    }

    public void setDesEmail(String desEmail) {
        this.desEmail = desEmail;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @XmlTransient
    public Collection<LocacaoHospede> getLocacaoHospedeCollection() {
        return locacaoHospedeCollection;
    }

    public void setLocacaoHospedeCollection(Collection<LocacaoHospede> locacaoHospedeCollection) {
        this.locacaoHospedeCollection = locacaoHospedeCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.codPessoa == null && other.codPessoa != null) || (this.codPessoa != null && !this.codPessoa.equals(other.codPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Pessoa[ codPessoa=" + codPessoa + " ]";
    }
    
}
