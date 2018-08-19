package hotel.model;

public enum TipoConsumivel {
    C("Café da manhã"), A("Almoço"), J("Janta"), L("Lanche"), B("Bebida");

    private final String valor;

    private TipoConsumivel(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
