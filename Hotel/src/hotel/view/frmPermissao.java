package hotel.view;

import hotel.controller.AplicacaoController;
import hotel.controller.BotaoController;
import hotel.controller.PermissaoController;
import hotel.controller.UsuarioController;
import hotel.model.Aplicacao;
import hotel.model.Permissao;
import hotel.model.Usuario;
import hotel.support.DocumentoLimitado;
import hotel.support.LimpaCampos;
import hotel.support.Validacao;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class frmPermissao extends javax.swing.JInternalFrame {
    
    Permissao permissao;
    Aplicacao aplicacao;
    Usuario usuario;
    PermissaoController permissaoController;
    AplicacaoController aplicacaoController;
    UsuarioController usuarioController;
    BotaoController botaoController;
    public frmPermissao() {
        initComponents();
        permissao = new Permissao();
        permissaoController = new PermissaoController();
        aplicacaoController = new AplicacaoController();
        usuarioController = new UsuarioController();
        botaoController = new BotaoController();
        
        setVisibleCodigo(false);
        addVariaveisComboBoxTela(cmbTela);
        addVariaveisComboBoxUsuario(cmbUsuario);
        permissaoController.popularTabela(tblLista, 0, "");

        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });

        setAba(0);
    }
    
    private void setVisibleCodigo(boolean isVisible) {
        lblCodigo.setVisible(isVisible);
        tfdCodigo.setVisible(isVisible);

        if (isVisible) {
            lblCodigo.setEnabled(!isVisible);
            tfdCodigo.setEnabled(!isVisible);
        }
    }

    private void setAba(int pIndex) {
        tbpPermissao.setSelectedIndex(pIndex);

        habilitar();
    }

    private void limparCampos() {
        permissao = new Permissao();
        LimpaCampos.LimparCampos(pnlCadastro);
    }

    private void habilitar() {
        if (tbpPermissao.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(true);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
        } else {
            if (tblLista.getSelectedRow() != -1) {
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
            }
            btnSalvar.setEnabled(false);
        }
    }

    private void popularTelaCadastro() {
//        tfdCodigo.setText(botao.getCodBotao().toString());
//        tfdBotaoNome.setText(botao.getNomBotaoForm());
//        tfdNome.setText(botao.getNomBotao());
//        for (int i = 0; i < cmbTela.getItemCount(); i++) {
//            if (cmbTela.getItemAt(i).equals(botao.getCodAplicacao().getCodAplicacao() + " " + botao.getCodAplicacao().getNomAplicacao())) {
//                cmbTela.setSelectedIndex(i);
//            }
//        }
//        setVisibleCodigo(true);
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
                listUsuarioString.add(u.getCodUsuario()+ " " + u.getDesLogin());
            }
            comboBox.setModel(new DefaultComboBoxModel(listUsuarioString.toArray()));
            String split[] = cmbUsuario.getSelectedItem().toString().split(" ");
            usuario = usuarioController.getReadId((Integer.parseInt(split[0])));
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
        botaoController.popularTabela(tblBotoes, 3, aplicacao.getCodAplicacao().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        pnlListagem = new javax.swing.JPanel();
        pnlDetalhe = new javax.swing.JPanel();
        tfdPesquisa = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        pnlOpcao = new javax.swing.JPanel();
        rbNome = new javax.swing.JRadioButton();
        rbCodigo = new javax.swing.JRadioButton();
        btnPesquisa = new javax.swing.JButton();
        scpLista = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
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

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scpBotoes.setViewportView(tblBotoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpPermissao.addTab("Adicionar", pnlCadastro);

        pnlListagem.setBackground(new java.awt.Color(255, 255, 255));

        pnlDetalhe.setBackground(new java.awt.Color(255, 255, 255));

        tfdPesquisa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdPesquisa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdPesquisaKeyTyped(evt);
            }
        });

        lblPesquisa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPesquisa.setText("Nome:");

        pnlOpcao.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pesquisa Detalhada ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbNome.setBackground(new java.awt.Color(255, 255, 255));
        rbNome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbNome.setSelected(true);
        rbNome.setText("Por nome");
        rbNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNomeActionPerformed(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(255, 255, 255));
        rbCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbCodigo.setText("Por código");
        rbCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcaoLayout = new javax.swing.GroupLayout(pnlOpcao);
        pnlOpcao.setLayout(pnlOpcaoLayout);
        pnlOpcaoLayout.setHorizontalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbCodigo))
        );
        pnlOpcaoLayout.setVerticalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNome)
                    .addComponent(rbCodigo))
                .addContainerGap())
        );

        btnPesquisa.setBackground(new java.awt.Color(12, 91, 160));
        btnPesquisa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPesquisa.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisa.setText("Pesquisar");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetalheLayout = new javax.swing.GroupLayout(pnlDetalhe);
        pnlDetalhe.setLayout(pnlDetalheLayout);
        pnlDetalheLayout.setHorizontalGroup(
            pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlOpcao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPesquisa)
                    .addComponent(tfdPesquisa))
                .addGap(18, 18, 18)
                .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 289, Short.MAX_VALUE))
        );
        pnlDetalheLayout.setVerticalGroup(
            pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisa))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tblLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "CNPJ"
            }
        ));
        scpLista.setViewportView(tblLista);

        javax.swing.GroupLayout pnlListagemLayout = new javax.swing.GroupLayout(pnlListagem);
        pnlListagem.setLayout(pnlListagemLayout);
        pnlListagemLayout.setHorizontalGroup(
            pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListagemLayout.createSequentialGroup()
                .addGroup(pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlListagemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scpLista)))
                .addContainerGap())
        );
        pnlListagemLayout.setVerticalGroup(
            pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetalhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpPermissao.addTab("Listagem", pnlListagem);

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

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(12, 91, 160));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(12, 91, 160));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/trash.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalvar)
            .addComponent(btnEditar)
            .addComponent(btnExcluir)
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

    private void tfdPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdPesquisaKeyTyped
        char vChar = evt.getKeyChar();
        if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
            if (rbCodigo.isSelected()) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_tfdPesquisaKeyTyped

    private void rbNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNomeActionPerformed
        lblPesquisa.setText("Nome:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbNomeActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        lblPesquisa.setText("Código:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbCodigoActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        if (rbNome.isSelected()) {
            permissaoController.popularTabela(tblLista, 1, tfdPesquisa.getText());
        } else {
            permissaoController.popularTabela(tblLista, 2, tfdPesquisa.getText());
        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void tbpPermissaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpPermissaoStateChanged
        habilitar();
    }//GEN-LAST:event_tbpPermissaoStateChanged

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if ((Validacao.validarCampos(pnlCadastro) == 0)) {
            boolean isNew = (permissao.getCodPermissao() == null);

//            permissao.setCodBotao();
//            botaoController.save(botao);
//
//            if (!isNew) {
//                JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
//                setVisibleCodigo(false);
//            } else {
//                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
//            }
//
//            botaoController.popularTabela(tblLista, 0, "");
//
//            limparCampos();
//            setAba(1);
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
//        botao = botaoController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
//        popularTelaCadastro();
//        setAba(0);
//        tfdNome.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
//        Object[] options = {"Sim", "Não"};
//        int escolha = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de excluir o registro " + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//        if (escolha == 0) {
//            botaoController.delete(botaoController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString())));
//            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
//            botaoController.popularTabela(tblLista, 0, "");
//            //addVariaveisComboBox(cmbBotao);
//        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void cmbUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUsuarioItemStateChanged
        habilitarUsuario();
    }//GEN-LAST:event_cmbUsuarioItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbTela;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblTela;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpBotoes;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tblBotoes;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpPermissao;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdPesquisa;
    // End of variables declaration//GEN-END:variables
}
