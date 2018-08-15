package hotel.apresentacao;

import hotel.apoio.DocumentoLimitado;
import hotel.apoio.JCheckBoxList;
import hotel.apoio.LimpaCampos;
import hotel.apoio.Validacao;
import hotel.model.Quarto;
import hotel.model.TipoCama;
import hotel.persistencia.QuartoDAO;
import hotel.persistencia.TipoCamaDAO;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class frmQuarto extends javax.swing.JInternalFrame {

    Quarto quarto;

    public frmQuarto() {
        initComponents();
        quarto = new Quarto();
        setVisibleCodigo(false);

        new QuartoDAO().popularTabela(tblLista, 0, "");

        tfdNumero.setDocument(new DocumentoLimitado(100));
        tfdValor.setLimit(10);

        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });
        populaListTipoQuarto();
        setAba(0);
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
        tbpTipoCama.setSelectedIndex(pIndex);

        habilitar();
    }

    private void limparCampos() {
        quarto = new Quarto();
        LimpaCampos.LimparCampos(pnlCadastro);
        LimpaCampos.LimparCampos(pnlListagem);
    }

    private void habilitar() {
        if (tbpTipoCama.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(true);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 4));
            }

            btnSalvar.setEnabled(false);
            btnEditar.setEnabled(lSituacao.equals("Ativo"));
            btnExcluir.setEnabled(lSituacao.equals("Ativo"));
        }
    }

    private void populaListTipoQuarto() {
        List<TipoCama> lc;
        lc = new ArrayList<>();
        DefaultListModel<JCheckBox> model = new DefaultListModel();
        TipoCamaDAO cama = new TipoCamaDAO();
        for (TipoCama tc : cama.readAll()) {
            JCheckBox checkBox = new JCheckBox(tc.getDesTipoCama() + " - Lugares: " + tc.getQtdLugarTipoCama(), false);
            checkBox.setName(tc.getCodTipoCama().toString());
            model.addElement(checkBox);
        }
        JCheckBoxList jCheckBoxList = new JCheckBoxList(model);
        lstTipoQuarto.setModel(model);
        lstTipoQuarto.setCellRenderer(jCheckBoxList.getCellRenderer());
        lstTipoQuarto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = lstTipoQuarto.locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) lstTipoQuarto.getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }
        });
        lstTipoQuarto.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void popularTelaCadastro() {
        tfdCodigo.setText(quarto.getCodQuarto().toString());
        tfdNumero.setText(quarto.getNumQuarto());
        tfdValor.setValue(quarto.getVlrQuarto());
        for (TipoCama cama : quarto.getTipoCama()) {
            for (int i = 0; i < lstTipoQuarto.getModel().getSize(); i++) {
                if (cama.getCodTipoCama().toString().equals(lstTipoQuarto.getModel().getElementAt(i).getName())) {
                    lstTipoQuarto.getModel().getElementAt(i).setSelected(true);
                }
            }
        }
        setVisibleCodigo(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroup = new javax.swing.ButtonGroup();
        tbpTipoCama = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblNumero = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        tfdNumero = new hotel.apoio.JNumberFormatField();
        tfdValor = new hotel.apoio.JNumberFormatField();
        scpTipoQuarto = new javax.swing.JScrollPane();
        lstTipoQuarto = new javax.swing.JList<>();
        lblTipoQuarto = new javax.swing.JLabel();
        pnlListagem = new javax.swing.JPanel();
        pnlDetalhe = new javax.swing.JPanel();
        tfdPesquisa = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        pnlOpcao = new javax.swing.JPanel();
        rbNome = new javax.swing.JRadioButton();
        rbCodigo = new javax.swing.JRadioButton();
        btnPesquisar = new javax.swing.JButton();
        scpLista = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbpTipoCama.setBackground(new java.awt.Color(255, 255, 255));
        tbpTipoCama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbpTipoCama.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpTipoCamaStateChanged(evt);
            }
        });

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Quarto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        lblNumero.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(102, 102, 102));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumero.setText("<html>Número<font color='red'><b>*</b></font>:</html>");
        lblNumero.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblValor.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblValor.setForeground(new java.awt.Color(102, 102, 102));
        lblValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValor.setText("<html>Valor<font color='red'><b>*</b></font>:</html>");
        lblValor.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tfdNumero.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdNumero.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        tfdValor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdValor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lstTipoQuarto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        lstTipoQuarto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        scpTipoQuarto.setViewportView(lstTipoQuarto);

        lblTipoQuarto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTipoQuarto.setForeground(new java.awt.Color(102, 102, 102));
        lblTipoQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTipoQuarto.setText("<html>Tipo(s) de quarto(s):<font color='red'><b>*</b></font>:</html>");
        lblTipoQuarto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCodigo)
                    .addComponent(lblNumero, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblValor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTipoQuarto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scpTipoQuarto)
                    .addComponent(tfdCodigo)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfdValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(102, Short.MAX_VALUE))
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
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scpTipoQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        tbpTipoCama.addTab("Adicionar", pnlCadastro);

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
        lblPesquisa.setText("Número:");

        pnlOpcao.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pesquisa Detalhada ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbNome.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbNome);
        rbNome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbNome.setSelected(true);
        rbNome.setText("Por número");
        rbNome.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbNomeItemStateChanged(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbCodigo);
        rbCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbCodigo.setText("Por código");
        rbCodigo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbCodigoItemStateChanged(evt);
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

        btnPesquisar.setBackground(new java.awt.Color(12, 91, 160));
        btnPesquisar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
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
                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 277, Short.MAX_VALUE))
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
                    .addComponent(btnPesquisar))
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
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpTipoCama.addTab("Listagem", pnlListagem);

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(12, 91, 160));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/imagens/save.png"))); // NOI18N
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
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/imagens/edit.png"))); // NOI18N
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
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/imagens/trash.png"))); // NOI18N
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
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/imagens/close.png"))); // NOI18N
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
                        .addComponent(tbpTipoCama, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpTipoCama)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfdPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdPesquisaKeyTyped
        char vChar = evt.getKeyChar();
        if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
            if (rbCodigo.isSelected()) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_tfdPesquisaKeyTyped

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        if (tfdPesquisa.getText().trim().isEmpty()) {
            new TipoCamaDAO().popularTabela(tblLista, 0, "");
        } else if (rbNome.isSelected()) {
            new TipoCamaDAO().popularTabela(tblLista, 1, tfdPesquisa.getText());
        } else if (rbCodigo.isSelected()) {
            new TipoCamaDAO().popularTabela(tblLista, 2, tfdPesquisa.getText());
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (Validacao.validarCampos(pnlCadastro) == 0) {
            boolean isNew = (quarto.getCodQuarto() != null ? false : true);

            quarto.setNumQuarto(tfdNumero.getText());
            quarto.setVlrQuarto(tfdValor.getValue());
            List<TipoCama> listaTiposCamasSelecionadas = new ArrayList<>();
            for (int i = 0; i < lstTipoQuarto.getModel().getSize(); i++) {
                if (lstTipoQuarto.getModel().getElementAt(i).isSelected()) {
                    int codigo = Integer.parseInt(lstTipoQuarto.getModel().getElementAt(i).getName());
                    listaTiposCamasSelecionadas.add(new TipoCamaDAO().readId(codigo));
                }
            }
            quarto.setTipoCama(listaTiposCamasSelecionadas);
            quarto.setIndSituacao("A");
            new QuartoDAO().insert(quarto);

            if (!isNew) {
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                setVisibleCodigo(false);
            } else {
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
            }

            new QuartoDAO().popularTabela(tblLista, 0, "");

            limparCampos();
            setAba(1);
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void rbCodigoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbCodigoItemStateChanged
        lblPesquisa.setText("Código:");
    }//GEN-LAST:event_rbCodigoItemStateChanged

    private void tbpTipoCamaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpTipoCamaStateChanged
        habilitar();
    }//GEN-LAST:event_tbpTipoCamaStateChanged

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        quarto = new QuartoDAO().readId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        popularTelaCadastro();
        setAba(0);
        tfdNumero.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void rbNomeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbNomeItemStateChanged
        lblPesquisa.setText("Número:");
    }//GEN-LAST:event_rbNomeItemStateChanged

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        int option = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de excluir o registro " + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (option == 0) {
            new QuartoDAO().delete(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
            new QuartoDAO().popularTabela(tblLista, 0, "");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQuarto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblTipoQuarto;
    private javax.swing.JLabel lblValor;
    private javax.swing.JList<JCheckBox> lstTipoQuarto;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.ButtonGroup rbGroup;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JScrollPane scpTipoQuarto;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpTipoCama;
    private javax.swing.JTextField tfdCodigo;
    private hotel.apoio.JNumberFormatField tfdNumero;
    private javax.swing.JTextField tfdPesquisa;
    private hotel.apoio.JNumberFormatField tfdValor;
    // End of variables declaration//GEN-END:variables
}
