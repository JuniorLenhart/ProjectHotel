package hotel.view;

import hotel.controller.AplicacaoController;
import hotel.controller.AplicacaoBotaoController;
import hotel.controller.PermissaoController;
import hotel.controller.UsuarioController;
import hotel.model.Aplicacao;
import hotel.model.AplicacaoBotao;
import hotel.model.Permissao;
import hotel.model.Usuario;
import hotel.support.LimpaCampos;
import hotel.support.Validacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class frmPermissao extends javax.swing.JInternalFrame {

    Permissao permissao;
    Aplicacao aplicacao;
    Usuario usuario;
    AplicacaoBotao aplicacaoBotao;
    PermissaoController permissaoController;
    AplicacaoController aplicacaoController;
    UsuarioController usuarioController;
    AplicacaoBotaoController aplicacaoBotaoController;

    boolean isSalvar = false;

    public frmPermissao() {
        initComponents();
        permissao = new Permissao();
        permissaoController = new PermissaoController();
        aplicacaoController = new AplicacaoController();
        usuarioController = new UsuarioController();
        aplicacaoBotao = new AplicacaoBotao();
        aplicacaoBotaoController = new AplicacaoBotaoController();

        setVisibleCodigo(false);
        addVariaveisComboBoxTela(cmbTela);
        addVariaveisComboBoxUsuario(cmbUsuario);

        loadPermission();
    }

    private void loadPermission() {
        isSalvar = PermissaoController.hasPermission("frmPermissao", "btnSalvar");
        habilitar();
    }

    private void setVisibleCodigo(boolean isVisible) {
        lblCodigo.setVisible(isVisible);
        tfdCodigo.setVisible(isVisible);

        if (isVisible) {
            lblCodigo.setEnabled(!isVisible);
            tfdCodigo.setEnabled(!isVisible);
        }
    }

    private void limparCampos() {
        permissao = new Permissao();
        LimpaCampos.LimparCampos(pnlCadastro);
    }

    private void habilitar() {
        if (tbpPermissao.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(isSalvar);
        } else {
            btnSalvar.setEnabled(false);
        }
    }

    private void addVariaveisComboBoxTela(javax.swing.JComboBox comboBox) {
        List<Aplicacao> listAplicacao = aplicacaoController.getReadAllAtivos();
        if (!listAplicacao.isEmpty()) {
            List<String> listAplicacaoString = new ArrayList<>();
            for (Aplicacao a : listAplicacao) {
                listAplicacaoString.add(a.getCodAplicacao() + " " + a.getNomAplicacao());
            }
            comboBox.setModel(new DefaultComboBoxModel(listAplicacaoString.toArray()));
            String split[] = cmbTela.getSelectedItem().toString().split(" ");
            aplicacao = aplicacaoController.getReadId((Integer.parseInt(split[0])));
        }
    }

    private void addVariaveisComboBoxUsuario(javax.swing.JComboBox comboBox) {
        List<Usuario> listUsuario = usuarioController.getReadAllAtivos();
        if (!listUsuario.isEmpty()) {
            List<String> listUsuarioString = new ArrayList<>();
            for (Usuario u : listUsuario) {
                listUsuarioString.add(u.getCodUsuario() + " " + u.getDesLogin());
            }
            comboBox.setModel(new DefaultComboBoxModel(listUsuarioString.toArray()));
            String split[] = cmbUsuario.getSelectedItem().toString().split(" ");
            usuario = usuarioController.getReadId((Integer.parseInt(split[0])));
            habilitarUsuario();
        }
    }

    private void habilitarUsuario() {
        if (cmbTela.getSelectedIndex() != -1) {
            String split[] = cmbTela.getSelectedItem().toString().split(" ");
            aplicacao = aplicacaoController.getReadId((Integer.parseInt(split[0])));
        } else {
            aplicacao = null;
        }
        if (cmbUsuario.getSelectedIndex() != -1) {
            String split[] = cmbUsuario.getSelectedItem().toString().split(" ");
            usuario = usuarioController.getReadId((Integer.parseInt(split[0])));
        } else {
            usuario = null;
        }
        if (aplicacao != null && usuario != null) {
            carregaBotoes();
        }
    }

    private void carregaBotoes() {
        aplicacaoBotaoController.popularTabela(tblBotoes, 3, aplicacao.getCodAplicacao().toString() + ";" + usuario.getCodUsuario());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpPermissao = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        lblTela = new javax.swing.JLabel();
        cmbTela = new javax.swing.JComboBox<>();
        cmbUsuario = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        scpBotoes = new javax.swing.JScrollPane();
        tblBotoes = new javax.swing.JTable();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        tbpPermissao.setBackground(new java.awt.Color(255, 255, 255));
        tbpPermissao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbpPermissao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpPermissaoStateChanged(evt);
            }
        });

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Cadastro de Permissões ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsuario.setText("<html>Usuário<font color='red'><b>*</b></font>:</html>");

        lblTela.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTela.setForeground(new java.awt.Color(102, 102, 102));
        lblTela.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTela.setText("<html>Tela<font color='red'><b>*</b></font>:</html>");

        cmbTela.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbTela.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTelaItemStateChanged(evt);
            }
        });

        cmbUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUsuarioItemStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Botões ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblBotoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scpBotoes.setViewportView(tblBotoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpBotoes)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scpBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCodigo)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTela, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdCodigo)
                    .addComponent(cmbTela, javax.swing.GroupLayout.Alignment.TRAILING, 0, 290, Short.MAX_VALUE)
                    .addComponent(cmbUsuario, 0, 290, Short.MAX_VALUE))
                .addContainerGap(137, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpPermissao.addTab("Adicionar", pnlCadastro);

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

        btnFechar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(12, 91, 160));
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/close.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFechar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalvar)
            .addComponent(btnFechar)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbpPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpPermissao)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTelaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTelaItemStateChanged
        habilitarUsuario();
    }//GEN-LAST:event_cmbTelaItemStateChanged

    private void tbpPermissaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpPermissaoStateChanged
        habilitar();
    }//GEN-LAST:event_tbpPermissaoStateChanged

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if ((Validacao.validarCampos(pnlCadastro) == 0)) {
            List<Permissao> permissoes = new ArrayList<>();

            for (int i = 0; i < tblBotoes.getModel().getRowCount(); i++) {
                AplicacaoBotao botao = aplicacaoBotaoController.getReadId(Integer.parseInt(tblBotoes.getModel().getValueAt(i, 0).toString()));
                if ((boolean) tblBotoes.getModel().getValueAt(i, 2) == true) {
                    if (!permissaoController.isInserted(botao.getCodAplicacaoBotao(), usuario.getCodUsuario())) {
                        permissao.setAplicacaoBotao(botao);
                        permissao.setUsuario(usuario);

                        permissoes.add(permissao);
                        permissao = new Permissao();
                    }
                } else {
                    if (permissaoController.isInserted(botao.getCodAplicacaoBotao(), usuario.getCodUsuario())) {
                        permissao = permissaoController.readByAplicacaoBotaoAndUsuario(usuario.getCodUsuario(), botao.getCodAplicacaoBotao());
                        permissaoController.delete(permissao);
                        permissao = new Permissao();
                    }
                }
            }

            permissaoController.saveAll(permissoes);
            permissaoController.loadPermission(usuario);

            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");

            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void cmbUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUsuarioItemStateChanged
        habilitarUsuario();
    }//GEN-LAST:event_cmbUsuarioItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbTela;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblTela;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane scpBotoes;
    private javax.swing.JTable tblBotoes;
    private javax.swing.JTabbedPane tbpPermissao;
    private javax.swing.JTextField tfdCodigo;
    // End of variables declaration//GEN-END:variables
}
