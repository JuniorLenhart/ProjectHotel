package hotel.view;

import hotel.controller.AuditoriaController;
import hotel.controller.ParametroController;
import hotel.controller.PermissaoController;
import hotel.model.Auditoria;
import hotel.model.Parametro;
import hotel.repository.ParametroRepository;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;

public class frmAuditoria extends javax.swing.JInternalFrame {

    Auditoria auditoria;
    AuditoriaController auditoriaController;
    ParametroController parametroController;

    boolean isAtivarAuditoria = false;
    boolean isDetalhe = false;
    boolean isBackup = false;

    int page = 0;

    public frmAuditoria() {
        initComponents();
        auditoria = new Auditoria();
        auditoriaController = new AuditoriaController();
        parametroController = new ParametroController();

        checaAuditoriaAtiva();

        criaEventoLista();

        auditoriaController.popularTabela(tblLista, 0, "", page, tfdRegistrosTotais);
        habilitaSetas(page);

        loadPermission();
        btnAtivarAuditoria.setEnabled(isAtivarAuditoria);
        btnBackup.setEnabled(isBackup);
    }

    private void loadPermission() {
        isAtivarAuditoria = PermissaoController.hasPermission("frmAuditoria", "btnAtivarAuditoria");
        isDetalhe = PermissaoController.hasPermission("frmAuditoria", "btnDetalhe");
        isBackup = PermissaoController.hasPermission("frmAuditoria", "btnBackup");
    }

    private void checaAuditoriaAtiva() {
        if (Parametro.AUDITORIA_ATIVA) {
            btnAtivarAuditoria.setSelected(Parametro.AUDITORIA_ATIVA);
            btnAtivarAuditoria.setText("Desativar");
            btnAtivarAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/lampoff.png")));
        } else {
            btnAtivarAuditoria.setSelected(Parametro.AUDITORIA_ATIVA);
            btnAtivarAuditoria.setText("Ativar");
            btnAtivarAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/lampon.png")));
        }
    }

    private void criaEventoLista() {
        tblLista.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (tblLista.getSelectedRow() != -1) {
                btnDetalhe.setEnabled(isDetalhe);
            } else {
                btnDetalhe.setEnabled(false);
            }
        });
    }

    private int setTotalPages() {
        if (!tfdRegistrosTotais.getText().equals("")) {
            return (int) Math.ceil(Double.parseDouble(tfdRegistrosTotais.getText()) / 25);
        } else {
            return 0;
        }
    }

    private void habilitaSetas(int currentPage) {
        alteraTextoPagina();
        btnUltimaPagina.setEnabled(true);
        btnProximaPagina.setEnabled(true);
        btnPaginaAnterior.setEnabled(true);
        btnPrimeiraPagina.setEnabled(true);
        if (currentPage == 0) {
            btnPaginaAnterior.setEnabled(false);
            btnPrimeiraPagina.setEnabled(false);
        }
        if (setTotalPages() != 0) {
            if (currentPage == setTotalPages() - 1) {
                btnUltimaPagina.setEnabled(false);
                btnProximaPagina.setEnabled(false);
            }
        } else {
            btnUltimaPagina.setEnabled(false);
            btnProximaPagina.setEnabled(false);
        }
    }

    private void alteraTextoPagina() {
        if (setTotalPages() != 0) {
            lblPagina.setText("Página " + (page + 1) + " de " + setTotalPages());
        } else {
            lblPagina.setText("Página " + (page + 1) + " de " + setTotalPages() + 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGroup = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        btnDetalhe = new javax.swing.JButton();
        btnAtivarAuditoria = new javax.swing.JToggleButton();
        btnBackup = new javax.swing.JButton();
        pnlListagem = new javax.swing.JPanel();
        pnlDetalhe = new javax.swing.JPanel();
        tfdPesquisa = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        pnlOpcao = new javax.swing.JPanel();
        rbNome = new javax.swing.JRadioButton();
        rbCodigo = new javax.swing.JRadioButton();
        rbUsuarioLogin = new javax.swing.JRadioButton();
        btnPesquisa = new javax.swing.JButton();
        pnlOpcaoTipo = new javax.swing.JPanel();
        cbxInsert = new javax.swing.JCheckBox();
        cbxUpdate = new javax.swing.JCheckBox();
        cbxDelete = new javax.swing.JCheckBox();
        pnlLista = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        pnlSetas = new javax.swing.JPanel();
        btnProximaPagina = new javax.swing.JButton();
        btnPaginaAnterior = new javax.swing.JButton();
        btnUltimaPagina = new javax.swing.JButton();
        btnPrimeiraPagina = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        lblRegistrosTotais = new javax.swing.JLabel();
        tfdRegistrosTotais = new javax.swing.JTextField();

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

        btnDetalhe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDetalhe.setForeground(new java.awt.Color(12, 91, 160));
        btnDetalhe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/detalhe.png"))); // NOI18N
        btnDetalhe.setText("Detalhe");
        btnDetalhe.setEnabled(false);
        btnDetalhe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetalhe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDetalhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalheActionPerformed(evt);
            }
        });

        btnAtivarAuditoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAtivarAuditoria.setForeground(new java.awt.Color(12, 91, 160));
        btnAtivarAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/lampon.png"))); // NOI18N
        btnAtivarAuditoria.setText("Ativar");
        btnAtivarAuditoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAtivarAuditoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAtivarAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtivarAuditoriaActionPerformed(evt);
            }
        });

        btnBackup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBackup.setForeground(new java.awt.Color(12, 91, 160));
        btnBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/backup.png"))); // NOI18N
        btnBackup.setText("Backup");
        btnBackup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBackup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtivarAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetalhe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAtivarAuditoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDetalhe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlListagem.setBackground(new java.awt.Color(255, 255, 255));
        pnlListagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Auditoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

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
        btgGroup.add(rbNome);
        rbNome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbNome.setSelected(true);
        rbNome.setText("Por nome");
        rbNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNomeActionPerformed(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(255, 255, 255));
        btgGroup.add(rbCodigo);
        rbCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbCodigo.setText("Por código");
        rbCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodigoActionPerformed(evt);
            }
        });

        rbUsuarioLogin.setBackground(new java.awt.Color(255, 255, 255));
        btgGroup.add(rbUsuarioLogin);
        rbUsuarioLogin.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbUsuarioLogin.setText("Por usuário");
        rbUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbUsuarioLoginActionPerformed(evt);
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
                .addComponent(rbCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbUsuarioLogin))
        );
        pnlOpcaoLayout.setVerticalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNome)
                    .addComponent(rbCodigo)
                    .addComponent(rbUsuarioLogin))
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

        pnlOpcaoTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcaoTipo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pesquisa por Tipo ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cbxInsert.setBackground(new java.awt.Color(255, 255, 255));
        cbxInsert.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbxInsert.setText("INSERT");
        cbxInsert.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxInsertItemStateChanged(evt);
            }
        });

        cbxUpdate.setBackground(new java.awt.Color(255, 255, 255));
        cbxUpdate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbxUpdate.setText("UPDATE");
        cbxUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxInsertItemStateChanged(evt);
            }
        });

        cbxDelete.setBackground(new java.awt.Color(255, 255, 255));
        cbxDelete.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbxDelete.setText("DELETE");
        cbxDelete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxInsertItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcaoTipoLayout = new javax.swing.GroupLayout(pnlOpcaoTipo);
        pnlOpcaoTipo.setLayout(pnlOpcaoTipoLayout);
        pnlOpcaoTipoLayout.setHorizontalGroup(
            pnlOpcaoTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcaoTipoLayout.setVerticalGroup(
            pnlOpcaoTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcaoTipoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlOpcaoTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxInsert)
                    .addComponent(cbxUpdate)
                    .addComponent(cbxDelete)))
        );

        javax.swing.GroupLayout pnlDetalheLayout = new javax.swing.GroupLayout(pnlDetalhe);
        pnlDetalhe.setLayout(pnlDetalheLayout);
        pnlDetalheLayout.setHorizontalGroup(
            pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalheLayout.createSequentialGroup()
                        .addComponent(pnlOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlOpcaoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetalheLayout.createSequentialGroup()
                        .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPesquisa)
                            .addComponent(tfdPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        pnlDetalheLayout.setVerticalGroup(
            pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlOpcaoTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        pnlLista.setViewportView(tblLista);

        pnlSetas.setBackground(new java.awt.Color(255, 255, 255));

        btnProximaPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/next_page.png"))); // NOI18N
        btnProximaPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximaPaginaActionPerformed(evt);
            }
        });

        btnPaginaAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/previous_page.png"))); // NOI18N
        btnPaginaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaginaAnteriorActionPerformed(evt);
            }
        });

        btnUltimaPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/last_page.png"))); // NOI18N
        btnUltimaPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimaPaginaActionPerformed(evt);
            }
        });

        btnPrimeiraPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/first_page.png"))); // NOI18N
        btnPrimeiraPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeiraPaginaActionPerformed(evt);
            }
        });

        lblPagina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPagina.setText("Página 1 de 5");

        lblRegistrosTotais.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRegistrosTotais.setText("Registros totais:");

        tfdRegistrosTotais.setEditable(false);
        tfdRegistrosTotais.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfdRegistrosTotais.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout pnlSetasLayout = new javax.swing.GroupLayout(pnlSetas);
        pnlSetas.setLayout(pnlSetasLayout);
        pnlSetasLayout.setHorizontalGroup(
            pnlSetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSetasLayout.createSequentialGroup()
                .addComponent(lblRegistrosTotais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRegistrosTotais, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrimeiraPagina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPaginaAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProximaPagina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUltimaPagina)
                .addGap(178, 178, 178)
                .addComponent(lblPagina))
        );
        pnlSetasLayout.setVerticalGroup(
            pnlSetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSetasLayout.createSequentialGroup()
                .addGroup(pnlSetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProximaPagina)
                    .addComponent(btnPaginaAnterior)
                    .addComponent(btnUltimaPagina)
                    .addComponent(btnPrimeiraPagina)
                    .addComponent(lblPagina)
                    .addGroup(pnlSetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRegistrosTotais)
                        .addComponent(tfdRegistrosTotais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlListagemLayout = new javax.swing.GroupLayout(pnlListagem);
        pnlListagem.setLayout(pnlListagemLayout);
        pnlListagemLayout.setHorizontalGroup(
            pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListagemLayout.createSequentialGroup()
                .addGroup(pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlListagemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                            .addComponent(pnlSetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlListagemLayout.setVerticalGroup(
            pnlListagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListagemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlDetalhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlListagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlListagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        lblPesquisa.setText("Descrição:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbNomeActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        lblPesquisa.setText("Código:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbCodigoActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        if (tfdPesquisa.getText().trim().isEmpty()) {
            auditoriaController.popularTabela(tblLista, 0, "", page, tfdRegistrosTotais);
        } else if (rbNome.isSelected()) {
            auditoriaController.popularTabela(tblLista, 1, tfdPesquisa.getText(), page, tfdRegistrosTotais);
        } else if (rbCodigo.isSelected()) {
            auditoriaController.popularTabela(tblLista, 4, tfdPesquisa.getText(), page, tfdRegistrosTotais);
        } else if (rbUsuarioLogin.isSelected()) {
            auditoriaController.popularTabela(tblLista, 3, tfdPesquisa.getText(), page, tfdRegistrosTotais);
        }
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnAtivarAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtivarAuditoriaActionPerformed
        Parametro parametro = ParametroRepository.read();
        if (btnAtivarAuditoria.isSelected()) {
            parametro.setAuditoriaAtiva(true);
            btnAtivarAuditoria.setText("Desativar");
            btnAtivarAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/lampoff.png")));
            JOptionPane.showMessageDialog(null, "Auditoria agora está ativada!");
        } else if (!btnAtivarAuditoria.isSelected()) {
            parametro.setAuditoriaAtiva(false);
            btnAtivarAuditoria.setText("Ativar");
            btnAtivarAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/lampon.png")));
            JOptionPane.showMessageDialog(null, "Auditoria agora está desativada!");
        }
        parametroController.save(parametro);
    }//GEN-LAST:event_btnAtivarAuditoriaActionPerformed

    private void cbxInsertItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxInsertItemStateChanged
        String pParam = "";
        if ((cbxInsert.isSelected()) && (cbxDelete.isSelected()) && (cbxUpdate.isSelected())) {
            pParam = "'INSERT' OR tipAuditoria = 'DELETE' OR tipAuditoria = 'UPDATE'";
        } else if ((cbxInsert.isSelected()) && ((cbxDelete.isSelected()))) {
            pParam = "'INSERT' OR tipAuditoria = 'DELETE'";
        } else if (((cbxInsert.isSelected())) && ((cbxUpdate.isSelected()))) {
            pParam = "'INSERT' OR tipAuditoria = 'UPDATE'";
        } else if (((cbxDelete.isSelected())) && ((cbxUpdate.isSelected()))) {
            pParam = "'DELETE' OR tipAuditoria = 'UPDATE'";
        } else if (cbxInsert.isSelected()) {
            pParam = "'INSERT'";
        } else if (cbxDelete.isSelected()) {
            pParam = "'DELETE'";
        } else if (cbxUpdate.isSelected()) {
            pParam = "'UPDATE'";
        } else {
            pParam = "'INSERT' OR tipAuditoria = 'DELETE' OR tipAuditoria = 'UPDATE'";
        }
        auditoriaController.popularTabela(tblLista, 2, pParam, page, tfdRegistrosTotais);
    }//GEN-LAST:event_cbxInsertItemStateChanged

    private void rbUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbUsuarioLoginActionPerformed
        lblPesquisa.setText("Usuário:");
        tfdPesquisa.setText("");
    }//GEN-LAST:event_rbUsuarioLoginActionPerformed

    private void btnDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalheActionPerformed
        auditoria = auditoriaController.getReadId(Integer.parseInt(tblLista.getModel().getValueAt(tblLista.getSelectedRow(), 0).toString()));
        new frmAuditoriaDetalhe((JFrame) SwingUtilities.getWindowAncestor(this), true, auditoria).setVisible(true);
    }//GEN-LAST:event_btnDetalheActionPerformed

    private void btnProximaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximaPaginaActionPerformed
        if (page + 1 <= setTotalPages()) {
            page++;
            auditoriaController.popularTabela(tblLista, 0, "", page, tfdRegistrosTotais);
        }
        habilitaSetas(page);
    }//GEN-LAST:event_btnProximaPaginaActionPerformed

    private void btnPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaAnteriorActionPerformed
        if (page - 1 != -1) {
            page--;
            auditoriaController.popularTabela(tblLista, 0, "", page, tfdRegistrosTotais);
        }
        habilitaSetas(page);
    }//GEN-LAST:event_btnPaginaAnteriorActionPerformed

    private void btnPrimeiraPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeiraPaginaActionPerformed
        page = 0;
        auditoriaController.popularTabela(tblLista, 0, "", page, tfdRegistrosTotais);
        habilitaSetas(page);
    }//GEN-LAST:event_btnPrimeiraPaginaActionPerformed

    private void btnUltimaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimaPaginaActionPerformed
        if (setTotalPages() != 0) {
            page = setTotalPages() - 1;
            auditoriaController.popularTabela(tblLista, 0, "", page, tfdRegistrosTotais);
        }
        habilitaSetas(page);
    }//GEN-LAST:event_btnUltimaPaginaActionPerformed

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        frmAuditoriaBackup auditoriaBackup = new frmAuditoriaBackup(null, true);
        auditoriaBackup.setVisible(true);

        String[] lStr = auditoriaBackup.getResult();

        if (lStr[0].equals("Yes")) {
            auditoriaController.backup(lStr[1], lStr[2]);
            btnPesquisaActionPerformed(evt);
            JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!");
        }
    }//GEN-LAST:event_btnBackupActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGroup;
    private javax.swing.JToggleButton btnAtivarAuditoria;
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnDetalhe;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPaginaAnterior;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPrimeiraPagina;
    private javax.swing.JButton btnProximaPagina;
    private javax.swing.JButton btnUltimaPagina;
    private javax.swing.JCheckBox cbxDelete;
    private javax.swing.JCheckBox cbxInsert;
    private javax.swing.JCheckBox cbxUpdate;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblRegistrosTotais;
    private javax.swing.JPanel pnlDetalhe;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane pnlLista;
    private javax.swing.JPanel pnlListagem;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JPanel pnlOpcaoTipo;
    private javax.swing.JPanel pnlSetas;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JRadioButton rbUsuarioLogin;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextField tfdPesquisa;
    private javax.swing.JTextField tfdRegistrosTotais;
    // End of variables declaration//GEN-END:variables
}
