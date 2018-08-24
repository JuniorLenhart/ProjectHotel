package hotel.view;

import hotel.controller.UsuarioController;
import hotel.model.Usuario;
import hotel.support.Criptografia;
import hotel.support.Validacao;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class frmCadastrarNovaSenha extends javax.swing.JFrame {

    String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
    Usuario usuario;
    UsuarioController usuarioController;

    public frmCadastrarNovaSenha(Usuario usuario) {
        initComponents();
        setLocationRelativeTo(this);
        setResizable(false);
        this.usuario = usuario;
        usuarioController = new UsuarioController();
    }

    public void salvar() {
        if (Validacao.validarCampos(pnlPai) == 0) {
            String senha = tfdSenha.getText();
            String confirmaSenha = tfdConfirmarSenha.getText();
            if (senha.equals(confirmaSenha)) {
                if (senha.matches(pattern)) {
                    usuario.setDesSenha(Criptografia.criptografar(senha));
                    usuarioController.save(usuario);
                    JOptionPane.showMessageDialog(this, "Senha alterada com sucesso");
                    this.dispose();
                    new frmPrincipal(usuario).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Senha não atende a todas as características!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senhas não são iguais!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campos obrigatórios não preenchidos!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPai = new javax.swing.JPanel();
        lblSenha = new javax.swing.JLabel();
        tfdSenha = new javax.swing.JPasswordField();
        lblConfirmarSenha = new javax.swing.JLabel();
        tfdConfirmarSenha = new javax.swing.JPasswordField();
        pnlInstrucoes = new javax.swing.JPanel();
        lblInstrucao1 = new javax.swing.JLabel();
        lblInstrucao2 = new javax.swing.JLabel();
        lblInstrucao3 = new javax.swing.JLabel();
        lblInstrucao4 = new javax.swing.JLabel();
        lblInstrucao5 = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        cbxMostrarSenha = new javax.swing.JCheckBox();

        lblSenha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSenha.setText("Nova senha:");

        tfdSenha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdSenhaKeyPressed(evt);
            }
        });

        lblConfirmarSenha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblConfirmarSenha.setText("Confirmar senha:");

        tfdConfirmarSenha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdConfirmarSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdSenhaKeyPressed(evt);
            }
        });

        lblInstrucao1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstrucao1.setText("* Pelo menos um número.");

        lblInstrucao2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstrucao2.setText("* Pelo menos uma letra maiúscula.");

        lblInstrucao3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstrucao3.setText("* Pelo menos uma letra minúscula.");
        lblInstrucao3.setToolTipText("");

        lblInstrucao4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstrucao4.setText("* Pelo menos um caracter especial.");
        lblInstrucao4.setToolTipText("");

        lblInstrucao5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstrucao5.setText("* Tamanho mínimo de 6 caracteres.");
        lblInstrucao5.setToolTipText("");

        javax.swing.GroupLayout pnlInstrucoesLayout = new javax.swing.GroupLayout(pnlInstrucoes);
        pnlInstrucoes.setLayout(pnlInstrucoesLayout);
        pnlInstrucoesLayout.setHorizontalGroup(
            pnlInstrucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstrucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInstrucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInstrucao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInstrucao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInstrucao3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInstrucao4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInstrucao5, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInstrucoesLayout.setVerticalGroup(
            pnlInstrucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstrucoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInstrucao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstrucao2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstrucao3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstrucao4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstrucao5))
        );

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(12, 91, 160));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/save.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalvar)
        );

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTitulo.setText("Cadastro de nova senha");

        cbxMostrarSenha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbxMostrarSenha.setText("Mostrar senha");
        cbxMostrarSenha.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbxMostrarSenhaStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlPaiLayout = new javax.swing.GroupLayout(pnlPai);
        pnlPai.setLayout(pnlPaiLayout);
        pnlPaiLayout.setHorizontalGroup(
            pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInstrucoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPaiLayout.createSequentialGroup()
                        .addGroup(pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSenha)
                            .addComponent(lblConfirmarSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(tfdConfirmarSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxMostrarSenha))
                    .addComponent(lblTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPaiLayout.setVerticalGroup(
            pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPaiLayout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInstrucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(tfdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMostrarSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmarSenha)
                    .addComponent(tfdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tfdSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            salvar();
        }
    }//GEN-LAST:event_tfdSenhaKeyPressed

    private void cbxMostrarSenhaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbxMostrarSenhaStateChanged
        if (cbxMostrarSenha.isSelected()) {
            tfdSenha.setEchoChar((char) 0);
            tfdConfirmarSenha.setEchoChar((char) 0);
        } else {
            tfdSenha.setEchoChar('*');
            tfdConfirmarSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_cbxMostrarSenhaStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox cbxMostrarSenha;
    private javax.swing.JLabel lblConfirmarSenha;
    private javax.swing.JLabel lblInstrucao1;
    private javax.swing.JLabel lblInstrucao2;
    private javax.swing.JLabel lblInstrucao3;
    private javax.swing.JLabel lblInstrucao4;
    private javax.swing.JLabel lblInstrucao5;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlInstrucoes;
    private javax.swing.JPanel pnlPai;
    private javax.swing.JPasswordField tfdConfirmarSenha;
    private javax.swing.JPasswordField tfdSenha;
    // End of variables declaration//GEN-END:variables
}
