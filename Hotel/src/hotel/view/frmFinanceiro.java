package hotel.view;

import hotel.controller.FinanceiroController;
import hotel.model.Financeiro;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class frmFinanceiro extends javax.swing.JInternalFrame {

    FinanceiroController financeiroController;

    public frmFinanceiro() {
        initComponents();
        financeiroController = new FinanceiroController();

        buildChartDayMonthlyGross();
    }

    private void buildChartDayMonthlyGross() {
        XYSeries series = new XYSeries("Faturamento");
        Calendar cal = Calendar.getInstance();
        Map<Integer, Double> map = new HashMap();
        for (Financeiro financeiro : financeiroController.getReadAllByMonthYear(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(financeiro.getDtaPgto());
            int day = cal2.get(Calendar.DAY_OF_MONTH);
            if(map.containsKey(day)){
                map.put(day, map.get(day) + financeiro.getVlrPago().doubleValue());
            } else {
                map.put(day, financeiro.getVlrPago().doubleValue());
            }
        }
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            series.add(key.intValue(), value.doubleValue());
        }
        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Faturamento por dia",
                "Dia",
                "Reais",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 320));
        chartPanel.setVisible(true);
        pnlFields.setLayout(new java.awt.BorderLayout());
        pnlFields.add(chartPanel, BorderLayout.CENTER);
        pnlFields.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnCheckIn = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnProcurarReserva = new javax.swing.JButton();
        tbpLocacao = new javax.swing.JTabbedPane();
        pnlResumo = new javax.swing.JPanel();
        pnlAcompanhante = new javax.swing.JPanel();
        scpAcompanhante = new javax.swing.JScrollPane();
        tbListaAcompanhante = new javax.swing.JTable();
        btnSelecaoPessoa = new javax.swing.JButton();
        pnlFields = new javax.swing.JPanel();
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

        pnlResumo.setBackground(new java.awt.Color(255, 255, 255));

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
                    .addComponent(scpAcompanhante, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
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
        pnlFields.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balanço Mensal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        javax.swing.GroupLayout pnlFieldsLayout = new javax.swing.GroupLayout(pnlFields);
        pnlFields.setLayout(pnlFieldsLayout);
        pnlFieldsLayout.setHorizontalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        pnlFieldsLayout.setVerticalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlResumoLayout = new javax.swing.GroupLayout(pnlResumo);
        pnlResumo.setLayout(pnlResumoLayout);
        pnlResumoLayout.setHorizontalGroup(
            pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAcompanhante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlResumoLayout.createSequentialGroup()
                        .addComponent(pnlFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlResumoLayout.setVerticalGroup(
            pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcompanhante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbpLocacao.addTab("Resumo", pnlResumo);

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
                .addGap(0, 633, Short.MAX_VALUE))
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
                    .addComponent(tbpLocacao)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpLocacao)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed

    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed

    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnProcurarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarReservaActionPerformed

    }//GEN-LAST:event_btnProcurarReservaActionPerformed

    private void btnSelecaoPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecaoPessoaActionPerformed

    }//GEN-LAST:event_btnSelecaoPessoaActionPerformed

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
        //habilitar();
    }//GEN-LAST:event_tbpLocacaoStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnProcurarReserva;
    private javax.swing.JButton btnSelecaoPessoa;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JPanel pnlAcompanhante;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JPanel pnlResumo;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpAcompanhante;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tbListaAcompanhante;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpLocacao;
    private javax.swing.JTextField tfdPesquisa;
    // End of variables declaration//GEN-END:variables
}
