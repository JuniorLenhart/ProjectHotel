package hotel.apresentacao;

import hotel.apoio.Unit;
import javax.swing.*;

public class frmPrincipal extends javax.swing.JFrame {

    public frmPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void abrirTela(JInternalFrame pInternalFrame) {
        dkpSistema.add(pInternalFrame);
        Unit.setPosition(pInternalFrame);
        pInternalFrame.setVisible(true);     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dkpSistema = new javax.swing.JDesktopPane(){
        };
        mnbPrincipal = new javax.swing.JMenuBar();
        mnuLocacao = new javax.swing.JMenu();
        mnuReserva = new javax.swing.JMenu();
        mnuCadastro = new javax.swing.JMenu();
        mniFilme = new javax.swing.JMenuItem();
        mniFormaPgto = new javax.swing.JMenuItem();
        mniGenero = new javax.swing.JMenuItem();
        mniPessoa = new javax.swing.JMenuItem();
        mniTipoMidia = new javax.swing.JMenuItem();
        mniUsuario = new javax.swing.JMenuItem();
        mnuRelatorio = new javax.swing.JMenu();
        mniReserva = new javax.swing.JMenuItem();
        mniLocacao = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mniSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Store - Locadora");

        dkpSistema.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout dkpSistemaLayout = new javax.swing.GroupLayout(dkpSistema);
        dkpSistema.setLayout(dkpSistemaLayout);
        dkpSistemaLayout.setHorizontalGroup(
            dkpSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 704, Short.MAX_VALUE)
        );
        dkpSistemaLayout.setVerticalGroup(
            dkpSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        mnuLocacao.setLabel("Locação");
        mnbPrincipal.add(mnuLocacao);

        mnuReserva.setLabel("Reserva");
        mnbPrincipal.add(mnuReserva);

        mnuCadastro.setText("Cadastros");

        mniFilme.setText("Filme");
        mnuCadastro.add(mniFilme);

        mniFormaPgto.setText("Forma de Pagamento");
        mnuCadastro.add(mniFormaPgto);

        mniGenero.setText("Gênero");
        mnuCadastro.add(mniGenero);

        mniPessoa.setText("Pessoa");
        mniPessoa.setToolTipText("");
        mnuCadastro.add(mniPessoa);

        mniTipoMidia.setText("Tipo de Mídia");
        mniTipoMidia.setActionCommand("Tipo Mídia");
        mnuCadastro.add(mniTipoMidia);

        mniUsuario.setText("Usuário");
        mnuCadastro.add(mniUsuario);

        mnbPrincipal.add(mnuCadastro);

        mnuRelatorio.setText("Relatórios");

        mniReserva.setText("Reserva");
        mnuRelatorio.add(mniReserva);

        mniLocacao.setText("Locação");
        mnuRelatorio.add(mniLocacao);

        mnbPrincipal.add(mnuRelatorio);

        mnuAjuda.setText("Ajuda");

        mniSair.setText("Sair");
        mniSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSairActionPerformed(evt);
            }
        });
        mnuAjuda.add(mniSair);

        mnbPrincipal.add(mnuAjuda);

        setJMenuBar(mnbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dkpSistema, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dkpSistema, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mniSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniSairActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dkpSistema;
    private javax.swing.JMenuBar mnbPrincipal;
    private javax.swing.JMenuItem mniFilme;
    private javax.swing.JMenuItem mniFormaPgto;
    private javax.swing.JMenuItem mniGenero;
    private javax.swing.JMenuItem mniLocacao;
    private javax.swing.JMenuItem mniPessoa;
    private javax.swing.JMenuItem mniReserva;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniTipoMidia;
    private javax.swing.JMenuItem mniUsuario;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenu mnuLocacao;
    private javax.swing.JMenu mnuRelatorio;
    private javax.swing.JMenu mnuReserva;
    // End of variables declaration//GEN-END:variables
}
