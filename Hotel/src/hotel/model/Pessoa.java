package hotel.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pessoa")
@XmlRootElement(name = "pessoa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pessoa")
    private Integer codPessoa;

    @Column(name = "nom_pessoa")
    private String nomPessoa;

    @Column(name = "num_cpf")
    private String numCpf;

    @Column(name = "dta_nasc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaNasc;

    @Column(name = "des_endereco")
    private String desEndereco;

    @Column(name = "des_email")
    private String desEmail;

    @Column(name = "num_telefone")
    private String numTelefone;

    @Column(name = "num_celular")
    private String numCelular;

    @Column(name = "ind_situacao")
    private String indSituacao;

    public Pessoa() {
    }

    public Pessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Pessoa(Integer codPessoa, String nomPessoa, String numCpf, String desEmail) {
        this.codPessoa = codPessoa;
        this.nomPessoa = nomPessoa;
        this.numCpf = numCpf;
        this.desEmail = desEmail;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
