package hotel.controller;

import hotel.support.ComboItens;
import hotel.support.ConexaoBD;
import java.sql.ResultSet;
import javax.swing.JComboBox;

public class CombosDAO {

    ResultSet FResultSet = null;

    public void popularCombo(String pSQL, JComboBox pCombo) {

        ComboItens i = new ComboItens();
        i.setCodigo(0);
        i.setDescricao("Selecione...");
        pCombo.addItem(i);

        try {
            FResultSet = new ConexaoBD().getConnection().createStatement().executeQuery(pSQL);

            if (FResultSet.isBeforeFirst()) {
                while (FResultSet.next()) {
                    i = new ComboItens();
                    i.setCodigo(FResultSet.getInt(1));
                    i.setDescricao(FResultSet.getString(2));

                    pCombo.addItem(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular combo = " + e.toString());
        }
    }

    public void definirItemCombo(JComboBox pCombo, ComboItens pItem) {
        for (int i = 0; i < pCombo.getItemCount(); i++) {
            if (((ComboItens) pCombo.getItemAt(i)).getCodigo() == (pItem.getCodigo())) {
                pCombo.setSelectedIndex(i);
                return;
            }
        }
    }
}
