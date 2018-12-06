package hotel.view;

import hotel.controller.PermissaoController;
import hotel.model.Parametro;
import hotel.model.Usuario;
import hotel.socketclient.Client;
import hotel.support.Unit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

public class frmPrincipal extends javax.swing.JFrame {

    public frmPrincipal(Usuario usuario) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);

        lblUsuarioLogado.setText(lblUsuarioLogado.getText() + usuario.getPessoa().getNomPessoa());

        new PermissaoController().loadPermission(usuario);

        Parametro.CLIENT = new Client(usuario, "127.0.0.1", 5000);
    }

    public void abrirTela(JInternalFrame pInternalFrame) {
        if (PermissaoController.hasPermission(pInternalFrame.getClass().getName().substring(11))) {
            dkpSistema.add(pInternalFrame);
            Unit.setPosition(pInternalFrame);
            pInternalFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem acesso neste formulário!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dkpSistema = dkpSistema = new javax.swing.JDesktopPane()
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Double width = screenSize.getWidth();
            Double height = screenSize.getHeight();
            Image img = new ImageIcon(getClass().getResource("/hotel/images/background_desktoppane.png")).getImage();

            Image newimage = img.getScaledInstance(width.intValue(), height.intValue(), Image.SCALE_SMOOTH);

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(newimage, 0, 0, this);
            }
        };
        lblUsuarioLogado = new javax.swing.JLabel();
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
        mniBotao = new javax.swing.JMenuItem();
        mniXML = new javax.swing.JMenuItem();
        mniBackupRestore = new javax.swing.JMenuItem();
        mniNovidade = new javax.swing.JMenuItem();
        mniSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Integrador");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        dkpSistema.setBackground(new java.awt.Color(255, 255, 255));

        lblUsuarioLogado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUsuarioLogado.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioLogado.setText("Usuário: ");

        dkpSistema.setLayer(lblUsuarioLogado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dkpSistemaLayout = new javax.swing.GroupLayout(dkpSistema);
        dkpSistema.setLayout(dkpSistemaLayout);
        dkpSistemaLayout.setHorizontalGroup(
            dkpSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dkpSistemaLayout.createSequentialGroup()
                .addContainerGap(619, Short.MAX_VALUE)
                .addComponent(lblUsuarioLogado)
                .addContainerGap())
        );
        dkpSistemaLayout.setVerticalGroup(
            dkpSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dkpSistemaLayout.createSequentialGroup()
                .addContainerGap(389, Short.MAX_VALUE)
                .addComponent(lblUsuarioLogado)
                .addContainerGap())
        );

        mnbPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        mnuCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/add-file.png"))); // NOI18N
        mnuCadastro.setText("Cadastros");
        mnuCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        mnuLocacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/locacao.png"))); // NOI18N
        mnuLocacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mnuLocacao.setLabel("Locação");
        mnuLocacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuLocacaoMouseClicked(evt);
            }
        });
        mnbPrincipal.add(mnuLocacao);

        mnuReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/reserva.png"))); // NOI18N
        mnuReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mnuReserva.setLabel("Reserva");
        mnuReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuReservaMouseClicked(evt);
            }
        });
        mnbPrincipal.add(mnuReserva);

        mnuFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/financeiro.png"))); // NOI18N
        mnuFinanceiro.setText("Financeiro");
        mnuFinanceiro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mnuFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuFinanceiroMouseClicked(evt);
            }
        });
        mnbPrincipal.add(mnuFinanceiro);

        mnuRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/report.png"))); // NOI18N
        mnuRelatorio.setText("Relatórios");
        mnuRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mniReserva.setText("Reserva");
        mnuRelatorio.add(mniReserva);

        mniLocacao.setText("Locação");
        mnuRelatorio.add(mniLocacao);

        mnbPrincipal.add(mnuRelatorio);

        mnuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/man.png"))); // NOI18N
        mnuUsuario.setText("Usuário");
        mnuUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mniUsuario.setText("Cadastro");
        mniUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuarioActionPerformed(evt);
            }
        });
        mnuUsuario.add(mniUsuario);

        mniPermissao.setText("Permissão");
        mniPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPermissaoActionPerformed(evt);
            }
        });
        mnuUsuario.add(mniPermissao);

        mniAuditoria.setText("Auditoria");
        mniAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAuditoriaActionPerformed(evt);
            }
        });
        mnuUsuario.add(mniAuditoria);

        mnbPrincipal.add(mnuUsuario);

        mnuAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/question.png"))); // NOI18N
        mnuAjuda.setText("Ajuda");
        mnuAjuda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mniAplicacao.setText("Cadastro de Aplicação");
        mniAplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAplicacaoActionPerformed(evt);
            }
        });
        mnuAjuda.add(mniAplicacao);

        mniBotao.setText("Cadastro de Botões");
        mniBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBotaoActionPerformed(evt);
            }
        });
        mnuAjuda.add(mniBotao);

        mniXML.setText("Importar/Exportar de Tabelas (XML)");
        mniXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniXMLActionPerformed(evt);
            }
        });
        mnuAjuda.add(mniXML);

        mniBackupRestore.setText("Importar/Exportar Banco/Aplicação");
        mniBackupRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBackupRestoreActionPerformed(evt);
            }
        });
        mnuAjuda.add(mniBackupRestore);

        mniNovidade.setText("Notas de Atualizações");
        mniNovidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNovidadeActionPerformed(evt);
            }
        });
        mnuAjuda.add(mniNovidade);

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

    private void mniUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuarioActionPerformed
        abrirTela(new frmUsuario());
    }//GEN-LAST:event_mniUsuarioActionPerformed

    private void mniAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAuditoriaActionPerformed
        abrirTela(new frmAuditoria());
    }//GEN-LAST:event_mniAuditoriaActionPerformed

    private void mnuReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuReservaMouseClicked
        abrirTela(new frmReserva());
    }//GEN-LAST:event_mnuReservaMouseClicked

    private void mniAplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAplicacaoActionPerformed
        abrirTela(new frmAplicacao());
    }//GEN-LAST:event_mniAplicacaoActionPerformed

    private void mniBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBotaoActionPerformed
        abrirTela(new frmAplicacaoBotoes());
    }//GEN-LAST:event_mniBotaoActionPerformed

    private void mniPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPermissaoActionPerformed
        abrirTela(new frmPermissao());
    }//GEN-LAST:event_mniPermissaoActionPerformed

    private void mnuFinanceiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuFinanceiroMouseClicked
        abrirTela(new frmFinanceiro());
    }//GEN-LAST:event_mnuFinanceiroMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Parametro.CLIENT.send(null);
        Parametro.CLIENT.stop();
    }//GEN-LAST:event_formWindowClosed

    private void mnuLocacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuLocacaoMouseClicked
        abrirTela(new frmLocacao());
    }//GEN-LAST:event_mnuLocacaoMouseClicked

    private void mniXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniXMLActionPerformed
        abrirTela(new frmXML());
    }//GEN-LAST:event_mniXMLActionPerformed

    private void mniBackupRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBackupRestoreActionPerformed
        abrirTela(new frmBackupEscolha());
    }//GEN-LAST:event_mniBackupRestoreActionPerformed

    private void mniNovidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNovidadeActionPerformed
        abrirTela(new frmNovidade());
    }//GEN-LAST:event_mniNovidadeActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new frmNovidadeDetalhe(this, true, null, true);
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dkpSistema;
    private javax.swing.JLabel lblUsuarioLogado;
    private javax.swing.JMenuBar mnbPrincipal;
    private javax.swing.JMenuItem mniAplicacao;
    private javax.swing.JMenuItem mniAuditoria;
    private javax.swing.JMenuItem mniBackupRestore;
    private javax.swing.JMenuItem mniBotao;
    private javax.swing.JMenuItem mniConsumivel;
    private javax.swing.JMenuItem mniFormaPgto;
    private javax.swing.JMenuItem mniLocacao;
    private javax.swing.JMenuItem mniNovidade;
    private javax.swing.JMenuItem mniPermissao;
    private javax.swing.JMenuItem mniPessoa;
    private javax.swing.JMenuItem mniQuarto;
    private javax.swing.JMenuItem mniReserva;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniTipoCama;
    private javax.swing.JMenuItem mniUsuario;
    private javax.swing.JMenuItem mniXML;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenu mnuCadastro;
    private javax.swing.JMenu mnuFinanceiro;
    private javax.swing.JMenu mnuLocacao;
    private javax.swing.JMenu mnuRelatorio;
    private javax.swing.JMenu mnuReserva;
    private javax.swing.JMenu mnuUsuario;
    // End of variables declaration//GEN-END:variables
}
