package hotel.model.xml;

import hotel.model.Pessoa;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pessoas")
@XmlAccessorType(XmlAccessType.FIELD)
public class PessoaXML {

    @XmlElement(name = "pessoa")
    private List<Pessoa> pessoa = null;

    public List<Pessoa> getPessoa() {
        return pessoa;
    }

    public void setPessoa(List<Pessoa> pessoa) {
        this.pessoa = pessoa;
    }
}
