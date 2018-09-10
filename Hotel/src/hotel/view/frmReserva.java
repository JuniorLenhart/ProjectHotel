package hotel.view;

import hotel.controller.PessoaController;
import hotel.support.LimpaCampos;
import hotel.controller.ReservaController;
import hotel.model.Parametro;
import hotel.model.Pessoa;
import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.support.DocumentoLimitado;
import hotel.support.Formatacao;
import hotel.support.Unit;
import hotel.support.Validacao;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class frmReserva extends javax.swing.JInternalFrame {

    Reserva reserva;
    ReservaController reservaController;
    PessoaController pessoaController;

    public frmReserva() {
        initComponents();
        reserva = new Reserva();
        reservaController = new ReservaController();
        pessoaController = new PessoaController();
        setVisibleCodigo(false);

        reservaController.popularTabela(tblLista, 0, "");

        Formatacao.formatarCPF(tfdCPF);
        tfdQuarto.setDocument(new DocumentoLimitado(4));

        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });

        setAba(0);
        habilitarQuarto();
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
        lblNomePessoa.setText("");
        LimpaCampos.LimparCampos(pnlCadastro);
        LimpaCampos.LimparCampos(pnlDetalhe);
    }

    private void habilitar() {
        if (tbpReserva.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(true);
            btnConfirmar.setEnabled(false);
            btnCancelar.setEnabled(false);
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 8));
            }

            btnSalvar.setEnabled(false);
            btnConfirmar.setEnabled(lSituacao.equals("Efetuada"));
            btnCancelar.setEnabled(lSituacao.equals("Efetuada"));
        }
    }

    private void habilitarQuarto() {
        if (tfdDataEntrada.getText().isEmpty() || tfdDataSaida.getText().isEmpty() || Integer.parseInt(tfdLugar.getValue().toString()) <= 0) {
            btnPesquisaQuarto.setEnabled(false);
        } else {
            btnPesquisaQuarto.setEnabled(true);
        }
        setInfoQuarto(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroup = new javax.swing.ButtonGroup();
        tbpReserva = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblDataEntrada = new javax.swing.JLabel();
        lblDataSaida = new javax.swing.JLabel();
        lblLugar = new javax.swing.JLabel();
        lblQuarto = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        tfdDataEntrada = new hotel.support.JDateChooserComboLayout();
        tfdDataSaida = new hotel.support.JDateChooserComboLayout();
        tfdLugar = new javax.swing.JSpinner();
        tfdCPF = new javax.swing.JFormattedTextField();
        btnPesquisaQuarto = new javax.swing.JButton();
        btnPesquisaPessoa = new javax.swing.JButton();
        lblNomePessoa = new javax.swing.JLabel();
        tfdQuarto = new javax.swing.JTextField();
        tfdValor = new hotel.support.JNumberFormatField();
        lblValor = new javax.swing.JLabel();
        pnlListagem = new javax.swing.JPanel();
        pnlDetalhe = new javax.swing.JPanel();
        tfdPesquisa = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        pnlOpcao = new javax.swing.JPanel();
        rbCPF = new javax.swing.JRadioButton();
        rbCodigo = new javax.swing.JRadioButton();
        rbQuarto = new javax.swing.JRadioButton();
        btnPesquisa = new javax.swing.JButton();
        scpLista = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();

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
        tfdDataEntrada.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tfdDataEntrada.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                tfdDataEntradaOnCommit(evt);
            }
        });

        tfdDataSaida.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataSaida.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tfdDataSaida.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                tfdDataSaidaOnCommit(evt);
            }
        });

        tfdLugar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdLugar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tfdLugarStateChanged(evt);
            }
        });

        tfdCPF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        try {
            tfdCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdCPF.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfdCPFFocusLost(evt);
            }
        });

        btnPesquisaQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N
        btnPesquisaQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaQuartoActionPerformed(evt);
            }
        });

        btnPesquisaPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N
        btnPesquisaPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaPessoaActionPerformed(evt);
            }
        });

        lblNomePessoa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        tfdQuarto.setEditable(false);
        tfdQuarto.setBackground(new java.awt.Color(255, 255, 255));
        tfdQuarto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdQuarto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdQuarto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfdQuartoKeyTyped(evt);
            }
        });

        tfdValor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdValor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblValor.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblValor.setForeground(new java.awt.Color(102, 102, 102));
        lblValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValor.setText("<html>Valor<font color='red'><b>*</b></font>:</html>");
        lblValor.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlCadastroLayout.createSequentialGroup()
                            .addGap(128, 128, 128)
                            .addComponent(lblNomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCadastroLayout.createSequentialGroup()
                            .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlCadastroLayout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlCadastroLayout.createSequentialGroup()
                                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfdLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlCadastroLayout.createSequentialGroup()
                                            .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnPesquisaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnPesquisaQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pnlCadastroLayout.createSequentialGroup()
                                            .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                            .addComponent(lblDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(18, 18, 18)
                            .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfdValor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdDataEntrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisaQuarto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisaPessoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
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
        lblPesquisa.setText("CPF:");

        pnlOpcao.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pesquisa Detalhada ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbCPF.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbCPF);
        rbCPF.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbCPF.setSelected(true);
        rbCPF.setText("Por CPF");
        rbCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCPFActionPerformed(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbCodigo);
        rbCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbCodigo.setText("Por código");
        rbCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodigoActionPerformed(evt);
            }
        });

        rbQuarto.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbQuarto);
        rbQuarto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbQuarto.setText("Por nº quarto");
        rbQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbQuartoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcaoLayout = new javax.swing.GroupLayout(pnlOpcao);
        pnlOpcao.setLayout(pnlOpcaoLayout);
        pnlOpcaoLayout.setHorizontalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbCPF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbQuarto)
                .addGap(6, 6, 6))
        );
        pnlOpcaoLayout.setVerticalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoLayout.createSequentialGroup()
                .addGroup(pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCPF)
                    .addComponent(rbQuarto)
                    .addComponent(rbCodigo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDetalheLayout.createSequentialGroup()
                        .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPesquisa)
                            .addComponent(tfdPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlListagemLayout.setVerticalGroup(
            pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetalhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
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

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(12, 91, 160));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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

        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(12, 91, 160));
        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/payment-method.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfirmar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
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
                .addComponent(btnConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalvar)
            .addComponent(btnCancelar)
            .addComponent(btnFechar)
            .addComponent(btnConfirmar)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbpReserva))
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
        if (tfdPesquisa.getText().trim().isEmpty()) {
            reservaController.popularTabela(tblLista, 0, "");
        } else if (rbCPF.isSelected()) {
            reservaController.popularTabela(tblLista, 4, tfdPesquisa.getText());
        } else if (rbCodigo.isSelected()) {
            reservaController.popularTabela(tblLista, 5, tfdPesquisa.getText());
        } else if (rbQuarto.isSelected()) {
            reservaController.popularTabela(tblLista, 6, tfdPesquisa.getText());
        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (Validacao.validarCampos(pnlCadastro) == 0) {
            boolean isNew = (reserva.getCodReserva() == null);

            Timestamp dtaReserva = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(Unit.getDataHoraAtual()));
            reserva.setDtaReserva(dtaReserva);
            Date dtaEntrada = Date.valueOf(Formatacao.ajustaDataAMD(tfdDataEntrada.getText()));
            reserva.setDtaEntrada(dtaEntrada);
            Date dtaSaida = Date.valueOf(Formatacao.ajustaDataAMD(tfdDataSaida.getText()));
            reserva.setDtaSaida(dtaSaida);
            reserva.setQtdLugar(Integer.parseInt(tfdLugar.getValue().toString()));
            reserva.setVlrReserva(tfdValor.getValue());
            reserva.setVlrPago(BigDecimal.ZERO);
            reserva.setUsuario(Parametro.USUARIO);
            reserva.setIndSituacao("E");
            reservaController.save(reserva);

            if (!isNew) {
                JOptionPane.showMessageDialog(this, "Atualizada com sucesso!");
                setVisibleCodigo(false);
            } else {
                JOptionPane.showMessageDialog(this, "Cadastrada com sucesso!");
            }

            reservaController.popularTabela(tblLista, 0, "");

            limparCampos();
            setAba(1);
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tbpReservaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpReservaStateChanged
        habilitar();
    }//GEN-LAST:event_tbpReservaStateChanged

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Object[] options = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de cancelar a reserva " + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (escolha == 0) {
            reservaController.cancelReserve(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(this, "Cancelada com sucesso!");
            reservaController.popularTabela(tblLista, 0, "");
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCPFActionPerformed
        lblPesquisa.setText("CPF:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbCPFActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        lblPesquisa.setText("Código:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbCodigoActionPerformed

    private void btnPesquisaQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaQuartoActionPerformed
        frmPesquisaQuarto pesquisaQuarto = new frmPesquisaQuarto(null, true, tfdDataEntrada.getText(), tfdDataSaida.getText(), Integer.parseInt(tfdLugar.getValue().toString()));
        pesquisaQuarto.setVisible(true);
        setInfoQuarto(pesquisaQuarto.getQuarto());
    }//GEN-LAST:event_btnPesquisaQuartoActionPerformed

    private void tfdLugarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tfdLugarStateChanged
        habilitarQuarto();
    }//GEN-LAST:event_tfdLugarStateChanged

    private void tfdDataEntradaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_tfdDataEntradaOnCommit
        habilitarQuarto();
    }//GEN-LAST:event_tfdDataEntradaOnCommit

    private void tfdDataSaidaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_tfdDataSaidaOnCommit
        habilitarQuarto();
    }//GEN-LAST:event_tfdDataSaidaOnCommit

    private void tfdQuartoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdQuartoKeyTyped
        char vChar = evt.getKeyChar();
        if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_tfdQuartoKeyTyped

    private void btnPesquisaPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaPessoaActionPerformed
        frmPesquisaPessoa pesquisaPessoa = new frmPesquisaPessoa(null, true);
        pesquisaPessoa.setVisible(true);
        setInfoPessoa(pesquisaPessoa.getPessoa());
    }//GEN-LAST:event_btnPesquisaPessoaActionPerformed

    private void tfdCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdCPFFocusLost
        setInfoPessoa(pessoaController.getReadCPF(Formatacao.removerFormatacao(tfdCPF.getText())));
    }//GEN-LAST:event_tfdCPFFocusLost

    private void rbQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbQuartoActionPerformed
        lblPesquisa.setText("Nº Quarto:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbQuartoActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        reserva = reservaController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));

        frmConfirmarReserva confirmarReserva = new frmConfirmarReserva(null, true, reserva);
        confirmarReserva.setVisible(true);

        if (!confirmarReserva.getCancel()) {
            reservaController.save(reserva);
            JOptionPane.showMessageDialog(this, "Confirmada com sucesso!");
        }

        reservaController.popularTabela(tblLista, 0, "");

        limparCampos();
        setAba(1);
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void setInfoPessoa(Pessoa pessoa) {
        if (pessoa != null && pessoa.getCodPessoa() != null) {
            tfdCPF.setText(pessoa.getNumCpf());
            lblNomePessoa.setText(pessoa.getNomPessoa());
            reserva.setPessoa(pessoa);
        } else {
            tfdCPF.setText("");
            lblNomePessoa.setText("");
            reserva.setPessoa(new Pessoa());
        }
    }

    private void setInfoQuarto(Quarto quarto) {
        if (quarto != null && quarto.getCodQuarto() != null) {
            tfdQuarto.setText(quarto.getNumQuarto());
            tfdValor.setValue(quarto.getVlrQuarto());
            reserva.setQuarto(quarto);
        } else {
            tfdQuarto.setText("");
            tfdValor.setValue(BigDecimal.ZERO);
            reserva.setQuarto(new Quarto());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisaPessoa;
    private javax.swing.JButton btnPesquisaQuarto;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblDataSaida;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblNomePessoa;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblQuarto;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JRadioButton rbCPF;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.ButtonGroup rbGroup;
    private javax.swing.JRadioButton rbQuarto;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpReserva;
    private javax.swing.JFormattedTextField tfdCPF;
    private javax.swing.JTextField tfdCodigo;
    private hotel.support.JDateChooserComboLayout tfdDataEntrada;
    private hotel.support.JDateChooserComboLayout tfdDataSaida;
    private javax.swing.JSpinner tfdLugar;
    private javax.swing.JTextField tfdPesquisa;
    private javax.swing.JTextField tfdQuarto;
    private hotel.support.JNumberFormatField tfdValor;
    // End of variables declaration//GEN-END:variables
}
