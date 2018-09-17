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
@Table(name = "aplicacao_botao")
public class AplicacaoBotao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aplicacao_botao")
    private Integer codAplicacaoBotao;
    
    @Column(name = "nom_botao")
    private String nomBotao;
    
    @Column(name = "nom_botao_form")
    private String nomBotaoForm;
    
    @JoinColumn(name = "cod_aplicacao", referencedColumnName = "cod_aplicacao")
    @ManyToOne(optional = false)
    private Aplicacao codAplicacao;

    public AplicacaoBotao() {
    }

    public AplicacaoBotao(Integer codBotao) {
        this.codAplicacaoBotao = codBotao;
    }

    public AplicacaoBotao(Integer codBotao, String nomBotao, String nomBotaoForm, Aplicacao codAplicacao) {
        this.codAplicacaoBotao = codBotao;
        this.nomBotao = nomBotao;
        this.nomBotaoForm = nomBotaoForm;
        this.codAplicacao = codAplicacao;
    }

    public Integer getCodAplicacaoBotao() {
        return codAplicacaoBotao;
    }

    public void setCodAplicacaoBotao(Integer codAplicacaoBotao) {
        this.codAplicacaoBotao = codAplicacaoBotao;
    }

    public String getNomBotao() {
        return nomBotao;
    }

    public void setNomBotao(String nomBotao) {
        this.nomBotao = nomBotao;
    }

    public String getNomBotaoForm() {
        return nomBotaoForm;
    }

    public void setNomBotaoForm(String nomBotaoForm) {
        this.nomBotaoForm = nomBotaoForm;
    }

    public Aplicacao getCodAplicacao() {
        return codAplicacao;
    }

    public void setCodAplicacao(Aplicacao codAplicacao) {
        this.codAplicacao = codAplicacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAplicacaoBotao != null ? codAplicacaoBotao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AplicacaoBotao)) {
            return false;
        }
        AplicacaoBotao other = (AplicacaoBotao) object;
        if ((this.codAplicacaoBotao == null && other.codAplicacaoBotao != null) || (this.codAplicacaoBotao != null && !this.codAplicacaoBotao.equals(other.codAplicacaoBotao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.AplicacaoBotao[ codAplicacaoBotao=" + codAplicacaoBotao + " ]";
    }
}
