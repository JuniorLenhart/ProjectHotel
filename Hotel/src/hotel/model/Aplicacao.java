package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aplicacao")
public class Aplicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aplicacao")
    private Integer codAplicacao;

    @Column(name = "nom_aplicacao")
    private String nomAplicacao;

    @Column(name = "nom_arquivo_java")
    private String nomArquivoJava;
    
    @Column(name = "ind_situacao")
    private String indSituacao;

    public Aplicacao() {
    }

    public Aplicacao(Integer codAplicacao) {
        this.codAplicacao = codAplicacao;
    }

    public Aplicacao(Integer codAplicacao, String nomAplicacao, String nomArquivoJava, String indSituacao) {
        this.codAplicacao = codAplicacao;
        this.nomAplicacao = nomAplicacao;
        this.nomArquivoJava = nomArquivoJava;
        this.indSituacao = indSituacao;
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

    public String getIndSituacao() {
        return indSituacao;
    }

    public void setIndSituacao(String indSituacao) {
        this.indSituacao = indSituacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAplicacao != null ? codAplicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
