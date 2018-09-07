package hotel.view;

import hotel.controller.LocacaoController;
import hotel.controller.LocacaoHospedeController;
import hotel.model.Locacao;
import hotel.model.LocacaoHospede;
import hotel.model.Pessoa;
import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.repository.LocacaoHospedeRepository;
import hotel.repository.PessoaRepository;
import hotel.repository.QuartoRepository;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import hotel.support.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class frmLocacao extends javax.swing.JInternalFrame {

    List<Pessoa> listAcompanhante = new ArrayList<>();
    Reserva reserva;
    Pessoa pessoaTitular;
    Locacao locacao;
    Quarto quarto;
    List<LocacaoHospede> locacaoHospede;
    LocacaoHospedeController locacaoHospedeController;
    LocacaoController locacaoController;

    public frmLocacao() {
        initComponents();
        tfdDataEntradaPrevista.setEditable(false);
        tfdDataSaidaPrevista.setEnabled(true);
        tfdPessoaCodigo.setVisible(false);
        btnSelecaoPessoa.setEnabled(false);
        locacao = new Locacao();
        locacaoHospedeController = new LocacaoHospedeController();
        locacaoController = new LocacaoController();
        locacaoHospede = new ArrayList<>();
        locacaoController.popularTabela(tblLista, 0, "");
        setFieldsEditable(true);
        setMinDateChooser();
        criaEventoTextFieldPessoaCodigo();
        criaEventoTextFieldValorRestante();
        habilitar();
        setAba(0);
        setVisibleCodigo(false);
        setVisibleDatasPrevistas(true);
        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
                if ((tfdPessoaCodigo.getText().equals(""))||(((tbListaAcompanhante.getRowCount()+1) - Integer.parseInt(tfdQuantidadeLugares.getValue().toString()))==0)) {
                    btnSelecaoPessoa.setEnabled(false);
                } else {
                    btnSelecaoPessoa.setEnabled(true);
                }
            }
        });
        //new AutoFill(new ArrayList<Reserva>(ReservaRepository.readAll()), tfdPesquisaReserva, pnlFields, pnlAcompanhante1);
    }

    private void setFieldsEditable(boolean editable) {
        tfdValorPago.setEditable(!editable);
        tfdCodigo.setEditable(!editable);
        tfdDataEntrada.setEditable(!editable);
        tfdDataSaida.setEditable(!editable);
        tfdQuarto.setEditable(!editable);
        tfdNomeTitular.setEditable(!editable);
    }

    private void setMinDateChooser() {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        tfdDataSaidaPrevista.setMinSelectableDate(Date.valueOf(now.toLocalDate().plusDays(1)));
        java.util.Date convertedDatetime = Date.from(now.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        tfdDataSaidaPrevista.setDate(convertedDatetime);
    }

    private void setVisibleCodigo(boolean isVisible) {
        lblCodigo.setVisible(isVisible);
        tfdCodigo.setVisible(isVisible);
        lblDataSaida.setVisible(isVisible);
        tfdDataSaida.setVisible(isVisible);

        if (isVisible) {
            lblCodigo.setEnabled(!isVisible);
            tfdCodigo.setEnabled(!isVisible);
            lblDataSaida.setEnabled(!isVisible);
            tfdDataSaida.setEnabled(!isVisible);
        }
    }

    private void setVisibleDatasPrevistas(boolean isVisible) {
        lblDataEntradaPrevista.setVisible(isVisible);
        tfdDataEntradaPrevista.setVisible(isVisible);
        lblSaidaPrevista.setVisible(isVisible);
        tfdDataSaidaPrevista.setVisible(isVisible);

        if (isVisible) {

        }
    }

    private void setDataTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        tfdDataEntrada.setText(dtf.format(now));
        tfdDataEntradaPrevista.setText(dtf.format(now));
    }

    private void habilitar() {
        if (tbpLocacao.getSelectedIndex() == 0) {
            btnSalvar.setEnabled(true);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnProcurarReserva.setEnabled(true);
            tblLista.clearSelection();
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 5));
            }

            btnSalvar.setEnabled(false);
            btnProcurarReserva.setEnabled(false);
            btnEditar.setEnabled(lSituacao.equals("Em aberto"));
            btnExcluir.setEnabled(lSituacao.equals("Em aberto"));
            limparCampos();
        }
        setDataTimeNow();
    }

    private void limparCampos() {
        locacao = new Locacao();
        LimpaCampos.LimparCampos(pnlFields);
        LimpaCampos.LimparCampos(pnlField2);
        setMinDateChooser();
        tfdPessoaCodigo.setText("");
        lblValorRestante.setText("R$ 0,00");
        DefaultTableModel dm = (DefaultTableModel) tbListaAcompanhante.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
        setDataTimeNow();
    }

    private void setAba(int pIndex) {
        tbpLocacao.setSelectedIndex(pIndex);

        habilitar();
    }

    public void popularTelaCadastro(int pOption) {
        if (pOption == 1) {
            locacaoHospede = LocacaoHospedeRepository.readLocacaoId(locacao.getCodLocacao());
            DefaultTableModel model = (DefaultTableModel) tbListaAcompanhante.getModel();
            int contadorPessoas = 0;
            for (LocacaoHospede lh : locacaoHospede) {
                if (lh.getIndResponsavel().equals("S")) {
                    tfdNomeTitular.setText(lh.getCodPessoa().getNomPessoa());
                    tfdPessoaCodigo.setText(lh.getCodPessoa().getCodPessoa().toString());
                } else {
                    model.addRow(new Object[]{lh.getCodPessoa().getCodPessoa(), lh.getCodPessoa().getNomPessoa(), lh.getCodPessoa().getNumCpf(), lh.getCodPessoa().getDesEmail()});
                }
                contadorPessoas++;
            }
            tfdQuantidadeLugares.setValue(contadorPessoas);
            tfdCodigo.setText(locacao.getCodLocacao().toString());
            tfdDataEntradaPrevista.setText(Formatacao.ajustaDataDMAHS(locacao.getDtaEntradaPrevista().toString()));
            tfdDataSaidaPrevista.setDate(locacao.getDtaSaidaPrevista());
            tfdQuarto.setText(locacao.getCodQuarto().getNumQuarto());
            tfdDataEntrada.setText(Formatacao.ajustaDataDMAHS(locacao.getDtaEntrada().toString()));
            if (locacao.getDtaSaida() != null) {
                tfdDataSaida.setText(Formatacao.ajustaDataDMAHS(locacao.getDtaSaida().toString()));
            }
            tfdValorPago.setValue(locacao.getVlrLocacao());
            setVisibleCodigo(true);
        } else {
            tfdNomeTitular.setText(reserva.getCodPessoa().getNomPessoa());
            tfdQuantidadeLugares.setValue(reserva.getQtdLugar());
            tfdValorPago.setValue(reserva.getVlrPago());
            tfdPessoaCodigo.setText(reserva.getCodPessoa().getCodPessoa() + "");
            tfdQuarto.setText(reserva.getCodQuarto().getNumQuarto());
            tfdDataEntradaPrevista.setText(Formatacao.ajustaDataDMAHS(reserva.getDtaEntrada().toString()));
            tfdDataSaidaPrevista.setDate(reserva.getDtaSaida());
            setVisibleDatasPrevistas(true);
        }
    }

    private void criaEventoTextFieldPessoaCodigo() {
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((tfdPessoaCodigo.getText().equals(""))||(((tbListaAcompanhante.getRowCount()+1) - Integer.parseInt(tfdQuantidadeLugares.getValue().toString()))==0)) {
                    btnSelecaoPessoa.setEnabled(false);
                } else {
                    btnSelecaoPessoa.setEnabled(true);
                }
            }
        };
        tfdPessoaCodigo.getDocument().addDocumentListener(dl);
    }

    private void criaEventoTextFieldValorRestante() {
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((!tfdValorPago.getText().equals("R$ 0,00")) && (reserva != null)) {
                    float diferenca = reserva.getVlrReserva().floatValue() - reserva.getVlrPago().floatValue();
                    lblValorRestante.setText("R$ " + String.valueOf(diferenca).replace(".", ","));
                }
            }
        };
        tfdValorPago.getDocument().addDocumentListener(dl);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgPesquisa = new javax.swing.ButtonGroup();
        btgCadastro = new javax.swing.ButtonGroup();
        btgPesquisaReserva = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnProcurarReserva = new javax.swing.JButton();
        tfdPessoaCodigo = new javax.swing.JTextField();
        tbpLocacao = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        pnlAcompanhante = new javax.swing.JPanel();
        scpAcompanhante = new javax.swing.JScrollPane();
        tbListaAcompanhante = new javax.swing.JTable();
        btnSelecaoPessoa = new javax.swing.JButton();
        pnlFields = new javax.swing.JPanel();
        lblNomeTitular = new javax.swing.JLabel();
        lblQuarto = new javax.swing.JLabel();
        lblDataEntrada = new javax.swing.JLabel();
        lblDataSaida = new javax.swing.JLabel();
        lblValorPago = new javax.swing.JLabel();
        tfdQuarto = new javax.swing.JTextField();
        tfdDataEntrada = new javax.swing.JTextField();
        tfdDataSaida = new javax.swing.JTextField();
        tfdValorPago = new hotel.support.JNumberFormatField();
        btnSelecaoTitular = new javax.swing.JButton();
        btnSelecaoQuarto = new javax.swing.JButton();
        pnlField2 = new javax.swing.JPanel();
        lblDataEntradaPrevista = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        tfdDataEntradaPrevista = new javax.swing.JTextField();
        lblSaidaPrevista = new javax.swing.JLabel();
        tfdDataSaidaPrevista = new com.toedter.calendar.JDateChooser();
        tfdNomeTitular = new javax.swing.JTextField();
        pnlValorTotal = new javax.swing.JPanel();
        lblValorRestante = new javax.swing.JLabel();
        lblQuantidadeLugares = new javax.swing.JLabel();
        tfdQuantidadeLugares = new javax.swing.JSpinner();
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

        setBackground(new java.awt.Color(255, 255, 255));

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));

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

        btnProcurarReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProcurarReserva.setForeground(new java.awt.Color(12, 91, 160));
        btnProcurarReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research.png"))); // NOI18N
        btnProcurarReserva.setText("Procurar reserva");
        btnProcurarReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProcurarReserva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProcurarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarReservaActionPerformed(evt);
            }
        });

        tfdPessoaCodigo.setName("tfdPessoaCodigo"); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProcurarReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfdPessoaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(btnProcurarReserva)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfdPessoaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbpLocacao.setBackground(new java.awt.Color(255, 255, 255));
        tbpLocacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbpLocacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpLocacaoStateChanged(evt);
            }
        });

        pnlCadastro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Cadastro de Locação ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        pnlAcompanhante.setBackground(new java.awt.Color(255, 255, 255));
        pnlAcompanhante.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acompanhantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        scpAcompanhante.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        tbListaAcompanhante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scpAcompanhante.setViewportView(tbListaAcompanhante);

        btnSelecaoPessoa.setBackground(new java.awt.Color(12, 91, 160));
        btnSelecaoPessoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSelecaoPessoa.setForeground(new java.awt.Color(255, 255, 255));
        btnSelecaoPessoa.setText("Selecionar acompanhantes");
        btnSelecaoPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecaoPessoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAcompanhanteLayout = new javax.swing.GroupLayout(pnlAcompanhante);
        pnlAcompanhante.setLayout(pnlAcompanhanteLayout);
        pnlAcompanhanteLayout.setHorizontalGroup(
            pnlAcompanhanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcompanhanteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAcompanhanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpAcompanhante)
                    .addGroup(pnlAcompanhanteLayout.createSequentialGroup()
                        .addComponent(btnSelecaoPessoa)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAcompanhanteLayout.setVerticalGroup(
            pnlAcompanhanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcompanhanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSelecaoPessoa)
                .addGap(18, 18, 18)
                .addComponent(scpAcompanhante, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));
        pnlFields.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        lblNomeTitular.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNomeTitular.setForeground(new java.awt.Color(102, 102, 102));
        lblNomeTitular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNomeTitular.setText("<html>Nome titular<font color='red'><b>*</b></font>:</html>");
        lblNomeTitular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblQuarto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblQuarto.setForeground(new java.awt.Color(102, 102, 102));
        lblQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuarto.setText("<html>Quarto<font color='red'><b>*</b></font>:</html>");

        lblDataEntrada.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataEntrada.setForeground(new java.awt.Color(102, 102, 102));
        lblDataEntrada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataEntrada.setText("<html>Data entrada<font color='red'><b>*</b></font>:</html>");
        lblDataEntrada.setToolTipText("");

        lblDataSaida.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataSaida.setForeground(new java.awt.Color(102, 102, 102));
        lblDataSaida.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataSaida.setText("<html>Data saída<font color='red'>*<b></b></font>:</html>");

        lblValorPago.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblValorPago.setForeground(new java.awt.Color(102, 102, 102));
        lblValorPago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorPago.setText("<html>Valor pago<font color='red'><b>*</b></font>:</html>");

        tfdQuarto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdQuarto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdQuarto.setName("tfdQuarto"); // NOI18N

        tfdDataEntrada.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdDataEntrada.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdDataEntrada.setName("tfdDataEntrada"); // NOI18N

        tfdDataSaida.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdDataSaida.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdDataSaida.setName("tfdDataSaida"); // NOI18N

        tfdValorPago.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdValorPago.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfdValorPago.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdValorPago.setName("tfdValorPago"); // NOI18N

        btnSelecaoTitular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N
        btnSelecaoTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecaoTitularActionPerformed(evt);
            }
        });

        btnSelecaoQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N
        btnSelecaoQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecaoQuartoActionPerformed(evt);
            }
        });

        pnlField2.setBackground(new java.awt.Color(255, 255, 255));

        lblDataEntradaPrevista.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataEntradaPrevista.setForeground(new java.awt.Color(102, 102, 102));
        lblDataEntradaPrevista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataEntradaPrevista.setText("<html>Data entrada prevista<font color='red'><b>*</b></font>:</html>");
        lblDataEntradaPrevista.setToolTipText("");

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdCodigo.setName("tfdCodigo"); // NOI18N

        tfdDataEntradaPrevista.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdDataEntradaPrevista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdDataEntradaPrevista.setName("tfdDataEntrada"); // NOI18N

        lblSaidaPrevista.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSaidaPrevista.setForeground(new java.awt.Color(102, 102, 102));
        lblSaidaPrevista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaidaPrevista.setText("<html>Data saída prevista<font color='red'>*<b></b></font>:</html>");
        lblSaidaPrevista.setToolTipText("");

        tfdDataSaidaPrevista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdDataSaidaPrevista.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        tfdDataSaidaPrevista.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlField2Layout = new javax.swing.GroupLayout(pnlField2);
        pnlField2.setLayout(pnlField2Layout);
        pnlField2Layout.setHorizontalGroup(
            pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlField2Layout.createSequentialGroup()
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDataEntradaPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(lblCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlField2Layout.createSequentialGroup()
                        .addComponent(tfdDataEntradaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSaidaPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdDataSaidaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfdCodigo)))
        );
        pnlField2Layout.setVerticalGroup(
            pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlField2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataEntradaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdDataEntradaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSaidaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfdDataSaidaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tfdNomeTitular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdNomeTitular.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdNomeTitular.setName("tfdNomeTitular"); // NOI18N

        pnlValorTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnlValorTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        lblValorRestante.setBackground(new java.awt.Color(255, 255, 255));
        lblValorRestante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblValorRestante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorRestante.setText("R$ 0,00");

        javax.swing.GroupLayout pnlValorTotalLayout = new javax.swing.GroupLayout(pnlValorTotal);
        pnlValorTotal.setLayout(pnlValorTotalLayout);
        pnlValorTotalLayout.setHorizontalGroup(
            pnlValorTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValorTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValorRestante, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlValorTotalLayout.setVerticalGroup(
            pnlValorTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValorTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblValorRestante)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        lblQuantidadeLugares.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblQuantidadeLugares.setForeground(new java.awt.Color(102, 102, 102));
        lblQuantidadeLugares.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuantidadeLugares.setText("<html>Quantidade de lugares<font color='red'><b>*</b></font>:</html>");

        tfdQuantidadeLugares.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdQuantidadeLugares.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));

        javax.swing.GroupLayout pnlFieldsLayout = new javax.swing.GroupLayout(pnlFields);
        pnlFields.setLayout(pnlFieldsLayout);
        pnlFieldsLayout.setHorizontalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addComponent(pnlField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(55, 55, 55))
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeTitular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFieldsLayout.createSequentialGroup()
                        .addComponent(lblQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFieldsLayout.createSequentialGroup()
                        .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdValorPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfdQuarto)
                            .addGroup(pnlFieldsLayout.createSequentialGroup()
                                .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecaoQuarto))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFieldsLayout.createSequentialGroup()
                        .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFieldsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfdNomeTitular))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecaoTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlFieldsLayout.setVerticalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFieldsLayout.createSequentialGroup()
                .addComponent(pnlField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSelecaoTitular))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSelecaoQuarto))
                .addGap(14, 14, 14)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAcompanhante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFields, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlAcompanhante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbpLocacao.addTab("Adicionar", pnlCadastro);

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
        btgPesquisa.add(rbNome);
        rbNome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbNome.setSelected(true);
        rbNome.setText("Por nome");
        rbNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNomeActionPerformed(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(255, 255, 255));
        btgPesquisa.add(rbCodigo);
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
                .addGap(0, 433, Short.MAX_VALUE))
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
                .addComponent(scpLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpLocacao.addTab("Listagem", pnlListagem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbpLocacao))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpLocacao)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if ((Validacao.validarCampos(pnlFields) == 0) && (Validacao.validarCampos(pnlField2) == 0)) {
            if (tbListaAcompanhante.getModel().getRowCount() + 1 == Integer.parseInt(tfdQuantidadeLugares.getValue().toString())) {
                boolean isNew = (locacao.getCodLocacao() == null);

                if (reserva != null) {
                    locacao.setCodQuarto(reserva.getCodQuarto());
                } else {
                    /// fazer dps
                }
                locacao.setCodUsuario(frmPrincipal.usuario);
                Timestamp dataEntrada = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(tfdDataEntrada.getText()));
                Timestamp dataEntradaPrevista = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(tfdDataEntradaPrevista.getText()));
                locacao.setDtaEntrada(dataEntrada);
                locacao.setDtaEntradaPrevista(dataEntradaPrevista);
                locacao.setDtaLocacao(dataEntrada);
                locacao.setDtaSaidaPrevista(tfdDataSaidaPrevista.getDate());
                locacao.setIndSituacao("A");
                double bd = Double.parseDouble(lblValorRestante.getText().replace(",", ".").replace("R$ ", ""));
                locacao.setVlrLocacao(BigDecimal.valueOf(bd));
                locacaoController.save(locacao);

                List<LocacaoHospede> locacaoHospedes = new ArrayList<LocacaoHospede>();
                LocacaoHospede lh = new LocacaoHospede();
                lh.setCodLocacao(locacao);
                lh.setCodPessoa(pessoaTitular);
                lh.setIndResponsavel("S");
                locacaoHospedes.add(lh);
                for (int i = 0; i < tbListaAcompanhante.getRowCount(); i++) {
                    lh = new LocacaoHospede();
                    Pessoa p = PessoaRepository.readId(Integer.parseInt(tbListaAcompanhante.getModel().getValueAt(i, 0).toString()));
                    lh.setCodLocacao(locacao);
                    lh.setCodPessoa(p);
                    lh.setIndResponsavel("N");
                    locacaoHospedes.add(lh);
                }
                locacaoHospedeController.saveAll(locacaoHospedes);

                if (!isNew) {
                    JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                    setVisibleCodigo(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
                }

                locacaoController.popularTabela(tblLista, 0, "");

                limparCampos();
                setAba(1);
            } else {
                JOptionPane.showMessageDialog(this, "A quantidade de lugares não bate com os acompanhantes selecionados! Por favor verifique.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        locacao = locacaoController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        popularTelaCadastro(1);
        setAba(0);
        tfdNomeTitular.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
//        Object[] options = {"Sim", "Não"};
//        int escolha = JOptionPane.showOptionDialog(null, "Você tem certeza que gostaria de excluir o registro " + tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString() + "?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//        if (escolha == 0) {
//            pessoaController.changeSituation(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
//            JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
//            pessoaController.popularTabela(tblLista, 0, "");
//        }
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
//        if (tfdPesquisa.getText().trim().isEmpty()) {
//            pessoaController.popularTabela(tblLista, 0, "");
//        } else if (rbNome.isSelected()) {
//            pessoaController.popularTabela(tblLista, 1, tfdPesquisa.getText());
//        } else if (rbCodigo.isSelected()) {
//            pessoaController.popularTabela(tblLista, 2, tfdPesquisa.getText());
//        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void tbpLocacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpLocacaoStateChanged
        habilitar();
    }//GEN-LAST:event_tbpLocacaoStateChanged

    private void btnProcurarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarReservaActionPerformed
        frmSelecaoReserva selecaoReserva;
        selecaoReserva = new frmSelecaoReserva((JFrame) SwingUtilities.getWindowAncestor(this), true, pnlFields);
        selecaoReserva.setVisible(true);
        reserva = selecaoReserva.getReserva();
        if (reserva != null) {
            popularTelaCadastro(2);
        }
    }//GEN-LAST:event_btnProcurarReservaActionPerformed

    private void btnSelecaoPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoPessoaActionPerformed
        pessoaTitular = PessoaRepository.readId(Integer.parseInt(tfdPessoaCodigo.getText()));
        frmSelecaoPessoa pessoa = new frmSelecaoPessoa((JFrame) SwingUtilities.getWindowAncestor(this), true, tbListaAcompanhante, listAcompanhante, pessoaTitular);
        pessoa.setVisible(true);
    }//GEN-LAST:event_btnSelecaoPessoaActionPerformed

    private void btnSelecaoTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoTitularActionPerformed
        limparCampos();
        frmSelecaoPessoa pessoa = new frmSelecaoPessoa((JFrame) SwingUtilities.getWindowAncestor(this), true);
        pessoa.setVisible(true);
        pessoaTitular = pessoa.getPessoaTitular();
        if (pessoaTitular != null) {
            tfdPessoaCodigo.setText(pessoaTitular.getCodPessoa().toString());
            tfdNomeTitular.setText(pessoaTitular.getNomPessoa());
        }
    }//GEN-LAST:event_btnSelecaoTitularActionPerformed

    private void btnSelecaoQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoQuartoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnSelecaoQuartoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgCadastro;
    private javax.swing.ButtonGroup btgPesquisa;
    private javax.swing.ButtonGroup btgPesquisaReserva;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnProcurarReserva;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSelecaoPessoa;
    private javax.swing.JButton btnSelecaoQuarto;
    private javax.swing.JButton btnSelecaoTitular;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblDataEntradaPrevista;
    private javax.swing.JLabel lblDataSaida;
    private javax.swing.JLabel lblNomeTitular;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblQuantidadeLugares;
    private javax.swing.JLabel lblQuarto;
    private javax.swing.JLabel lblSaidaPrevista;
    private javax.swing.JLabel lblValorPago;
    private javax.swing.JLabel lblValorRestante;
    private javax.swing.JPanel pnlAcompanhante;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlField2;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JPanel pnlValorTotal;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpAcompanhante;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tbListaAcompanhante;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpLocacao;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdDataEntrada;
    private javax.swing.JTextField tfdDataEntradaPrevista;
    private javax.swing.JTextField tfdDataSaida;
    private com.toedter.calendar.JDateChooser tfdDataSaidaPrevista;
    private javax.swing.JTextField tfdNomeTitular;
    private javax.swing.JTextField tfdPesquisa;
    private javax.swing.JTextField tfdPessoaCodigo;
    private javax.swing.JSpinner tfdQuantidadeLugares;
    private javax.swing.JTextField tfdQuarto;
    private hotel.support.JNumberFormatField tfdValorPago;
    // End of variables declaration//GEN-END:variables
}
