package hotel.view;

import hotel.controller.LocacaoConsumivelController;
import hotel.controller.LocacaoController;
import hotel.model.Locacao;
import hotel.model.LocacaoHospede;
import hotel.repository.LocacaoHospedeRepository;
import hotel.support.Formatacao;
import hotel.support.Unit;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class frmConfirmarCheckOut extends javax.swing.JDialog {

    Locacao locacao;
    LocacaoController locacaoController;
    LocacaoConsumivelController locacaoConsumivelController;

    public frmConfirmarCheckOut(java.awt.Frame parent, boolean modal, Locacao locacao) {
        super(parent, modal);
        initComponents();
        this.locacao = locacao;
        locacaoController = new LocacaoController();
        locacaoConsumivelController = new LocacaoConsumivelController();
        populaTelaLocacao();
        populaConsumiveis();
        calculaValorTotal();
        
        setTitle("Hotel Integrador - Confirmar Check-Out");
        setLocationRelativeTo(null);
    }

    private void populaTelaLocacao() {
        LocalDate dateSaida = LocalDate.now();
        LocacaoHospede lh = LocacaoHospedeRepository.readResponsavel(locacao.getCodLocacao());
        tfdCodigo.setText(locacao.getCodLocacao().toString());
        tfdDataEntrada.setText(Formatacao.ajustaDataDMA(locacao.getDtaEntrada().toString()));
        tfdDataSaida.setText(Formatacao.ajustaDataDMA(dateSaida.toString()));
        tfdNomeTitular.setText(lh.getPessoa().getNomPessoa());
        tfdQuarto.setText(locacao.getQuarto().getNumQuarto());
        calculaValorLocacao();
    }

    private long calculaDiasEntreDatas() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEntrada = LocalDate.parse(tfdDataEntrada.getText(), dtf);
        LocalDate dateSaida = LocalDate.parse(tfdDataSaida.getText(), dtf);
        return ChronoUnit.DAYS.between(dateEntrada, dateSaida);
    }

    private void calculaValorLocacao() {
        long days = calculaDiasEntreDatas() + 1;
        double valorTotal;
        valorTotal = days * locacao.getQuarto().getVlrQuarto().doubleValue();
        tfdSubTotalLocacao.setValue((BigDecimal.valueOf(valorTotal)));
    }

    private void populaConsumiveis() {
        locacaoConsumivelController.popularTabela(tblConsumiveis, 4, locacao.getCodLocacao().toString());
        double subTotal = 0;
        for (int i = 0; i < tblConsumiveis.getModel().getRowCount(); i++) {
            subTotal += Double.parseDouble(tblConsumiveis.getModel().getValueAt(i, 3).toString());
        }
        tfdSubTotalConsumiveis.setValue(BigDecimal.valueOf(subTotal));
    }
    
    private void calculaValorTotal() {
        tfdTotal.setValue(tfdSubTotalConsumiveis.getValue().add(tfdSubTotalLocacao.getValue()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        pnlInfo = new javax.swing.JPanel();
        pnlInfoLocacao = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblNomeTitular = new javax.swing.JLabel();
        tfdNomeTitular = new javax.swing.JTextField();
        lblQuarto = new javax.swing.JLabel();
        tfdQuarto = new javax.swing.JTextField();
        lblDataEntrada = new javax.swing.JLabel();
        tfdDataEntrada = new javax.swing.JTextField();
        lblDataSaida = new javax.swing.JLabel();
        tfdDataSaida = new javax.swing.JTextField();
        pnlSubTotalLocacao = new javax.swing.JPanel();
        tfdSubTotalLocacao = new hotel.support.JNumberFormatField();
        pnlInfoConsumiveis = new javax.swing.JPanel();
        pnlSubTotalConsumiveis = new javax.swing.JPanel();
        tfdSubTotalConsumiveis = new hotel.support.JNumberFormatField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsumiveis = new javax.swing.JTable();
        pnlTotal = new javax.swing.JPanel();
        tfdTotal = new hotel.support.JNumberFormatField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        pnlInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Informações da Despesas de Locação ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        pnlInfoLocacao.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoLocacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblCodigo.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCodigo.setText("Código:");

        tfdCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblNomeTitular.setBackground(new java.awt.Color(255, 255, 255));
        lblNomeTitular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNomeTitular.setText("Nome:");

        tfdNomeTitular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfdNomeTitular.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblQuarto.setBackground(new java.awt.Color(255, 255, 255));
        lblQuarto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuarto.setText("Quarto:");

        tfdQuarto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfdQuarto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblDataEntrada.setBackground(new java.awt.Color(255, 255, 255));
        lblDataEntrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDataEntrada.setText("Data entrada:");

        tfdDataEntrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfdDataEntrada.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblDataSaida.setBackground(new java.awt.Color(255, 255, 255));
        lblDataSaida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDataSaida.setText("Data saida:");

        tfdDataSaida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfdDataSaida.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        pnlSubTotalLocacao.setBackground(new java.awt.Color(255, 255, 255));
        pnlSubTotalLocacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sub-total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tfdSubTotalLocacao.setBorder(null);
        tfdSubTotalLocacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfdSubTotalLocacao.setToolTipText("");
        tfdSubTotalLocacao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlSubTotalLocacaoLayout = new javax.swing.GroupLayout(pnlSubTotalLocacao);
        pnlSubTotalLocacao.setLayout(pnlSubTotalLocacaoLayout);
        pnlSubTotalLocacaoLayout.setHorizontalGroup(
            pnlSubTotalLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubTotalLocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfdSubTotalLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSubTotalLocacaoLayout.setVerticalGroup(
            pnlSubTotalLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubTotalLocacaoLayout.createSequentialGroup()
                .addComponent(tfdSubTotalLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlInfoLocacaoLayout = new javax.swing.GroupLayout(pnlInfoLocacao);
        pnlInfoLocacao.setLayout(pnlInfoLocacaoLayout);
        pnlInfoLocacaoLayout.setHorizontalGroup(
            pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoLocacaoLayout.createSequentialGroup()
                        .addGroup(pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblQuarto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoLocacaoLayout.createSequentialGroup()
                                .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNomeTitular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfdNomeTitular))
                            .addGroup(pnlInfoLocacaoLayout.createSequentialGroup()
                                .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDataEntrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDataSaida)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfdDataSaida))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoLocacaoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlSubTotalLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlInfoLocacaoLayout.setVerticalGroup(
            pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeTitular)
                    .addComponent(tfdNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuarto)
                    .addComponent(tfdQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataEntrada)
                    .addComponent(tfdDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataSaida)
                    .addComponent(tfdDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSubTotalLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlInfoConsumiveis.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfoConsumiveis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumíveis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        pnlSubTotalConsumiveis.setBackground(new java.awt.Color(255, 255, 255));
        pnlSubTotalConsumiveis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sub-total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tfdSubTotalConsumiveis.setBorder(null);
        tfdSubTotalConsumiveis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfdSubTotalConsumiveis.setToolTipText("");
        tfdSubTotalConsumiveis.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlSubTotalConsumiveisLayout = new javax.swing.GroupLayout(pnlSubTotalConsumiveis);
        pnlSubTotalConsumiveis.setLayout(pnlSubTotalConsumiveisLayout);
        pnlSubTotalConsumiveisLayout.setHorizontalGroup(
            pnlSubTotalConsumiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubTotalConsumiveisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfdSubTotalConsumiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSubTotalConsumiveisLayout.setVerticalGroup(
            pnlSubTotalConsumiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubTotalConsumiveisLayout.createSequentialGroup()
                .addComponent(tfdSubTotalConsumiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblConsumiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblConsumiveis);

        javax.swing.GroupLayout pnlInfoConsumiveisLayout = new javax.swing.GroupLayout(pnlInfoConsumiveis);
        pnlInfoConsumiveis.setLayout(pnlInfoConsumiveisLayout);
        pnlInfoConsumiveisLayout.setHorizontalGroup(
            pnlInfoConsumiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoConsumiveisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoConsumiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoConsumiveisLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlSubTotalConsumiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlInfoConsumiveisLayout.setVerticalGroup(
            pnlInfoConsumiveisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoConsumiveisLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlSubTotalConsumiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnlTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tfdTotal.setBorder(null);
        tfdTotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfdTotal.setToolTipText("");
        tfdTotal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfdTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addComponent(tfdTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInfoLocacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInfoConsumiveis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInfoLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlInfoConsumiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        frmSelecaoPagamento selecaoPagamento;
        selecaoPagamento = new frmSelecaoPagamento((JFrame) SwingUtilities.getWindowAncestor(this), true, locacao, tfdTotal.getValue().doubleValue());
        selecaoPagamento.setVisible(true);
        if (selecaoPagamento.getResult()) {
            this.dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblDataSaida;
    private javax.swing.JLabel lblNomeTitular;
    private javax.swing.JLabel lblQuarto;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlInfoConsumiveis;
    private javax.swing.JPanel pnlInfoLocacao;
    private javax.swing.JPanel pnlSubTotalConsumiveis;
    private javax.swing.JPanel pnlSubTotalLocacao;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JTable tblConsumiveis;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdDataEntrada;
    private javax.swing.JTextField tfdDataSaida;
    private javax.swing.JTextField tfdNomeTitular;
    private javax.swing.JTextField tfdQuarto;
    private hotel.support.JNumberFormatField tfdSubTotalConsumiveis;
    private hotel.support.JNumberFormatField tfdSubTotalLocacao;
    private hotel.support.JNumberFormatField tfdTotal;
    // End of variables declaration//GEN-END:variables
}
