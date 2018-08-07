package hotel.apresentacao;

import hotel.apoio.Unit;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

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
        mnuCadastro = new javax.swing.JMenu();
        mniFilme = new javax.swing.JMenuItem();
        mniFormaPgto = new javax.swing.JMenuItem();
        mniGenero = new javax.swing.JMenuItem();
        mniPessoa = new javax.swing.JMenuItem();
        mniTipoMidia = new javax.swing.JMenuItem();
        mnuLocacao = new javax.swing.JMenu();
        mnuReserva = new javax.swing.JMenu();
        mnuFinanceiro = new javax.swing.JMenu();
        mnuRelatorio = new javax.swing.JMenu();
        mniReserva = new javax.swing.JMenuItem();
        mniLocacao = new javax.swing.JMenuItem();
        mnuUsuario = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mniSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Integrador");

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

        mnuCadastro.setText("Cadastros");

        mniFilme.setText("Pessoa");
        mnuCadastro.add(mniFilme);

        mniFormaPgto.setText("Tipo de Quarto");
        mnuCadastro.add(mniFormaPgto);

        mniGenero.setText("Quarto");
        mnuCadastro.add(mniGenero);

        mniPessoa.setText("Consumível");
        mniPessoa.setToolTipText("");
        mnuCadastro.add(mniPessoa);

        mniTipoMidia.setText("Forma de Pagamento");
        mniTipoMidia.setActionCommand("Tipo Mídia");
        mnuCadastro.add(mniTipoMidia);

        mnbPrincipal.add(mnuCadastro);

        mnuLocacao.setLabel("Locação");
        mnbPrincipal.add(mnuLocacao);

        mnuReserva.setLabel("Reserva");
        mnbPrincipal.add(mnuReserva);

        mnuFinanceiro.setText("Financeiro");
        mnbPrincipal.add(mnuFinanceiro);

        mnuRelatorio.setText("Relatórios");

        mniReserva.setText("Reserva");
        mnuRelatorio.add(mniReserva);

        mniLocacao.setText("Locação");
        mnuRelatorio.add(mniLocacao);

        mnbPrincipal.add(mnuRelatorio);

        mnuUsuario.setText("Usuário");

        jMenuItem2.setText("Cadastro");
        mnuUsuario.add(jMenuItem2);

        jMenuItem3.setText("Permissão");
        mnuUsuario.add(jMenuItem3);

        jMenuItem4.setText("Auditoria");
        mnuUsuario.add(jMenuItem4);

        mnbPrincipal.add(mnuUsuario);

        mnuAjuda.setText("Ajuda");

        jMenuItem1.setText("Cadastro de Aplicação");
        mnuAjuda.add(jMenuItem1);

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
    
    public void retornaTelaRedimensionada(JInternalFrame frame)
    {
        Dimension desktopSize = dkpSistema.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                                         (desktopSize.height- jInternalFrameSize.height)/2);
        dkpSistema.add(frame);
        BasicInternalFrameUI bi = (BasicInternalFrameUI)frame.getUI();
        bi.setNorthPane(null);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dkpSistema;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuBar mnbPrincipal;
    private javax.swing.JMenuItem mniFilme;
    private javax.swing.JMenuItem mniFormaPgto;
    private javax.swing.JMenuItem mniGenero;
    private javax.swing.JMenuItem mniLocacao;
    private javax.swing.JMenuItem mniPessoa;
    private javax.swing.JMenuItem mniReserva;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniTipoMidia;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenu mnuFinanceiro;
    private javax.swing.JMenu mnuLocacao;
    private javax.swing.JMenu mnuRelatorio;
    private javax.swing.JMenu mnuReserva;
    private javax.swing.JMenu mnuUsuario;
    // End of variables declaration//GEN-END:variables
}
