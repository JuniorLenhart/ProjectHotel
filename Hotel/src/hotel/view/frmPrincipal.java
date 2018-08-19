package hotel.view;

import hotel.support.Unit;
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
        mnuCadastro = new javax.swing.JMenu();
        mniPessoa = new javax.swing.JMenuItem();
        mniTipoCama = new javax.swing.JMenuItem();
        mniQuarto = new javax.swing.JMenuItem();
        mniConsumivel = new javax.swing.JMenuItem();
        mniFormaPgto = new javax.swing.JMenuItem();
        mnuLocacao = new javax.swing.JMenu();
        mnuReserva = new javax.swing.JMenu();
        mnuFinanceiro = new javax.swing.JMenu();
        mnuRelatorio = new javax.swing.JMenu();
        mniReserva = new javax.swing.JMenuItem();
        mniLocacao = new javax.swing.JMenuItem();
        mnuUsuario = new javax.swing.JMenu();
        mniUsuario = new javax.swing.JMenuItem();
        mniPermissao = new javax.swing.JMenuItem();
        mniAuditoria = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mniAplicacao = new javax.swing.JMenuItem();
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

        mniPessoa.setText("Pessoa");
        mniPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPessoaActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniPessoa);

        mniTipoCama.setText("Tipo de Cama");
        mniTipoCama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTipoCamaActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniTipoCama);

        mniQuarto.setText("Quarto");
        mniQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQuartoActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniQuarto);

        mniConsumivel.setText("Consumível");
        mniConsumivel.setToolTipText("");
        mniConsumivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniConsumivelActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniConsumivel);

        mniFormaPgto.setText("Forma de Pagamento");
        mniFormaPgto.setActionCommand("Tipo Mídia");
        mniFormaPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFormaPgtoActionPerformed(evt);
            }
        });
        mnuCadastro.add(mniFormaPgto);

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

        mniUsuario.setText("Cadastro");
        mnuUsuario.add(mniUsuario);

        mniPermissao.setText("Permissão");
        mnuUsuario.add(mniPermissao);

        mniAuditoria.setText("Auditoria");
        mnuUsuario.add(mniAuditoria);

        mnbPrincipal.add(mnuUsuario);

        mnuAjuda.setText("Ajuda");

        mniAplicacao.setText("Cadastro de Aplicação");
        mnuAjuda.add(mniAplicacao);

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

    private void mniConsumivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniConsumivelActionPerformed
        abrirTela(new frmConsumivel());
    }//GEN-LAST:event_mniConsumivelActionPerformed

    private void mniTipoCamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTipoCamaActionPerformed
        abrirTela(new frmTipoCama());
    }//GEN-LAST:event_mniTipoCamaActionPerformed

    private void mniFormaPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFormaPgtoActionPerformed
        abrirTela(new frmFormaPagamento());
    }//GEN-LAST:event_mniFormaPgtoActionPerformed

    private void mniQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQuartoActionPerformed
        abrirTela(new frmQuarto());
    }//GEN-LAST:event_mniQuartoActionPerformed

    private void mniPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPessoaActionPerformed
        abrirTela(new frmPessoa());
    }//GEN-LAST:event_mniPessoaActionPerformed

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
    private javax.swing.JMenuItem mniAplicacao;
    private javax.swing.JMenuItem mniAuditoria;
    private javax.swing.JMenuItem mniConsumivel;
    private javax.swing.JMenuItem mniFormaPgto;
    private javax.swing.JMenuItem mniLocacao;
    private javax.swing.JMenuItem mniPermissao;
    private javax.swing.JMenuItem mniPessoa;
    private javax.swing.JMenuItem mniQuarto;
    private javax.swing.JMenuItem mniReserva;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniTipoCama;
    private javax.swing.JMenuItem mniUsuario;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenu mnuFinanceiro;
    private javax.swing.JMenu mnuLocacao;
    private javax.swing.JMenu mnuRelatorio;
    private javax.swing.JMenu mnuReserva;
    private javax.swing.JMenu mnuUsuario;
    // End of variables declaration//GEN-END:variables
}
