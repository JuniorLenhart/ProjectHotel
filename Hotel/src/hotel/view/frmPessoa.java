package hotel.view;

import hotel.controller.PermissaoController;
import hotel.controller.PessoaController;
import hotel.model.Locacao;
import hotel.model.Pessoa;
import hotel.support.DocumentoLimitado;
import hotel.support.Endereco;
import hotel.support.Formatacao;
import hotel.support.LimpaCampos;
import hotel.support.Localizacao;
import hotel.support.Validacao;
import java.awt.event.KeyEvent;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class frmPessoa extends javax.swing.JInternalFrame {

    Pessoa pessoa;
    PessoaController pessoaController;

    boolean isSalvar = false;
    boolean isEditar = false;
    boolean isExcluir = false;

    public frmPessoa() {
        iniciaConstrutor();
    }

    public frmPessoa(Pessoa pessoaTitular, Locacao locacao) {
        iniciaConstrutor();

        if (pessoaTitular != null) {
            String[] enderecoSplitado = pessoaTitular.getDesEndereco().split(",");
            tfdRua.setText(enderecoSplitado[0]);
            tfdNumero.setText(enderecoSplitado[1]);
            if (enderecoSplitado.length == 3) {
                tfdComplemento.setText(enderecoSplitado[2]);
            }
        }
    }

    private void setInfoEndereco() {
        String cep = tfdCEP.getText().replace("-", "");
        if (Validacao.validarCep(cep)) {
            Endereco endereco = Localizacao.getEnderecoPorCep(cep);
            tfdCidade.setText(endereco.getLocalidade());
            tfdUF.setText(endereco.getUf());
        } else {
            tfdCidade.setText("");
            tfdUF.setText("");
        }
    }

    private void iniciaConstrutor() {
        initComponents();
        pessoa = new Pessoa();
        pessoaController = new PessoaController();
        setVisibleCodigo(false);

        pessoaController.popularTabela(tblLista, 0, "", -1);

        tfdCidade.setEditable(false);
        tfdUF.setEditable(false);

        tfdNome.setDocument(new DocumentoLimitado(200));
        tfdRua.setDocument(new DocumentoLimitado(100));
        tfdNumero.setDocument(new DocumentoLimitado(4));
        tfdComplemento.setDocument(new DocumentoLimitado(44));
        tfdEmail.setDocument(new DocumentoLimitado(100));

        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });

        loadPermission();
        setAba(0);
    }

    private void loadPermission() {
        isSalvar = PermissaoController.hasPermission("frmPessoa", "btnSalvar");
        isEditar = PermissaoController.hasPermission("frmPessoa", "btnEditar");
        isExcluir = PermissaoController.hasPermission("frmPessoa", "btnExcluir");
    }

    public void setVisibleCodigo(boolean isVisible) {
        lblCodigo.setVisible(isVisible);
        tfdCodigo.setVisible(isVisible);

        if (isVisible) {
            lblCodigo.setEnabled(!isVisible);
            tfdCodigo.setEnabled(!isVisible);
        }
    }

    private void setAba(int pIndex) {
        tbpConsumivel.setSelectedIndex(pIndex);

        habilitar();
    }

    private void limparCampos() {
        pessoa = new Pessoa();
        LimpaCampos.LimparCampos(pnlCadastro);
        LimpaCampos.LimparCampos(pnlDetalhe);
        LimpaCampos.LimparCampos(pnlEndereco);
    }

    private void habilitar() {
        if (tbpConsumivel.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(isSalvar);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 6));
            }

            btnSalvar.setEnabled(false);
            btnEditar.setEnabled(isEditar && lSituacao.equals("Ativo"));
            btnExcluir.setEnabled(isExcluir && lSituacao.equals("Ativo"));
        }
    }

    public void popularTelaCadastro() {
        tfdCodigo.setText(pessoa.getCodPessoa().toString());
        tfdNome.setText(pessoa.getNomPessoa());
        tfdCPF.setText(pessoa.getNumCpf());
        tfdCelular.setText(pessoa.getNumCelular());
        tfdDataNasc.setText(Formatacao.ajustaDataDMA(pessoa.getDtaNasc().toString()));
        tfdEmail.setText(pessoa.getDesEmail());
        tfdTelefone.setText(pessoa.getNumTelefone());
        String[] enderecoSplitado = pessoa.getDesEndereco().split(",");
        tfdCEP.setText(enderecoSplitado[0]);
        tfdUF.setText(enderecoSplitado[1]);
        tfdCidade.setText(enderecoSplitado[2]);
        tfdRua.setText(enderecoSplitado[3]);
        tfdNumero.setText(enderecoSplitado[4]);
        if (enderecoSplitado.length == 6) {
            tfdComplemento.setText(enderecoSplitado[5]);
        }
        setVisibleCodigo(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGrupoRadio = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        tbpConsumivel = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        tfdNome = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblCPF = new javax.swing.JLabel();
        lblDataNasc = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        tfdCPF = new javax.swing.JFormattedTextField();
        tfdDataNasc = new javax.swing.JFormattedTextField();
        pnlEndereco = new javax.swing.JPanel();
        lblRua = new javax.swing.JLabel();
        tfdRua = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        tfdNumero = new javax.swing.JTextField();
        lblComplemento = new javax.swing.JLabel();
        tfdComplemento = new javax.swing.JTextField();
        lblCEP = new javax.swing.JLabel();
        tfdCEP = new javax.swing.JFormattedTextField();
        tfdUF = new javax.swing.JTextField();
        lblUF = new javax.swing.JLabel();
        lblCidade = new javax.swing.JLabel();
        tfdCidade = new javax.swing.JTextField();
        tfdEmail = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        tfdTelefone = new javax.swing.JFormattedTextField();
        lblCelular = new javax.swing.JLabel();
        tfdCelular = new javax.swing.JFormattedTextField();
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

        tbpConsumivel.setBackground(new java.awt.Color(255, 255, 255));
        tbpConsumivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbpConsumivel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpConsumivelStateChanged(evt);
            }
        });

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Cadastro de Pessoa ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNome.setForeground(new java.awt.Color(102, 102, 102));
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNome.setText("<html>Nome<font color='red'><b>*</b></font>:</html>");
        lblNome.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tfdNome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdNomeTyped(evt);
            }
        });

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblCPF.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCPF.setForeground(new java.awt.Color(102, 102, 102));
        lblCPF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCPF.setText("<html>CPF<font color='red'><b>*</b></font>:</html>");

        lblDataNasc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataNasc.setForeground(new java.awt.Color(102, 102, 102));
        lblDataNasc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataNasc.setText("<html>Data de nascimento<font color='red'><b>*</b></font>:</html>");
        lblDataNasc.setToolTipText("");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(102, 102, 102));
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setText("<html>E-mail<font color='red'><b>*</b></font>:</html>");

        tfdCPF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCPF.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        tfdDataNasc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdDataNasc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        pnlEndereco.setBackground(new java.awt.Color(255, 255, 255));
        pnlEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html> Endereço<font color='red'><b>* </b></font></html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        lblRua.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblRua.setForeground(new java.awt.Color(102, 102, 102));
        lblRua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRua.setText("<html>Rua<font color='red'><b>*</b></font>:</html>");

        tfdRua.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdRua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdRuaKeyTyped(evt);
            }
        });

        lblNumero.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(102, 102, 102));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumero.setText("<html>Número<font color='red'><b>*</b></font>:</html>");

        tfdNumero.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdNumero.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdNumeroKeyTyped(evt);
            }
        });

        lblComplemento.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblComplemento.setForeground(new java.awt.Color(102, 102, 102));
        lblComplemento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblComplemento.setText("Complemento:");

        tfdComplemento.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdComplemento.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdComplemento.setName("tfdComplemento"); // NOI18N
        tfdComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdRuaKeyTyped(evt);
            }
        });

        lblCEP.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblCEP.setForeground(new java.awt.Color(102, 102, 102));
        lblCEP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCEP.setText("<html>CEP<font color='red'><b>*</b></font>:</html>");

        tfdCEP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCEP.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdCEPKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdCEPKeyTyped(evt);
            }
        });

        tfdUF.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdUF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdUF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdUFKeyTyped(evt);
            }
        });

        lblUF.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblUF.setForeground(new java.awt.Color(102, 102, 102));
        lblUF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUF.setText("<html>UF<font color='red'><b>*</b></font>:</html>");

        lblCidade.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblCidade.setForeground(new java.awt.Color(102, 102, 102));
        lblCidade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCidade.setText("<html>Cidade<font color='red'><b>*</b></font>:</html>");

        tfdCidade.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCidade.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdCidadeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlEnderecoLayout = new javax.swing.GroupLayout(pnlEndereco);
        pnlEndereco.setLayout(pnlEnderecoLayout);
        pnlEnderecoLayout.setHorizontalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCEP)
                    .addComponent(lblNumero, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRua, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblComplemento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEnderecoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdCEP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(tfdRua, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdComplemento)
                    .addComponent(tfdUF)
                    .addComponent(tfdCidade))
                .addGap(60, 60, 60))
        );
        pnlEnderecoLayout.setVerticalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComplemento)
                    .addComponent(tfdComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tfdEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblTelefone.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTelefone.setForeground(new java.awt.Color(102, 102, 102));
        lblTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTelefone.setText("<html>Telefone<font color='red'><b>*</b></font>:</html>");

        tfdTelefone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdTelefone.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblCelular.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCelular.setForeground(new java.awt.Color(102, 102, 102));
        lblCelular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCelular.setText("<html>Celular<font color='red'><b>*</b></font>:</html>");

        tfdCelular.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCelular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCodigo)
                            .addComponent(lblCPF)
                            .addComponent(lblDataNasc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTelefone)
                            .addComponent(lblCelular))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdCPF)
                            .addComponent(tfdNome)
                            .addComponent(tfdCodigo)
                            .addComponent(tfdDataNasc)
                            .addComponent(tfdEmail)
                            .addComponent(tfdTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(tfdCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tbpConsumivel.addTab("Adicionar", pnlCadastro);

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
        btgGrupoRadio.add(rbNome);
        rbNome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbNome.setSelected(true);
        rbNome.setText("Por nome");
        rbNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNomeActionPerformed(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(255, 255, 255));
        btgGrupoRadio.add(rbCodigo);
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
                .addGap(0, 424, Short.MAX_VALUE))
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
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpConsumivel.addTab("Listagem", pnlListagem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbpConsumivel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpConsumivel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (Validacao.validarCampos(pnlCadastro) == 0 && Validacao.validarCampos(pnlEndereco) == 0) {
            if (Validacao.validarCPF(Formatacao.removerFormatacao(tfdCPF.getText()))) {
                if (Validacao.validarDataFormatada(tfdDataNasc.getText())) {
                    if (Validacao.validarEmail(tfdEmail.getText())) {
                        boolean isNew = (pessoa.getCodPessoa() == null);

                        pessoa.setNomPessoa(tfdNome.getText());
                        pessoa.setDesEmail(tfdEmail.getText());
                        pessoa.setDesEndereco(tfdCEP.getText().replace("-", "") + "," + tfdUF.getText() + "," + tfdCidade.getText() + "," + tfdRua.getText()
                                + "," + tfdNumero.getText() + "," + tfdComplemento.getText());
                        Date dataNasc = Date.valueOf(Formatacao.ajustaDataAMD(tfdDataNasc.getText()));
                        pessoa.setDtaNasc(dataNasc);
                        pessoa.setNumCelular(Formatacao.removerFormatacao(tfdCelular.getText().replace(" ", "")));
                        pessoa.setNumCpf(Formatacao.removerFormatacao(tfdCPF.getText()));
                        pessoa.setNumTelefone(Formatacao.removerFormatacao(tfdTelefone.getText().replace(" ", "")));
                        pessoa.setIndSituacao("A");
                        pessoaController.save(pessoa);

                        if (!isNew) {
                            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                            setVisibleCodigo(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
                        }

                        pessoaController.popularTabela(tblLista, 0, "", -1);

                        limparCampos();
                        setAba(1);
                    } else {
                        JOptionPane.showMessageDialog(this, "E-mail inválido!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Data inválida!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "CPF inválido!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        pessoa = pessoaController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        popularTelaCadastro();
        setAba(0);
        tfdNome.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de excluir o registro " + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (escolha == 0) {
            pessoaController.changeSituation(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
            pessoaController.popularTabela(tblLista, 0, "", -1);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void tfdNomeTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNomeTyped
        char vChar = evt.getKeyChar();
        if (!(Character.isLetter(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_SPACE))) {
            evt.consume();
        }
    }//GEN-LAST:event_tfdNomeTyped

    private void tfdPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdPesquisaKeyTyped
        char vChar = evt.getKeyChar();
        if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
            if (rbCodigo.isSelected()) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_tfdPesquisaKeyTyped

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        if (tfdPesquisa.getText().trim().isEmpty()) {
            pessoaController.popularTabela(tblLista, 0, "", -1);
        } else if (rbNome.isSelected()) {
            pessoaController.popularTabela(tblLista, 1, tfdPesquisa.getText(), -1);
        } else if (rbCodigo.isSelected()) {
            pessoaController.popularTabela(tblLista, 2, tfdPesquisa.getText(), -1);
        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void tbpConsumivelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpConsumivelStateChanged
        habilitar();
    }//GEN-LAST:event_tbpConsumivelStateChanged

    private void tfdRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRuaKeyTyped
        char vChar = evt.getKeyChar();
        if ((vChar == KeyEvent.VK_COMMA)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfdRuaKeyTyped

    private void tfdNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdNumeroKeyTyped
        char vChar = evt.getKeyChar();
        if ((Character.isLetter(vChar))) {
            evt.consume();
        }
    }//GEN-LAST:event_tfdNumeroKeyTyped

    private void rbNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNomeActionPerformed
        lblPesquisa.setText("Nome:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbNomeActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        lblPesquisa.setText("Código:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbCodigoActionPerformed

    private void tfdUFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdUFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdUFKeyTyped

    private void tfdCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCidadeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdCidadeKeyTyped

    private void tfdCEPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCEPKeyReleased
        setInfoEndereco();
    }//GEN-LAST:event_tfdCEPKeyReleased

    private void tfdCEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdCEPKeyTyped
        setInfoEndereco();
    }//GEN-LAST:event_tfdCEPKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGrupoRadio;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCEP;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblComplemento;
    private javax.swing.JLabel lblDataNasc;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblUF;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpConsumivel;
    private javax.swing.JFormattedTextField tfdCEP;
    private javax.swing.JFormattedTextField tfdCPF;
    private javax.swing.JFormattedTextField tfdCelular;
    private javax.swing.JTextField tfdCidade;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdComplemento;
    private javax.swing.JFormattedTextField tfdDataNasc;
    private javax.swing.JTextField tfdEmail;
    private javax.swing.JTextField tfdNome;
    private javax.swing.JTextField tfdNumero;
    private javax.swing.JTextField tfdPesquisa;
    private javax.swing.JTextField tfdRua;
    private javax.swing.JFormattedTextField tfdTelefone;
    private javax.swing.JTextField tfdUF;
    // End of variables declaration//GEN-END:variables
}
