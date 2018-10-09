package hotel.view;

import hotel.controller.FinanceiroController;
import hotel.controller.LocacaoController;
import hotel.model.*;
import hotel.repository.FormaPagamentoRepository;
import hotel.support.Formatacao;
import hotel.support.Report;
import hotel.support.Unit;
import hotel.support.Validacao;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import net.sf.jasperreports.engine.JasperPrint;

public class frmSelecaoPagamento extends javax.swing.JDialog {

    Financeiro financeiro;
    FinanceiroController financeiroController;
    LocacaoController locacaoController;
    Locacao locacao;
    FormaPagamento formaPagamento;
    double valorTotal;
    boolean Result = false;

    public frmSelecaoPagamento(java.awt.Frame parent, boolean modal, Locacao locacao, double valor) {
        super(parent, modal);
        initComponents();
        financeiro = new Financeiro();
        financeiroController = new FinanceiroController();
        locacaoController = new LocacaoController();
        this.locacao = locacao;
        this.valorTotal = valor;
        populaComboFormaPagamento();
        addEventJSpinner();
        tfdValorParcela.setValue(BigDecimal.valueOf(valorTotal));
        tfdParcela.setModel(new SpinnerNumberModel(1, 1, 1, 1));
        tfdValorParcela.setEditable(false);
        String[] split = cmbFormaPagamento.getSelectedItem().toString().split(" ");
        int codigo = Integer.parseInt(split[0]);
        formaPagamento = FormaPagamentoRepository.readId(codigo);

        setTitle("Hotel Integrador - Seleção de Forma de Pagamento");
        setLocationRelativeTo(null);
    }

    private void populaComboFormaPagamento() {
        List<String> lc;
        lc = new ArrayList<>();
        for (FormaPagamento fp : FormaPagamentoRepository.readAll()) {
            lc.add(fp.getCodFormaPgto() + " " + fp.getDesFormaPgto());
        }
        cmbFormaPagamento.setModel(new DefaultComboBoxModel(lc.toArray()));
    }

    public boolean getResult() {
        return Result;
    }

    private void addEventJSpinner() {
        JComponent comp = tfdParcela.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        tfdParcela.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (cmbFormaPagamento.getSelectedItem() != null) {
                    String[] split = cmbFormaPagamento.getSelectedItem().toString().split(" ");
                    int codigo = Integer.parseInt(split[0]);
                    divideTotal(codigo);
                }
            }
        });
    }

    private void divideTotal(int codigo) {
        if ((codigo == 1) || (codigo == 4)) {
            tfdValorParcela.setValue(BigDecimal.valueOf(valorTotal));
        } else {
            double valorParcela = valorTotal / Integer.parseInt(tfdParcela.getValue().toString());
            tfdValorParcela.setValue(BigDecimal.valueOf(valorParcela));
        }
    }

    private void createReportLocacao(Locacao locacao) {
        try {
            Map map = new HashMap();
            map.put("caminhoImagem", getClass().getResource("/hotel/images/logo_1.png").getPath());
            map.put("subTitulo", "Relatório de Locação e Consumíveis");
            map.put("SUBREPORT_DIR", getClass().getResource("/hotel/report/relatorio_locacao_usuario_consumiveis.jasper").getPath());
            map.put("cod_locacao", locacao.getCodLocacao());

            Report report = new Report();
            JasperPrint impressao = report.openReport(map, "relatorio_locacao_usuario");
            report.exportPDFDir(impressao, Parametro.DIR_FINANCEIRO, "L" + locacao.getCodLocacao().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        cmbFormaPagamento = new javax.swing.JComboBox<>();
        lblFormaPagamento = new javax.swing.JLabel();
        lblParcela = new javax.swing.JLabel();
        tfdParcela = new javax.swing.JSpinner();
        lblValorParcela = new javax.swing.JLabel();
        tfdValorParcela = new hotel.support.JNumberFormatField();
        pnlHeader = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Selecione o Pagamento ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cmbFormaPagamento.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbFormaPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFormaPagamentoItemStateChanged(evt);
            }
        });

        lblFormaPagamento.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblFormaPagamento.setForeground(new java.awt.Color(102, 102, 102));
        lblFormaPagamento.setText("<html>Forma de pagamento<font color='red'><b>*</b></font>:</html>");
        lblFormaPagamento.setToolTipText("");

        lblParcela.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblParcela.setForeground(new java.awt.Color(102, 102, 102));
        lblParcela.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblParcela.setText("<html>Parcelas<font color='red'><b>*</b></font>:</html>");
        lblParcela.setToolTipText("");

        tfdParcela.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdParcela.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));

        lblValorParcela.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblValorParcela.setForeground(new java.awt.Color(102, 102, 102));
        lblValorParcela.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorParcela.setText("<html>Valor parcela:<font color='red'><b>*</b></font>:</html>");
        lblValorParcela.setToolTipText("");

        tfdValorParcela.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblValorParcela)
                    .addComponent(lblParcela)
                    .addComponent(lblFormaPagamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFormaPagamento, 0, 181, Short.MAX_VALUE)
                    .addComponent(tfdParcela)
                    .addComponent(tfdValorParcela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(12, 91, 160));
        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/confirm.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfirmar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
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
                .addComponent(btnConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConfirmar)
            .addComponent(btnFechar)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (Validacao.validarCampos(pnlMain) == 0 && tfdValorParcela.getValue().doubleValue() != 0) {
            for (int i = 1; i <= Integer.parseInt(tfdParcela.getValue().toString()); i++) {
                if (i == 1) {
                    LocalDateTime dateTime = LocalDateTime.now();
                    Timestamp dtaVencimento = Timestamp.valueOf(dateTime);
                    financeiro.setDtaVencimento(dtaVencimento);
                    financeiro.setDtaPgto(dtaVencimento);
                    financeiro.setFormaPgto(formaPagamento);
                    financeiro.setLocacao(locacao);
                    financeiro.setParcela(i);
                    financeiro.setVlrFinanceiro(tfdValorParcela.getValue());
                    financeiro.setVlrPago(tfdValorParcela.getValue());

                    financeiroController.save(financeiro);
                    financeiro = new Financeiro();
                } else {
                    LocalDateTime dateTime = LocalDateTime.now();
                    Timestamp dtaPagamento = Timestamp.valueOf(dateTime);
                    dtaPagamento.setDate(10);
                    dtaPagamento.setMonth(dtaPagamento.getMonth() + (i - 1));
                    financeiro.setDtaVencimento(dtaPagamento);
                    financeiro.setFormaPgto(formaPagamento);
                    financeiro.setLocacao(locacao);
                    financeiro.setParcela(i);
                    financeiro.setVlrFinanceiro(tfdValorParcela.getValue());

                    financeiroController.save(financeiro);
                    financeiro = new Financeiro();
                }
            }
            locacao.setVlrLocacao(BigDecimal.valueOf(valorTotal));
            locacaoController.save(locacao);
            JOptionPane.showMessageDialog(null, "Pagamento registrado com sucesso!");
            Timestamp dtaSaida = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(Unit.getDataHoraAtual()));
            locacao.setDtaSaida(dtaSaida);
            locacaoController.changeSituation(locacao.getCodLocacao(), "F");
            createReportLocacao(locacao);
            Result = true;
            this.dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void cmbFormaPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFormaPagamentoItemStateChanged
        if (cmbFormaPagamento.getSelectedItem() != null) {
            String[] split = cmbFormaPagamento.getSelectedItem().toString().split(" ");
            int codigo = Integer.parseInt(split[0]);
            formaPagamento = FormaPagamentoRepository.readId(codigo);
            if ((codigo == 1) || (codigo == 4)) {
                tfdParcela.setModel(new SpinnerNumberModel(1, 1, 1, 1));
            } else {
                tfdParcela.setModel(new SpinnerNumberModel(1, 1, 6, 1));
            }
            divideTotal(codigo);
        }
    }//GEN-LAST:event_cmbFormaPagamentoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JComboBox<String> cmbFormaPagamento;
    private javax.swing.JLabel lblFormaPagamento;
    private javax.swing.JLabel lblParcela;
    private javax.swing.JLabel lblValorParcela;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JSpinner tfdParcela;
    private hotel.support.JNumberFormatField tfdValorParcela;
    // End of variables declaration//GEN-END:variables
}
