package hotel.view;

import hotel.controller.LocacaoController;
import hotel.controller.LocacaoHospedeController;
import hotel.model.Locacao;
import hotel.model.LocacaoHospede;
import hotel.model.Parametro;
import hotel.model.Pessoa;
import hotel.model.Quarto;
import hotel.model.Reserva;
import hotel.repository.PessoaRepository;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import hotel.support.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
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
        btnSelecaoPessoa.setEnabled(false);
        locacao = new Locacao();
        locacaoHospedeController = new LocacaoHospedeController();
        locacaoController = new LocacaoController();
        locacaoHospede = new ArrayList<>();
        locacaoController.popularTabela(tblLista, 0, "");
        setFieldsEditable(true);
        setMinDateCombo();
        criaEventoTextFieldValorRestante();
        habilitar();
        setAba(0);
        setVisibleCodigo(false);
        setVisibleDatasPrevistas(true);
        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });
    }

    private void setFieldsEditable(boolean editable) {
        tfdValorPago.setEditable(!editable);
        tfdCodigo.setEditable(!editable);
        tfdDataEntrada.setLocked(editable);
        tfdValorTotal.setEditable(!editable);
        tfdQuarto.setEditable(!editable);
        tfdNomeTitular.setEditable(!editable);
    }

    private void setMinDateCombo() {
        tfdDataEntrada.setLocked(false);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        Calendar minSelectableDate = Calendar.getInstance();
        minSelectableDate.setTime(Date.valueOf(now.toLocalDate()));
        tfdDataSaidaPrevista.setMinDate(minSelectableDate);
        tfdDataSaidaPrevista.setText(dateTimeFormatter.format(now));
        tfdDataEntrada.setText(dateTimeFormatter.format(now.minusDays(1)));
        tfdDataEntrada.setLocked(true);
        //tfdDataSaidaPrevista.setCurrent(minSelectableDate);
    }

    private void setVisibleCodigo(boolean isVisible) {
        lblCodigo.setVisible(isVisible);
        tfdCodigo.setVisible(isVisible);

        if (isVisible) {
            lblCodigo.setEnabled(!isVisible);
            tfdCodigo.setEnabled(!isVisible);
        }
    }

    private void setVisibleDatasPrevistas(boolean isVisible) {
        lblDataEntrada.setVisible(isVisible);
        tfdDataEntrada.setVisible(isVisible);
        lblSaidaPrevista.setVisible(isVisible);
        tfdDataSaidaPrevista.setVisible(isVisible);

        if (isVisible) {

        }
    }

    private void setDataTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        tfdDataEntrada.setText(dtf.format(now));
    }

    private void habilitar() {
        if (tbpLocacao.getSelectedIndex() == 0) {
            btnCheckIn.setEnabled(true);
            btnCheckOut.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnProcurarReserva.setEnabled(true);
            tblLista.clearSelection();
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 6));
            }

            btnCheckIn.setEnabled(false);
            btnProcurarReserva.setEnabled(false);
            btnCheckOut.setEnabled(lSituacao.equals("Em aberto"));
            btnCancelar.setEnabled(lSituacao.equals("Em aberto"));
            limparCampos();
        }
    }

    private void limparCampos() {
        locacao = new Locacao();
        reserva = null;
        setInfoPessoa(null);
        LimpaCampos.LimparCampos(pnlFields);
        LimpaCampos.LimparCampos(pnlField2);
        tfdValorTotal.setValue(BigDecimal.ZERO);
        setMinDateCombo();
        lblValorRestante.setText("R$ 0,00");
        DefaultTableModel dm = (DefaultTableModel) tbListaAcompanhante.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
        setDataTimeNow();
        setJSpinnerEditable(true);
    }

    private void setAba(int pIndex) {
        tbpLocacao.setSelectedIndex(pIndex);

        habilitar();
    }

    private void setJSpinnerEditable(boolean pEditable) {
        tfdQuantidadeLugares.setEnabled(pEditable);
        if (tfdQuantidadeLugares.getEditor() instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) tfdQuantidadeLugares.getEditor();
            if (pEditable == false) {
                editor.getTextField().setEnabled(!pEditable);
            } else {
                editor.getTextField().setEnabled(pEditable);
            }
            editor.getTextField().setEditable(pEditable);
        }
    }

    public void popularTelaCadastro(int pOption) {
        pessoaTitular = reserva.getPessoa();
        quarto = reserva.getQuarto();
        tfdValorTotal.setText(title);
        tfdNomeTitular.setText(pessoaTitular.getNomPessoa());
        tfdQuantidadeLugares.setValue(reserva.getQtdLugar());
        tfdValorPago.setValue(reserva.getVlrPago());
        tfdQuarto.setText(quarto.getNumQuarto());
        //tfdDataEntrada.setText(Formatacao.ajustaDataDMAShort(reserva.getDtaEntrada().toString()));
        tfdDataSaidaPrevista.setText(Formatacao.ajustaDataDMAShort(reserva.getDtaSaida().toString()));
        setVisibleDatasPrevistas(true);
        setJSpinnerEditable(false);
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

    private void habilitarSelecaoAcompanhante() {
        if ((pessoaTitular == null) || ((Integer.parseInt(tfdQuantidadeLugares.getValue().toString()) - ((tbListaAcompanhante.getRowCount() + 1))) < 1)) {
            btnSelecaoPessoa.setEnabled(false);
        } else {
            btnSelecaoPessoa.setEnabled(true);
        }
    }

    private void setInfoPessoa(Pessoa pessoa) {
        if (pessoa != null) {
            tfdNomeTitular.setText(pessoa.getNomPessoa());
            pessoaTitular = pessoa;
        } else {
            tfdNomeTitular.setText("");
            pessoaTitular = null;
        }

    }

    private void setInfoQuarto(Quarto quarto) {
        if (quarto != null) {
            tfdQuarto.setText(quarto.getNumQuarto());
            locacao.setQuarto(quarto);
            this.quarto = quarto;
        } else {
            locacao.setQuarto(new Quarto());
            tfdQuarto.setText("");
            this.quarto = null;
        }
        calculaValorLocacao();
    }

    private void calculaValorLocacao() {
        if (quarto != null) {
            long days = calculaDiasEntreDatas() + 1;
            double valorTotal;
            if (reserva != null) {
                valorTotal = (days * reserva.getQuarto().getVlrQuarto().doubleValue());
                tfdValorTotal.setValue((BigDecimal.valueOf(valorTotal)));
                valorTotal = valorTotal - reserva.getVlrPago().doubleValue();
            } else {
                valorTotal = days * quarto.getVlrQuarto().doubleValue();
                tfdValorTotal.setValue((BigDecimal.valueOf(valorTotal)));
            }
            lblValorRestante.setText("R$ " + String.valueOf(valorTotal).replace(".", ","));
        }
    }

    private long calculaDiasEntreDatas() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate dateEntrada = LocalDate.parse(tfdDataEntrada.getText(), dtf);
        LocalDate dateSaida = LocalDate.parse(tfdDataSaidaPrevista.getText(), dtf);
        return ChronoUnit.DAYS.between(dateEntrada, dateSaida);
    }

    private void habilitarQuarto() {
        if (tfdDataEntrada.getText().isEmpty() || tfdDataSaidaPrevista.getText().isEmpty() || Integer.parseInt(tfdQuantidadeLugares.getValue().toString()) <= 0) {
            btnSelecaoQuarto.setEnabled(false);
        } else {
            if (reserva == null) {
                btnSelecaoQuarto.setEnabled(true);
            }
        }
        if (reserva == null) {
            setInfoQuarto(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgPesquisa = new javax.swing.ButtonGroup();
        btgCadastro = new javax.swing.ButtonGroup();
        btgPesquisaReserva = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        btnCheckIn = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnProcurarReserva = new javax.swing.JButton();
        tbpLocacao = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        pnlAcompanhante = new javax.swing.JPanel();
        scpAcompanhante = new javax.swing.JScrollPane();
        tbListaAcompanhante = new javax.swing.JTable();
        btnSelecaoPessoa = new javax.swing.JButton();
        pnlFields = new javax.swing.JPanel();
        lblNomeTitular = new javax.swing.JLabel();
        lblQuarto = new javax.swing.JLabel();
        lblValorPago = new javax.swing.JLabel();
        tfdQuarto = new javax.swing.JTextField();
        tfdValorPago = new hotel.support.JNumberFormatField();
        btnSelecaoTitular = new javax.swing.JButton();
        btnSelecaoQuarto = new javax.swing.JButton();
        pnlField2 = new javax.swing.JPanel();
        lblDataEntrada = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblSaidaPrevista = new javax.swing.JLabel();
        tfdDataEntrada = new hotel.support.JDateChooserComboLayout();
        tfdDataSaidaPrevista = new hotel.support.JDateChooserComboLayout();
        tfdNomeTitular = new javax.swing.JTextField();
        pnlValorTotal = new javax.swing.JPanel();
        lblValorRestante = new javax.swing.JLabel();
        lblQuantidadeLugares = new javax.swing.JLabel();
        tfdQuantidadeLugares = new javax.swing.JSpinner();
        lblValorTotal = new javax.swing.JLabel();
        tfdValorTotal = new hotel.support.JNumberFormatField();
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

        btnCheckIn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCheckIn.setForeground(new java.awt.Color(12, 91, 160));
        btnCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/save.png"))); // NOI18N
        btnCheckIn.setText("Check-in");
        btnCheckIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCheckIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInActionPerformed(evt);
            }
        });

        btnCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCheckOut.setForeground(new java.awt.Color(12, 91, 160));
        btnCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/edit.png"))); // NOI18N
        btnCheckOut.setText("Check-out");
        btnCheckOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCheckOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
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

        btnProcurarReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProcurarReserva.setForeground(new java.awt.Color(12, 91, 160));
        btnProcurarReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research.png"))); // NOI18N
        btnProcurarReserva.setText("Reserva");
        btnProcurarReserva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProcurarReserva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProcurarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCheckIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProcurarReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCheckIn)
            .addComponent(btnCheckOut)
            .addComponent(btnCancelar)
            .addComponent(btnFechar)
            .addComponent(btnProcurarReserva)
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

        lblValorPago.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblValorPago.setForeground(new java.awt.Color(102, 102, 102));
        lblValorPago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorPago.setText("<html>Valor pago<font color='red'><b>*</b></font>:</html>");

        tfdQuarto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdQuarto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdQuarto.setName("tfdQuarto"); // NOI18N

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

        lblDataEntrada.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataEntrada.setForeground(new java.awt.Color(102, 102, 102));
        lblDataEntrada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataEntrada.setText("<html>Data entrada<font color='red'><b>*</b></font>:</html>");
        lblDataEntrada.setToolTipText("");

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("<html>Código<font color='red'><b>*</b></font>:</html>");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdCodigo.setName("tfdCodigo"); // NOI18N

        lblSaidaPrevista.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSaidaPrevista.setForeground(new java.awt.Color(102, 102, 102));
        lblSaidaPrevista.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaidaPrevista.setText("<html>Data saída prevista<font color='red'>*<b></b></font>:</html>");
        lblSaidaPrevista.setToolTipText("");

        tfdDataEntrada.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataEntrada.setNothingAllowed(false);
        tfdDataEntrada.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tfdDataEntrada.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        tfdDataEntrada.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                tfdDataEntradaOnCommit(evt);
            }
        });

        tfdDataSaidaPrevista.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataSaidaPrevista.setNothingAllowed(false);
        tfdDataSaidaPrevista.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tfdDataSaidaPrevista.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        tfdDataSaidaPrevista.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                tfdDataSaidaPrevistaOnCommit(evt);
            }
        });

        javax.swing.GroupLayout pnlField2Layout = new javax.swing.GroupLayout(pnlField2);
        pnlField2.setLayout(pnlField2Layout);
        pnlField2Layout.setHorizontalGroup(
            pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlField2Layout.createSequentialGroup()
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDataEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(lblCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlField2Layout.createSequentialGroup()
                        .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblSaidaPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdDataSaidaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addComponent(tfdCodigo)))
        );
        pnlField2Layout.setVerticalGroup(
            pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlField2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlField2Layout.createSequentialGroup()
                        .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlField2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSaidaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfdDataSaidaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))))
        );

        tfdNomeTitular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdNomeTitular.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdNomeTitular.setName("tfdNomeTitular"); // NOI18N

        pnlValorTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnlValorTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Devedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

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
            .addComponent(lblValorRestante)
        );

        lblQuantidadeLugares.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblQuantidadeLugares.setForeground(new java.awt.Color(102, 102, 102));
        lblQuantidadeLugares.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQuantidadeLugares.setText("<html>Quantidade de lugares<font color='red'><b>*</b></font>:</html>");

        tfdQuantidadeLugares.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdQuantidadeLugares.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        tfdQuantidadeLugares.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tfdQuantidadeLugaresStateChanged(evt);
            }
        });

        lblValorTotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(102, 102, 102));
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotal.setText("<html>Valor total<font color='red'><b>*</b></font>:</html>");

        tfdValorTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        tfdValorTotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfdValorTotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdValorTotal.setName("tfdValorPago"); // NOI18N

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
                    .addComponent(lblNomeTitular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFieldsLayout.createSequentialGroup()
                        .addComponent(tfdNomeTitular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecaoTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFieldsLayout.createSequentialGroup()
                        .addComponent(tfdQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFieldsLayout.createSequentialGroup()
                        .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfdValorTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfdValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfdQuarto, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecaoQuarto))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFieldsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFieldsLayout.setVerticalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFieldsLayout.createSequentialGroup()
                .addComponent(pnlField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSelecaoTitular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdQuantidadeLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelecaoQuarto))
                    .addComponent(lblQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(pnlValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(pnlFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        if ((Validacao.validarCampos(pnlFields) == 0) && (Validacao.validarCampos(pnlField2) == 0)) {
            if (tbListaAcompanhante.getModel().getRowCount() + 1 <= Integer.parseInt(tfdQuantidadeLugares.getValue().toString())) {
                boolean isNew = (locacao.getCodLocacao() == null);

                locacao.setUsuario(Parametro.USUARIO);
                Timestamp dtaEntrada = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(Unit.getDataHoraAtual()));
                //Timestamp dtaSaidaPrevista = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(tfdDataSaidaPrevista.getCurrent().getTime().toString()));
                locacao.setDtaEntrada(dtaEntrada);
                locacao.setQuarto(quarto);
                locacao.setDtaSaidaPrevista(tfdDataSaidaPrevista.getCurrent().getTime());
                locacao.setIndSituacao("A");
                double bd = Double.parseDouble(lblValorRestante.getText().replace(",", ".").replace("R$ ", ""));
                locacao.setVlrLocacao(BigDecimal.valueOf(bd));
                locacaoController.saveOver(locacao);
                System.out.println("Cod: " + locacao.getCodLocacao());
                List<LocacaoHospede> locacaoHospedes = new ArrayList<LocacaoHospede>();
                LocacaoHospede lh = new LocacaoHospede();
                lh.setLocacao(locacao);
                lh.setPessoa(pessoaTitular);
                lh.setIndResponsavel("S");
                locacaoHospedes.add(lh);
                for (int i = 0; i < tbListaAcompanhante.getRowCount(); i++) {
                    lh = new LocacaoHospede();
                    Pessoa p = PessoaRepository.readId(Integer.parseInt(tbListaAcompanhante.getModel().getValueAt(i, 0).toString()));
                    lh.setLocacao(locacao);
                    lh.setPessoa(p);
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
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        locacao = locacaoController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        Timestamp dtaSaida = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(Unit.getDataHoraAtual()));
        locacao.setDtaSaida(dtaSaida);
        locacaoController.changeSituation(locacao.getCodLocacao(), "F");
        locacaoController.popularTabela(tblLista, 0, "");
        limparCampos();
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        locacao = locacaoController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        locacaoController.changeSituation(locacao.getCodLocacao(), "C");
        locacaoController.popularTabela(tblLista, 0, "");
        limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            calculaValorLocacao();
        }
    }//GEN-LAST:event_btnProcurarReservaActionPerformed

    private void btnSelecaoPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoPessoaActionPerformed
        pessoaTitular = PessoaRepository.readId(pessoaTitular.getCodPessoa());
        frmSelecaoPessoa pessoa = new frmSelecaoPessoa((JFrame) SwingUtilities.getWindowAncestor(this), true, listAcompanhante, pessoaTitular, Integer.parseInt(tfdQuantidadeLugares.getValue().toString()));
        pessoa.setVisible(true);
        listAcompanhante = pessoa.getListPessoas();
        populaTableAcompanhantes(listAcompanhante);
    }//GEN-LAST:event_btnSelecaoPessoaActionPerformed

    private void btnSelecaoTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoTitularActionPerformed
        frmPesquisaPessoa pesquisaPessoa = new frmPesquisaPessoa((JFrame) SwingUtilities.getWindowAncestor(this), true);
        pesquisaPessoa.setVisible(true);
        setInfoPessoa(pesquisaPessoa.getPessoa());
    }//GEN-LAST:event_btnSelecaoTitularActionPerformed

    private void btnSelecaoQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoQuartoActionPerformed
        frmPesquisaQuarto pesquisaQuarto = new frmPesquisaQuarto(null, true, tfdDataEntrada.getText(), tfdDataSaidaPrevista.getText(), Integer.parseInt(tfdQuantidadeLugares.getValue().toString()));
        pesquisaQuarto.setVisible(true);
        setInfoQuarto(pesquisaQuarto.getQuarto());
    }//GEN-LAST:event_btnSelecaoQuartoActionPerformed

    private void tfdDataEntradaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_tfdDataEntradaOnCommit
        habilitarQuarto();
        calculaValorLocacao();
    }//GEN-LAST:event_tfdDataEntradaOnCommit

    private void tfdQuantidadeLugaresStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tfdQuantidadeLugaresStateChanged
        habilitarQuarto();
        habilitarSelecaoAcompanhante();
    }//GEN-LAST:event_tfdQuantidadeLugaresStateChanged

    private void tfdDataSaidaPrevistaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_tfdDataSaidaPrevistaOnCommit
        habilitarQuarto();
        calculaValorLocacao();
    }//GEN-LAST:event_tfdDataSaidaPrevistaOnCommit

    private void populaTableAcompanhantes(List<Pessoa> list) {
        clearTabela(tbListaAcompanhante);
        DefaultTableModel model = (DefaultTableModel) tbListaAcompanhante.getModel();
        for (Pessoa p : list) {
            model.addRow(new Object[]{p.getCodPessoa(), p.getNomPessoa(), p.getNumCpf(), p.getDesEmail()});
        }
    }

    private void clearTabela(JTable tblTable) {
        DefaultTableModel modelAcompanhante = (DefaultTableModel) tblTable.getModel();
        modelAcompanhante.setRowCount(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgCadastro;
    private javax.swing.ButtonGroup btgPesquisa;
    private javax.swing.ButtonGroup btgPesquisaReserva;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnProcurarReserva;
    private javax.swing.JButton btnSelecaoPessoa;
    private javax.swing.JButton btnSelecaoQuarto;
    private javax.swing.JButton btnSelecaoTitular;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblNomeTitular;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblQuantidadeLugares;
    private javax.swing.JLabel lblQuarto;
    private javax.swing.JLabel lblSaidaPrevista;
    private javax.swing.JLabel lblValorPago;
    private javax.swing.JLabel lblValorRestante;
    private javax.swing.JLabel lblValorTotal;
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
    private hotel.support.JDateChooserComboLayout tfdDataEntrada;
    private hotel.support.JDateChooserComboLayout tfdDataSaidaPrevista;
    private javax.swing.JTextField tfdNomeTitular;
    private javax.swing.JTextField tfdPesquisa;
    private javax.swing.JSpinner tfdQuantidadeLugares;
    private javax.swing.JTextField tfdQuarto;
    private hotel.support.JNumberFormatField tfdValorPago;
    private hotel.support.JNumberFormatField tfdValorTotal;
    // End of variables declaration//GEN-END:variables
}
