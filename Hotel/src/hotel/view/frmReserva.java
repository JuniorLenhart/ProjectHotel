package hotel.view;

import hotel.support.LimpaCampos;
import hotel.controller.ReservaController;
import hotel.model.Reserva;
import hotel.support.Formatacao;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class frmReserva extends javax.swing.JInternalFrame {

    Reserva reserva;
    ReservaController reservaController;

    public frmReserva() {
        initComponents();
        reserva = new Reserva();
        reservaController = new ReservaController();
        setVisibleCodigo(false);

        reservaController.popularTabela(tblLista, 0, "");

        Formatacao.formatarCPF(tfdCPF);
        tfdQuarto.setLimit(4);
        tfdQuarto.setNumberFormat(NumberFormat.getIntegerInstance());

        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });

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
        tbpReserva.setSelectedIndex(pIndex);

        habilitar();
    }

    private void limparCampos() {
        reserva = new Reserva();
        LimpaCampos.LimparCampos(pnlCadastro);
        LimpaCampos.LimparCampos(pnlDetalhe);
    }

    private void habilitar() {
        if (tbpReserva.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(true);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 5));
            }

            btnSalvar.setEnabled(false);
            btnEditar.setEnabled(lSituacao.equals("Ativo"));
            btnExcluir.setEnabled(lSituacao.equals("Ativo"));
        }
    }

    public void popularTelaCadastro() {
        tfdCodigo.setText(reserva.getCodReserva().toString());
        tfdDataEntrada.setText(Formatacao.ajustaDataDMA(reserva.getDtaEntrada().toString()));
        tfdDataSaida.setText(Formatacao.ajustaDataDMA(reserva.getDtaSaida().toString()));
        tfdLugar.setValue(reserva.getQtdLugar());
        tfdQuarto.setText(reserva.getQuarto().getNumQuarto());
        tfdCPF.setText(reserva.getPessoa().getNumCpf());
        lblNomePessoa.setText(reserva.getPessoa().getNomPessoa());
        setVisibleCodigo(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpReserva = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblDataEntrada = new javax.swing.JLabel();
        lblDataSaida = new javax.swing.JLabel();
        lblLugar = new javax.swing.JLabel();
        lblQuarto = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        tfdDataEntrada = new hotel.support.DateChooserComboLayout();
        tfdDataSaida = new hotel.support.DateChooserComboLayout();
        tfdLugar = new javax.swing.JSpinner();
        tfdQuarto = new hotel.support.JNumberFormatField();
        tfdCPF = new javax.swing.JFormattedTextField();
        btnPesquisaQuarto = new javax.swing.JButton();
        btnPesquisaPessoa = new javax.swing.JButton();
        lblNomePessoa = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbpReserva.setBackground(new java.awt.Color(255, 255, 255));
        tbpReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbpReserva.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpReservaStateChanged(evt);
            }
        });

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Cadastro de Reserva ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblDataEntrada.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataEntrada.setForeground(new java.awt.Color(102, 102, 102));
        lblDataEntrada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataEntrada.setText("<html>Data Entrada<font color='red'><b>*</b></font>:</html>");
        lblDataEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDataSaida.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataSaida.setForeground(new java.awt.Color(102, 102, 102));
        lblDataSaida.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataSaida.setText("<html>Data Saída<font color='red'><b>*</b></font>:</html>");
        lblDataSaida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblLugar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLugar.setForeground(new java.awt.Color(102, 102, 102));
        lblLugar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLugar.setText("<html>Quantidade<font color='red'><b>*</b></font>:</html>");
        lblLugar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblQuarto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblQuarto.setForeground(new java.awt.Color(102, 102, 102));
        lblQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuarto.setText("<html>Nº Quarto<font color='red'><b>*</b></font>:</html>");
        lblQuarto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCPF.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCPF.setForeground(new java.awt.Color(102, 102, 102));
        lblCPF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCPF.setText("<html>CPF<font color='red'><b>*</b></font>:</html>");
        lblCPF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdDataEntrada.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataEntrada.setFormat(2);
        tfdDataEntrada.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));

        tfdDataSaida.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataSaida.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));

        tfdLugar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        tfdQuarto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdQuarto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfdQuarto.setText("0");
        tfdQuarto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        tfdCPF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCPF.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        btnPesquisaQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N

        btnPesquisaPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N

        lblNomePessoa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCadastroLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfdLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(lblDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                                        .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnPesquisaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPesquisaQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfdDataEntrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPesquisaQuarto))
                        .addGap(12, 12, 12)
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisaPessoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        tbpReserva.addTab("Adicionar", pnlCadastro);

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
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpReserva.addTab("Listagem", pnlListagem);

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
                        .addComponent(tbpReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpReserva)
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

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        if (rbNome.isSelected()) {
            reservaController.popularTabela(tblLista, 1, tfdPesquisa.getText());
        } else {
            reservaController.popularTabela(tblLista, 2, tfdPesquisa.getText());
        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
       /* if ((Validacao.validarCampos(pnlCadastro) == 0) && (!tfdDescricao.getText().equals("")) && (tfdPreco.getValue().intValue() != 0)) {
            boolean isNew = (consumivel.getCodConsumivel() == null);

            consumivel.setNomConsumivel(tfdNome.getText());
            consumivel.setDesConsumivel(tfdDescricao.getText());
            consumivel.setVlrConsumivel(tfdPreco.getValue());
            consumivel.setIndSituacao("A");
            for (TipoConsumivel tc : TipoConsumivel.values()) {
                if (cmbTipo.getSelectedItem().toString().equals(tc.getValor())) {
                    consumivel.setTipConsumivel(tc.name());
                }
            }
            consumivelController.save(consumivel);

            if (!isNew) {
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                setVisibleCodigo(false);
            } else {
                JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
            }

            consumivelController.popularTabela(tblLista, 0, "");

            limparCampos();
            setAba(1);
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }*/
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tbpReservaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpReservaStateChanged
        habilitar();
    }//GEN-LAST:event_tbpReservaStateChanged

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        reserva = reservaController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        popularTelaCadastro();
        setAba(0);
        tfdDataEntrada.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de cancelar a reserva" + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (escolha == 0) {
            reservaController.cancelReserve(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(this, "Cancelada com sucesso!");
            reservaController.popularTabela(tblLista, 0, "");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void rbNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNomeActionPerformed
        lblPesquisa.setText("Nome:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbNomeActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        lblPesquisa.setText("Código:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbCodigoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisaPessoa;
    private javax.swing.JButton btnPesquisaQuarto;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSelecaoQuarto;
    private javax.swing.JButton btnSelecaoQuarto1;
    private javax.swing.JButton btnSelecaoQuarto2;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblDataSaida;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblNomePessoa;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblQuarto;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpReserva;
    private javax.swing.JFormattedTextField tfdCPF;
    private javax.swing.JTextField tfdCodigo;
    private hotel.support.DateChooserComboLayout tfdDataEntrada;
    private hotel.support.DateChooserComboLayout tfdDataSaida;
    private javax.swing.JSpinner tfdLugar;
    private javax.swing.JTextField tfdPesquisa;
    private hotel.support.JNumberFormatField tfdQuarto;
    // End of variables declaration//GEN-END:variables
}
