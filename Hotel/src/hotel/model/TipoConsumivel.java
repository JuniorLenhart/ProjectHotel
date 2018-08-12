/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

/**
 *
 * @author George
 */
public enum TipoConsumivel {
    C("Café da manhã"), A("Almoço"), J("Janta"), L("Lanche"), B("Bebida");
    
    private final String valor;
    
    private TipoConsumivel(String valor) {
        this.valor = valor;
    }
    
    public String getValor(){
        return valor;
    }
    
    /*public static final String CAFE_DA_MANHA = "Café da Manhã";  
    public static final String ALMOCO = "Almoço";  
    public static final String JANTA = "Janta";
    public static final String LANCHE = "Lanche";  
    public static final String BEBIDA = "Bebida";
    
    
    public TipoConsumivel(javax.swing.JComboBox comboBox) {
        lc = new ArrayList<>();
        lc.add(CAFE_DA_MANHA);
        lc.add(ALMOCO);
        lc.add(JANTA);
        lc.add(LANCHE);
        lc.add(BEBIDA);
        comboBox.setModel(new DefaultComboBoxModel(lc.toArray()));
    }*/

    
}
