package hotel.view;

import hotel.model.Auditoria;
import hotel.support.Formatacao;
import javax.swing.JFrame;

public class frmAuditoriaDetalhe extends javax.swing.JDialog {

    Auditoria auditoria;

    public frmAuditoriaDetalhe(JFrame frame, boolean modal, Auditoria pAuditoria) {
        super(frame, modal);
        initComponents();

        setLocationRelativeTo(null);
        this.auditoria = pAuditoria;

        popularTelaAuditoria();
    }

    private void popularTelaAuditoria() {
        tfdCodigo.setText(auditoria.getCodAuditoria().toString());
        tfdUsuario.setText(auditoria.getCodUsuario().getDesLogin());
        tfdTipo.setText(auditoria.getTipAuditoria());
        String data, hora, descricao;
        data = Formatacao.ajustaDataDMA(auditoria.getDtaAuditoria().toString());
        hora = Formatacao.ajustaDataHMS(auditoria.getDtaAuditoria().toString());
        tfdData.setText(data);
        tfdHora.setText(hora);
        descricao = auditoria.getDesAuditoria();
        tfaDadosNovos.append(retornaDescricaoFormatada(descricao, 1));
        if (auditoria.getTipAuditoria().equals("UPDATE")) {
            descricao = auditoria.getDesAuditoria();
            tfaDadosAntigos.append(retornaDescricaoFormatada(descricao, 2));  //append(descricao);
        }

        setEditableFields(false);
    }

    private String retornaDescricaoFormatada(String desString, int opcao) {
        if (opcao == 1) {
            desString = desString.substring(desString.indexOf("(") + 1, desString.indexOf(")"));
            desString = desString.replaceAll(",", "\n");
            return desString;
        } else {
            int secondFirst = desString.indexOf("(", desString.indexOf("(") + 1);
            int secondEnd = desString.indexOf(")", desString.indexOf(")") + 1);
            desString = desString.substring(secondFirst + 1, secondEnd);
            desString = desString.replaceAll(",", "\n");
            return desString;
        }
    }

    private void setEditableFields(boolean pEditable) {
        tfdCodigo.setEditable(pEditable);
        tfdData.setEditable(pEditable);
        tfdHora.setEditable(pEditable);
        tfdTipo.setEditable(pEditable);
        tfdUsuario.setEditable(pEditable);
        tfaDadosAntigos.setEditable(pEditable);
        tfaDadosNovos.setEditable(pEditable);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPanel = new javax.swing.JPanel();
        pnlDadosAntigos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tfaDadosAntigos = new javax.swing.JTextArea();
        pnlDadosNovos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfaDadosNovos = new javax.swing.JTextArea();
        pnlAuditoria = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        tfdUsuario = new javax.swing.JTextField();
        lblTipoNovo = new javax.swing.JLabel();
        tfdTipo = new javax.swing.JTextField();
        lblCodigo1 = new javax.swing.JLabel();
        tfdData = new javax.swing.JTextField();
        lblCodigo2 = new javax.swing.JLabel();
        tfdHora = new javax.swing.JTextField();
        pnlHeader = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnlPanel.setBackground(new java.awt.Color(255, 255, 255));

        pnlDadosAntigos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDadosAntigos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Dados antigos ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        pnlDadosAntigos.setPreferredSize(new java.awt.Dimension(290, 117));

        tfaDadosAntigos.setColumns(20);
        tfaDadosAntigos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfaDadosAntigos.setLineWrap(true);
        tfaDadosAntigos.setRows(5);
        jScrollPane3.setViewportView(tfaDadosAntigos);

        javax.swing.GroupLayout pnlDadosAntigosLayout = new javax.swing.GroupLayout(pnlDadosAntigos);
        pnlDadosAntigos.setLayout(pnlDadosAntigosLayout);
        pnlDadosAntigosLayout.setHorizontalGroup(
            pnlDadosAntigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosAntigosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDadosAntigosLayout.setVerticalGroup(
            pnlDadosAntigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosAntigosLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDadosNovos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDadosNovos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Dados novos ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        pnlDadosNovos.setPreferredSize(new java.awt.Dimension(290, 0));

        tfaDadosNovos.setColumns(20);
        tfaDadosNovos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfaDadosNovos.setLineWrap(true);
        tfaDadosNovos.setRows(5);
        jScrollPane2.setViewportView(tfaDadosNovos);

        javax.swing.GroupLayout pnlDadosNovosLayout = new javax.swing.GroupLayout(pnlDadosNovos);
        pnlDadosNovos.setLayout(pnlDadosNovosLayout);
        pnlDadosNovosLayout.setHorizontalGroup(
            pnlDadosNovosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosNovosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDadosNovosLayout.setVerticalGroup(
            pnlDadosNovosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosNovosLayout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        pnlAuditoria.setBackground(new java.awt.Color(255, 255, 255));
        pnlAuditoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Auditoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("Código:");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsuario.setText("Usuário:");
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblTipoNovo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTipoNovo.setForeground(new java.awt.Color(102, 102, 102));
        lblTipoNovo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTipoNovo.setText("Tipo:");
        lblTipoNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdTipo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdTipo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblCodigo1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo1.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo1.setText("Data:");
        lblCodigo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdData.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdData.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        lblCodigo2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo2.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo2.setText("Hora:");
        lblCodigo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdHora.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdHora.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        javax.swing.GroupLayout pnlAuditoriaLayout = new javax.swing.GroupLayout(pnlAuditoria);
        pnlAuditoria.setLayout(pnlAuditoriaLayout);
        pnlAuditoriaLayout.setHorizontalGroup(
            pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdData, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdHora, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                        .addComponent(lblTipoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAuditoriaLayout.setVerticalGroup(
            pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigo1)
                        .addComponent(tfdData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigo2)
                        .addComponent(tfdHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoNovo)
                    .addComponent(tfdTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlAuditoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addComponent(pnlDadosNovos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDadosAntigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPanelLayout.createSequentialGroup()
                .addComponent(pnlAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDadosAntigos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(pnlDadosNovos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
        );

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFechar)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigo1;
    private javax.swing.JLabel lblCodigo2;
    private javax.swing.JLabel lblTipoNovo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAuditoria;
    private javax.swing.JPanel pnlDadosAntigos;
    private javax.swing.JPanel pnlDadosNovos;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlPanel;
    private javax.swing.JTextArea tfaDadosAntigos;
    private javax.swing.JTextArea tfaDadosNovos;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdData;
    private javax.swing.JTextField tfdHora;
    private javax.swing.JTextField tfdTipo;
    private javax.swing.JTextField tfdUsuario;
    // End of variables declaration//GEN-END:variables
}
