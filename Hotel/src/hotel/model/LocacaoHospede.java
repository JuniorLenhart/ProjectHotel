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
@Table(name = "locacao_hospede")
public class LocacaoHospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_locacao_hospede")
    private Integer codLocacaoHospede;

    @Column(name = "ind_responsavel")
    private String indResponsavel;

    @JoinColumn(name = "cod_locacao", referencedColumnName = "cod_locacao")
    @ManyToOne(optional = false)
    private Locacao locacao;

    @JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public LocacaoHospede() {
    }

    public LocacaoHospede(Integer codLocacaoHospede) {
        this.codLocacaoHospede = codLocacaoHospede;
    }

    public LocacaoHospede(Integer codLocacaoHospede, String indResponsavel) {
        this.codLocacaoHospede = codLocacaoHospede;
        this.indResponsavel = indResponsavel;
    }

    public Integer getCodLocacaoHospede() {
        return codLocacaoHospede;
    }

    public void setCodLocacaoHospede(Integer codLocacaoHospede) {
        this.codLocacaoHospede = codLocacaoHospede;
    }

    public String getIndResponsavel() {
        return indResponsavel;
    }

    public void setIndResponsavel(String indResponsavel) {
        this.indResponsavel = indResponsavel;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
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
        hash += (codLocacaoHospede != null ? codLocacaoHospede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LocacaoHospede)) {
            return false;
        }
        LocacaoHospede other = (LocacaoHospede) object;
        if ((this.codLocacaoHospede == null && other.codLocacaoHospede != null) || (this.codLocacaoHospede != null && !this.codLocacaoHospede.equals(other.codLocacaoHospede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.model.LocacaoHospede[ codLocacaoHospede=" + codLocacaoHospede + " ]";
    }
}