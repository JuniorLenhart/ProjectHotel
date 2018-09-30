package hotel.view;

import hotel.controller.LocacaoConsumivelController;
import hotel.controller.PermissaoController;
import hotel.model.Consumivel;
import hotel.model.Locacao;
import hotel.model.LocacaoConsumivel;
import hotel.repository.ConsumivelRepository;
import hotel.support.DocumentoLimitado;
import hotel.support.LimpaCampos;
import hotel.support.Validacao;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultFormatter;

public class frmLocacaoConsumivel extends javax.swing.JInternalFrame {

    Locacao locacao;
    Consumivel consumivel;
    LocacaoConsumivel locacaoConsumivel;
    LocacaoConsumivelController locacaoConsumivelController;

    boolean isSalvar = false;
    boolean isEditar = false;
    boolean isExcluir = false;

    public frmLocacaoConsumivel(Locacao locacao) {
        initComponents();
        this.locacao = locacao;
        this.consumivel = null;
        locacaoConsumivel = new LocacaoConsumivel();
        locacaoConsumivelController = new LocacaoConsumivelController();

        loadPermission();
        locacaoConsumivelController.popularTabela(tblLista, 1, locacao.getCodLocacao().toString());
        addVariaveisComboBox(cmbConsumivel);
        addEventJSpinner();
        if (cmbConsumivel.getSelectedItem() != null) {
            String[] split = cmbConsumivel.getSelectedItem().toString().split(" ");
            consumivel = ConsumivelRepository.readId(Integer.parseInt(split[0]));
        }

        calculaTotal();
        setLocacaoField();
        setVisibleCodigo(false);
        setAba(0);

        tfdPreco.setEditable(false);

        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });
    }

    private void calculaTotal() {
        int quantidade = Integer.parseInt(tfdQuantidade.getValue().toString());
        if (quantidade <= 0) {
            JOptionPane.showMessageDialog(this, "Quantidade não pode ser menor ou igual a zero!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            if (consumivel != null) {
                tfdPreco.setValue(BigDecimal.valueOf(quantidade * consumivel.getVlrConsumivel().doubleValue()));
            }
        }
    }

    public frmLocacaoConsumivel() {
        initComponents();
    }

    private void loadPermission() {
        isSalvar = PermissaoController.hasPermission("frmLocacaoConsumivel", "btnSalvar");
        isEditar = PermissaoController.hasPermission("frmLocacaoConsumivel", "btnEditar");
        isExcluir = PermissaoController.hasPermission("frmLocacaoConsumivel", "btnExcluir");
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
        tbpLocacaoConsumivel.setSelectedIndex(pIndex);

        habilitar();
    }

    private void limparCampos() {
        locacaoConsumivel = new LocacaoConsumivel();
        if (cmbConsumivel.getItemCount() != 0) {
            cmbConsumivel.setSelectedIndex(0);
        }
        tfdQuantidade.setValue(1);
    }

    private void habilitar() {
        if (tbpLocacaoConsumivel.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(isSalvar);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
        } else {
            btnSalvar.setEnabled(false);
            btnEditar.setEnabled(isEditar);
            btnExcluir.setEnabled(isExcluir);
            limparCampos();
        }
    }

    private void setLocacaoField() {
        tfdLocacao.setEditable(false);
        tfdLocacao.setText(locacao.getCodLocacao().toString());
    }

    private void popularTelaCadastro() {
        tfdCodigo.setText(locacaoConsumivel.getCodLocacaoConsumivel().toString());
        cmbConsumivel.setSelectedItem(locacaoConsumivel.getConsumivel().getCodConsumivel() + " " + locacaoConsumivel.getConsumivel().getNomConsumivel());
        tfdQuantidade.setValue(locacaoConsumivel.getQtdConsumivel());
        tfdPreco.setValue(locacaoConsumivel.getVlrConsumivel());
        setVisibleCodigo(true);
    }

    private void addVariaveisComboBox(javax.swing.JComboBox comboBox) {
        List<String> lc;
        lc = new ArrayList<>();
        for (Consumivel c : ConsumivelRepository.readAll()) {
            lc.add(c.getCodConsumivel() + " " + c.getNomConsumivel());
        }
        comboBox.setModel(new DefaultComboBoxModel(lc.toArray()));
    }

    private void addEventJSpinner() {
        JComponent comp = tfdQuantidade.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        tfdQuantidade.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                calculaTotal();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        tbpLocacaoConsumivel = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblQuantidade = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblPreco = new javax.swing.JLabel();
        tfdPreco = new hotel.support.JNumberFormatField();
        lblLocacao = new javax.swing.JLabel();
        tfdLocacao = new javax.swing.JTextField();
        lblConsumivel = new javax.swing.JLabel();
        cmbConsumivel = new javax.swing.JComboBox<>();
        tfdQuantidade = new javax.swing.JSpinner();
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

        tbpLocacaoConsumivel.setBackground(new java.awt.Color(255, 255, 255));
        tbpLocacaoConsumivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbpLocacaoConsumivel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpLocacaoConsumivelStateChanged(evt);
            }
        });

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Cadastro de Consumível ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblQuantidade.setForeground(new java.awt.Color(102, 102, 102));
        lblQuantidade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuantidade.setText("<html>Quantidade<font color='red'><b>*</b></font>:</html>");
        lblQuantidade.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblPreco.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPreco.setForeground(new java.awt.Color(102, 102, 102));
        lblPreco.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPreco.setText("<html>Preço<font color='red'><b>*</b></font>:</html>");

        tfdPreco.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdPreco.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfdPreco.setToolTipText("");
        tfdPreco.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblLocacao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLocacao.setForeground(new java.awt.Color(102, 102, 102));
        lblLocacao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLocacao.setText("<html>Locação<font color='red'><b>*</b></font>:</html>");
        lblLocacao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdLocacao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdLocacao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblConsumivel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblConsumivel.setForeground(new java.awt.Color(102, 102, 102));
        lblConsumivel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblConsumivel.setText("<html>Consumível<font color='red'><b>*</b></font>:</html>");

        cmbConsumivel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbConsumivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbConsumivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbConsumivelItemStateChanged(evt);
            }
        });

        tfdQuantidade.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdQuantidade.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreco, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantidade)
                    .addComponent(lblConsumivel)
                    .addComponent(lblLocacao))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfdCodigo)
                    .addComponent(tfdPreco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfdLocacao)
                    .addComponent(cmbConsumivel, javax.swing.GroupLayout.Alignment.TRAILING, 0, 270, Short.MAX_VALUE)
                    .addComponent(tfdQuantidade))
                .addContainerGap(132, Short.MAX_VALUE))
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
                    .addComponent(lblLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbConsumivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConsumivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(245, Short.MAX_VALUE))
        );

        tbpLocacaoConsumivel.addTab("Adicionar", pnlCadastro);

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
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpLocacaoConsumivel.addTab("Listagem", pnlListagem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbpLocacaoConsumivel, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpLocacaoConsumivel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if ((Validacao.validarCampos(pnlCadastro) == 0)) {
            boolean isNew = (locacaoConsumivel.getCodLocacaoConsumivel() == null);

            locacaoConsumivel.setConsumivel(consumivel);
            locacaoConsumivel.setLocacao(locacao);
            locacaoConsumivel.setVlrConsumivel(tfdPreco.getValue());
            locacaoConsumivel.setQtdConsumivel(Integer.parseInt(tfdQuantidade.getValue().toString()));

            locacaoConsumivelController.save(locacaoConsumivel);

            if (!isNew) {
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                setVisibleCodigo(false);
            } else {
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
            }

            locacaoConsumivelController.popularTabela(tblLista, 0, "");

            limparCampos();
            setAba(1);
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        locacaoConsumivel = locacaoConsumivelController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        popularTelaCadastro();
        setAba(0);
        tfdQuantidade.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de excluir o registro " + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (escolha == 0) {
            locacaoConsumivelController.delete(locacaoConsumivelController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString())));
            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
            locacaoConsumivelController.popularTabela(tblLista, 0, "");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

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
            locacaoConsumivelController.popularTabela(tblLista, 1, tfdPesquisa.getText());
        } else {
            locacaoConsumivelController.popularTabela(tblLista, 2, tfdPesquisa.getText());
        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void tbpLocacaoConsumivelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpLocacaoConsumivelStateChanged
        habilitar();
    }//GEN-LAST:event_tbpLocacaoConsumivelStateChanged

    private void cmbConsumivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbConsumivelItemStateChanged
        if (cmbConsumivel.getSelectedItem() != null) {
            String[] split = cmbConsumivel.getSelectedItem().toString().split(" ");
            consumivel = ConsumivelRepository.readId(Integer.parseInt(split[0]));
            calculaTotal();
        } else {
            consumivel = null;
        }
    }//GEN-LAST:event_cmbConsumivelItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbConsumivel;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblConsumivel;
    private javax.swing.JLabel lblLocacao;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpLocacaoConsumivel;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdLocacao;
    private javax.swing.JTextField tfdPesquisa;
    private hotel.support.JNumberFormatField tfdPreco;
    private javax.swing.JSpinner tfdQuantidade;
    // End of variables declaration//GEN-END:variables
}
