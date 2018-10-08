package hotel.view;

import hotel.controller.FinanceiroController;
import hotel.controller.PermissaoController;
import hotel.model.Financeiro;
import hotel.model.Parametro;
import hotel.support.Report;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class frmFinanceiro extends javax.swing.JInternalFrame {

    Financeiro financeiro;
    FinanceiroController financeiroController;

    boolean isRegistrar = false;
    boolean isNota = false;

    public frmFinanceiro() {
        initComponents();
        financeiro = new Financeiro();
        financeiroController = new FinanceiroController();

        buildChartDayMonthlyGross();

        financeiroController.popularTabela(tblLista, 0, "");
        tblLista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });
        setMaxDays();
        loadPermission();
        setAba(0);
    }

    private void buildChartDayMonthlyGross() {
        XYSeries series = new XYSeries("Faturamento");
        Calendar cal = Calendar.getInstance();
        Map<Integer, Double> map = new HashMap();
        for (Financeiro financeiro : financeiroController.getReadAllByMonthYear(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(financeiro.getDtaPgto());
            int day = cal2.get(Calendar.DAY_OF_MONTH);
            if (map.containsKey(day)) {
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

    private void setMaxDays() {
        YearMonth yearMonthObject = YearMonth.of(tfdAno.getYear(), tfdMes.getMonth() + 1);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        tfdDia.setModel(new SpinnerNumberModel(1, 1, daysInMonth, 1));
    }

    private void loadPermission() {
        isRegistrar = PermissaoController.hasPermission("frmFinanceiro", "btnRegistrarPagamento");
        isNota = PermissaoController.hasPermission("frmFinanceiro", "btnNota");
    }

    private void setAba(int pIndex) {
        tbpLocacao.setSelectedIndex(pIndex);

        habilitar();
    }

    private void limparCampos() {
        financeiro = new Financeiro();
    }

    private void habilitar() {
        if (tbpLocacao.getSelectedIndex() == 0) {
            btnRegistrarPagamento.setEnabled(false);
            btnNota.setEnabled(false);
        } else {
            String lSituacao = "";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 5));
            }

            btnRegistrarPagamento.setEnabled(isRegistrar && lSituacao.equals("null"));
            btnNota.setEnabled(isRegistrar && tblLista.getSelectedRow() != -1);
        }
    }

    private long calculaDiasEntreDatas(String dataVencimento, String dataPagamento) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataVenci = LocalDate.parse(dataVencimento, dtf);
        LocalDate dataPaga = LocalDate.parse(dataPagamento, dtf);
        return ChronoUnit.DAYS.between(dataVenci, dataPaga);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnRegistrarPagamento = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnNota = new javax.swing.JButton();
        tbpLocacao = new javax.swing.JTabbedPane();
        pnlResumo = new javax.swing.JPanel();
        pnlAcompanhante = new javax.swing.JPanel();
        pnlFields = new javax.swing.JPanel();
        pnlListagem = new javax.swing.JPanel();
        pnlDetalhe = new javax.swing.JPanel();
        tfdPesquisa = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        pnlOpcao = new javax.swing.JPanel();
        rbNome = new javax.swing.JRadioButton();
        rbCodigo = new javax.swing.JRadioButton();
        btnPesquisa = new javax.swing.JButton();
        pnlOpcao1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdMes = new com.toedter.calendar.JMonthChooser();
        jLabel2 = new javax.swing.JLabel();
        tfdAno = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        tfdDia = new javax.swing.JSpinner();
        scpLista = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();

        btnRegistrarPagamento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrarPagamento.setForeground(new java.awt.Color(12, 91, 160));
        btnRegistrarPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/payment.png"))); // NOI18N
        btnRegistrarPagamento.setText("Registrar Pagamento");
        btnRegistrarPagamento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarPagamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPagamentoActionPerformed(evt);
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

        btnNota.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNota.setForeground(new java.awt.Color(12, 91, 160));
        btnNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/atm-machine.png"))); // NOI18N
        btnNota.setText("Nota");
        btnNota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegistrarPagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNota, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarPagamento)
            .addComponent(btnFechar)
            .addComponent(btnNota)
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

        javax.swing.GroupLayout pnlAcompanhanteLayout = new javax.swing.GroupLayout(pnlAcompanhante);
        pnlAcompanhante.setLayout(pnlAcompanhanteLayout);
        pnlAcompanhanteLayout.setHorizontalGroup(
            pnlAcompanhanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        pnlAcompanhanteLayout.setVerticalGroup(
            pnlAcompanhanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
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

        pnlOpcao1.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcao1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pesquisa Detalhada Datas ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Mês:");

        tfdMes.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdMes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfdMesPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Ano:");

        tfdAno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfdAnoPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Dia:");

        tfdDia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlOpcao1Layout = new javax.swing.GroupLayout(pnlOpcao1);
        pnlOpcao1.setLayout(pnlOpcao1Layout);
        pnlOpcao1Layout.setHorizontalGroup(
            pnlOpcao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcao1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcao1Layout.setVerticalGroup(
            pnlOpcao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tfdMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tfdAno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tfdDia)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOpcao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDetalheLayout.createSequentialGroup()
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDetalheLayout.setVerticalGroup(
            pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlOpcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)))
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

    private void btnRegistrarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPagamentoActionPerformed
        financeiro = financeiroController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        Calendar now = Calendar.getInstance();
        if (now.getTime().after(financeiro.getDtaVencimento())) {
            long days = calculaDiasEntreDatas(financeiro.getDtaVencimento().toString(), now.getTime().toString());
            double valorComJuros = (Parametro.PER_JUROS * days) * financeiro.getVlrFinanceiro().doubleValue();
            financeiro.setVlrPago(BigDecimal.valueOf(valorComJuros));
        } else {
            financeiro.setVlrPago(financeiro.getVlrFinanceiro());
        }
        financeiro.setDtaPgto(now.getTime());
        financeiroController.popularTabela(tblLista, 0, "");
    }//GEN-LAST:event_btnRegistrarPagamentoActionPerformed

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

    private void tfdMesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfdMesPropertyChange
        setMaxDays();
    }//GEN-LAST:event_tfdMesPropertyChange

    private void tfdAnoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfdAnoPropertyChange
        setMaxDays();
    }//GEN-LAST:event_tfdAnoPropertyChange

    private void btnNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotaActionPerformed
        financeiro = financeiroController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        new Report().openFile(Parametro.DIR_FINANCEIRO, "L" + financeiro.getLocacao().getCodLocacao() + ".pdf");
    }//GEN-LAST:event_btnNotaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNota;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnRegistrarPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JPanel pnlAcompanhante;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JPanel pnlOpcao1;
    private javax.swing.JPanel pnlResumo;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JScrollPane scpLista;
    private javax.swing.JTable tblLista;
    private javax.swing.JTabbedPane tbpLocacao;
    private com.toedter.calendar.JYearChooser tfdAno;
    private javax.swing.JSpinner tfdDia;
    private com.toedter.calendar.JMonthChooser tfdMes;
    private javax.swing.JTextField tfdPesquisa;
    // End of variables declaration//GEN-END:variables
}
