package hotel;

import hotel.apresentacao.frmPrincipal;

public class Hotel {

    public static void main(String[] args) {
        new frmPrincipal().setVisible(true);
//        if (ConexaoBD.getInstance().getConnection() != null) {
//            new frmLogin().setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Falha ao acessar o sistema!");
//        }
    }
}
