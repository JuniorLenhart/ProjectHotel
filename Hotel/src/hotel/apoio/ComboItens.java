package hotel.apoio;

public class ComboItens {

    private int codigo;
    private String descricao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int pCodigo) {
        this.codigo = pCodigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String pDescricao) {
        this.descricao = pDescricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
