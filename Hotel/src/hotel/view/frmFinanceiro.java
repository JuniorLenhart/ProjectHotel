package hotel.view;

import hotel.controller.FinanceiroController;
import hotel.controller.PermissaoController;
import hotel.model.Financeiro;
import hotel.model.Parametro;
import hotel.support.Formatacao;
import hotel.support.Report;
import hotel.support.Unit;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class frmFinanceiro extends javax.swing.JInternalFrame {

    Financeiro financeiro;
    FinanceiroController financeiroController;
    String[] monthName = {"Janeiro", "Fevereiro",
        "Março", "Abril", "Maio", "Junho", "Julho",
        "Agosto", "Setembro", "Outubro", "Novembro",
        "Dezembro"};
    Calendar now;

    boolean isRegistrar = false;
    boolean isNota = false;

    public frmFinanceiro() {
        initComponents();
        financeiro = new Financeiro();
        financeiroController = new FinanceiroController();
        now = Calendar.getInstance();

        buildChartDayMonthlyGross(now, 0);
        buildChartDayMonthlyGross(now, 1);
        buildChartLast11MonthsGross();

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
        habilitaSetas(now);
    }

    private void buildChartDayMonthlyGross(Calendar pCal, int month) {
        XYSeries series = new XYSeries("Faturamento");
        Map<Integer, Double> map = new HashMap();
        Calendar calendarLastYear = (Calendar) pCal.clone();
        if (month != 0) {
            for (int i = 0; i <= 11; i++) {
                calendarLastYear.add(Calendar.MONTH, -1);
            }
            clearChartDayMonthlyGross(calendarLastYear, map);
            for (Financeiro financeiro : financeiroController.getReadAllByMonthYear(calendarLastYear.get(Calendar.MONTH), calendarLastYear.get(Calendar.YEAR))) {
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
                //System.out.println(entry.getKey() + " " + entry.getValue());
                Integer key = entry.getKey();
                Double value = entry.getValue();
                series.add(key.intValue(), value.doubleValue());
            }
            XYSeriesCollection data = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Faturamento " + monthName[calendarLastYear.get(Calendar.MONTH)] + "/" + calendarLastYear.get(Calendar.YEAR) + " por dia",
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
            pnlBalancoMesVelho.setLayout(new java.awt.BorderLayout());
            pnlBalancoMesVelho.add(chartPanel, BorderLayout.CENTER);
            pnlBalancoMesVelho.validate();
        } else {
            setJLabelDate(pCal);
            clearChartDayMonthlyGross(pCal, map);
            for (Financeiro financeiro : financeiroController.getReadAllByMonthYear(pCal.get(Calendar.MONTH), pCal.get(Calendar.YEAR))) {
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
                //System.out.println(entry.getKey() + " " + entry.getValue());
                Integer key = entry.getKey();
                Double value = entry.getValue();
                series.add(key.intValue(), value.doubleValue());
            }
            XYSeriesCollection data = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Faturamento " + monthName[pCal.get(Calendar.MONTH)] + "/" + pCal.get(Calendar.YEAR) + " por dia",
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
            pnlBalancoMesAtual.setLayout(new java.awt.BorderLayout());
            pnlBalancoMesAtual.add(chartPanel, BorderLayout.CENTER);
            pnlBalancoMesAtual.validate();
        }

    }

    private void buildChartLast11MonthsGross() {
        XYSeries series = new XYSeries("Faturamento 11 meses");
        Calendar cal = Calendar.getInstance();
        Map<Integer, Double> map = new HashMap();

        String[] grade = clearChartLast11MonthsGross(cal, map);

        cal = Calendar.getInstance();
        for (int i = 1; i <= 11; i++) {
            cal.add(Calendar.MONTH, -1);
            for (Financeiro financeiro : financeiroController.getReadAllByMonthYear(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))) {
                if (map.containsKey(i - 1)) {
                    map.put(i - 1, map.get(i - 1) + financeiro.getVlrPago().doubleValue());
                }
                System.out.println(i - 1 + " - " + financeiro.getVlrPago().doubleValue());
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {

            System.out.println(entry.getKey() + " " + entry.getValue());
            Integer key = entry.getKey();
            Double value = entry.getValue();
            series.add(key.intValue(), value.doubleValue());
            count++;
        }
        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Faturamento 11 últimos meses",
                "Mês/Ano",
                "Reais",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        SymbolAxis rangeAxis = new SymbolAxis("", grade);
        rangeAxis.setAutoRange(false);
        rangeAxis.setTickUnit(new NumberTickUnit(1));
        rangeAxis.setRange(0, 11);
        plot.setDomainAxis(rangeAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(950, 230));
        chartPanel.setVisible(true);
        chartPanel.zoomInRange(1, 2);
        chartPanel.restoreAutoBounds();
        pnlBalancoAnual.setLayout(new java.awt.BorderLayout());
        pnlBalancoAnual.add(chartPanel, BorderLayout.CENTER);
        pnlBalancoAnual.validate();
    }

    private void clearChartDayMonthlyGross(Calendar cal, Map map) {
        YearMonth yearMonthObject = YearMonth.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        for (int i = 1; i <= daysInMonth; i++) {
            map.put(i, Double.parseDouble("0"));
        }
    }

    private String[] clearChartLast11MonthsGross(Calendar cal, Map map) {
        String[] grade = new String[11];
        for (int i = 1; i <= 11; i++) {
            cal.add(Calendar.MONTH, -1);
            map.put(i - 1, Double.parseDouble("0"));
            grade[i - 1] = new SimpleDateFormat("MMM").format(cal.getTime()) + "/" + cal.get(Calendar.YEAR);
        }
        return grade;
    }

    private void setMonth(Calendar pCal, int pOption) {
        if (pOption == 0) { // Seta para diminuir mes
            pCal.add(Calendar.MONTH, -1);
        } else {
            pCal.add(Calendar.MONTH, 1);
            if ((pCal.compareTo(Calendar.getInstance())) > 0) {
                pCal.add(Calendar.MONTH, -1);
            }
        }
        setJLabelDate(pCal);
        habilitaSetas(pCal);
        buildChartDayMonthlyGross(pCal, 0);
        buildChartDayMonthlyGross(pCal, 1);
    }
    
    private void habilitaSetas(Calendar pCal) {
        btnMesAnterior.setEnabled(true);
        btnMesProximo.setEnabled(true);
        if(pCal.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) && pCal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            btnMesProximo.setEnabled(false);
        }
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

    private void setJLabelDate(Calendar cal) {
        lblMes.setText(monthName[cal.get(Calendar.MONTH)] + "/" + cal.get(Calendar.YEAR));
    }

    private void habilitar() {
        if (tbpLocacao.getSelectedIndex() == 0) {
            btnRegistrarPagamento.setEnabled(false);
            btnNota.setEnabled(false);
        } else {
            String lSituacao = "1";
            if (tblLista.getSelectedRow() != -1) {
                lSituacao = String.valueOf(tblLista.getValueAt(tblLista.getSelectedRow(), 5));
            }

            btnRegistrarPagamento.setEnabled(isRegistrar && lSituacao.equals(""));
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
        pnlBalancoAnual = new javax.swing.JPanel();
        pnlBalancoMesAtual = new javax.swing.JPanel();
        pnlBalancoMesVelho = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnMesProximo = new javax.swing.JButton();
        btnMesAnterior = new javax.swing.JButton();
        lblMes = new javax.swing.JLabel();
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

        pnlBalancoAnual.setBackground(new java.awt.Color(255, 255, 255));
        pnlBalancoAnual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balanço 12 últimos meses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        javax.swing.GroupLayout pnlBalancoAnualLayout = new javax.swing.GroupLayout(pnlBalancoAnual);
        pnlBalancoAnual.setLayout(pnlBalancoAnualLayout);
        pnlBalancoAnualLayout.setHorizontalGroup(
            pnlBalancoAnualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1028, Short.MAX_VALUE)
        );
        pnlBalancoAnualLayout.setVerticalGroup(
            pnlBalancoAnualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        pnlBalancoMesAtual.setBackground(new java.awt.Color(255, 255, 255));
        pnlBalancoMesAtual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balanço Mensal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        javax.swing.GroupLayout pnlBalancoMesAtualLayout = new javax.swing.GroupLayout(pnlBalancoMesAtual);
        pnlBalancoMesAtual.setLayout(pnlBalancoMesAtualLayout);
        pnlBalancoMesAtualLayout.setHorizontalGroup(
            pnlBalancoMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
        pnlBalancoMesAtualLayout.setVerticalGroup(
            pnlBalancoMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        pnlBalancoMesVelho.setBackground(new java.awt.Color(255, 255, 255));
        pnlBalancoMesVelho.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balanço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        javax.swing.GroupLayout pnlBalancoMesVelhoLayout = new javax.swing.GroupLayout(pnlBalancoMesVelho);
        pnlBalancoMesVelho.setLayout(pnlBalancoMesVelhoLayout);
        pnlBalancoMesVelhoLayout.setHorizontalGroup(
            pnlBalancoMesVelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBalancoMesVelhoLayout.setVerticalGroup(
            pnlBalancoMesVelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnMesProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/next_page_bigger.png"))); // NOI18N
        btnMesProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesProximoActionPerformed(evt);
            }
        });

        btnMesAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/previous_page_bigger.png"))); // NOI18N
        btnMesAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesAnteriorActionPerformed(evt);
            }
        });

        lblMes.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMes.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMesAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMesProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMesAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMesProximo, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlResumoLayout = new javax.swing.GroupLayout(pnlResumo);
        pnlResumo.setLayout(pnlResumoLayout);
        pnlResumoLayout.setHorizontalGroup(
            pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBalancoAnual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlResumoLayout.createSequentialGroup()
                        .addGroup(pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlResumoLayout.createSequentialGroup()
                                .addComponent(pnlBalancoMesAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlBalancoMesVelho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pnlResumoLayout.setVerticalGroup(
            pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBalancoMesAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBalancoMesVelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBalancoAnual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                        .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlListagemLayout.setVerticalGroup(
            pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetalhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpLista, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
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
        Timestamp dtaPagamento = Timestamp.valueOf(Formatacao.ajustaDataAMDHMS(Unit.getDataHoraAtual()));
        financeiro.setDtaPgto(dtaPagamento);
        financeiroController.save(financeiro);
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

    private void btnMesProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesProximoActionPerformed
        setMonth(now, 1);
    }//GEN-LAST:event_btnMesProximoActionPerformed

    private void btnMesAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesAnteriorActionPerformed
        setMonth(now, 0);
    }//GEN-LAST:event_btnMesAnteriorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnMesAnterior;
    private javax.swing.JButton btnMesProximo;
    private javax.swing.JButton btnNota;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnRegistrarPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JPanel pnlBalancoAnual;
    private javax.swing.JPanel pnlBalancoMesAtual;
    private javax.swing.JPanel pnlBalancoMesVelho;
    private javax.swing.JPanel pnlDetalhe;
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
