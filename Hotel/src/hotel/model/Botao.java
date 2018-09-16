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
@Table(name = "botao")
public class Botao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_botao")
    private Integer codBotao;
    
    @Column(name = "nom_botao")
    private String nomBotao;
    
    @Column(name = "nom_botao_form")
    private String nomBotaoForm;
    
    @JoinColumn(name = "cod_aplicacao", referencedColumnName = "cod_aplicacao")
    @ManyToOne(optional = false)
    private Aplicacao codAplicacao;

    public Botao() {
    }

    public Botao(Integer codBotao) {
        this.codBotao = codBotao;
    }

    public Botao(Integer codBotao, String nomBotao, String nomBotaoForm, Aplicacao codAplicacao) {
        this.codBotao = codBotao;
        this.nomBotao = nomBotao;
        this.nomBotaoForm = nomBotaoForm;
        this.codAplicacao = codAplicacao;
    }

    public Integer getCodBotao() {
        return codBotao;
    }

    public void setCodBotao(Integer codBotao) {
        this.codBotao = codBotao;
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
        hash += (codBotao != null ? codBotao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Botao)) {
            return false;
        }
        Botao other = (Botao) object;
        if ((this.codBotao == null && other.codBotao != null) || (this.codBotao != null && !this.codBotao.equals(other.codBotao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.Botao[ codBotao=" + codBotao + " ]";
    }
}
