package hotel.view;

public class frmAuditoriaBackup extends javax.swing.JDialog {

    private String[] FResult;

    public frmAuditoriaBackup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        FResult = new String[3];
        FResult[0] = "No";

        habilitar();
    }

    public String[] getResult() {
        return FResult;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroup = new javax.swing.ButtonGroup();
        pnlPanel = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblDataInicial = new javax.swing.JLabel();
        tfdDataInicial = new hotel.support.JDateChooserComboLayout();
        lblDataFinal = new javax.swing.JLabel();
        tfdDataFinal = new hotel.support.JDateChooserComboLayout();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Backup Auditoria");

        pnlPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnConfirm.setText("Confirmar");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lblDataInicial.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataInicial.setForeground(new java.awt.Color(102, 102, 102));
        lblDataInicial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataInicial.setText("<html>Data Inicial<font color='red'><b>*</b></font>:</html>");
        lblDataInicial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdDataInicial.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataInicial.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tfdDataInicial.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                tfdDataInicialOnCommit(evt);
            }
        });

        lblDataFinal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataFinal.setForeground(new java.awt.Color(102, 102, 102));
        lblDataFinal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDataFinal.setText("<html>Data Final<font color='red'><b>*</b></font>:</html>");
        lblDataFinal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdDataFinal.setCalendarBackground(new java.awt.Color(255, 255, 255));
        tfdDataFinal.setNavigateFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tfdDataFinal.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                tfdDataFinalOnCommit(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPanelLayout.createSequentialGroup()
                                .addComponent(lblDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfdDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPanelLayout.createSequentialGroup()
                                .addComponent(lblDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfdDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        FResult[0] = "Yes";
        FResult[1] = tfdDataInicial.getText();
        FResult[2] = tfdDataFinal.getText();
        this.dispose();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfdDataInicialOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_tfdDataInicialOnCommit
        habilitar();
    }//GEN-LAST:event_tfdDataInicialOnCommit

    private void tfdDataFinalOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_tfdDataFinalOnCommit
        habilitar();
    }//GEN-LAST:event_tfdDataFinalOnCommit

    private void habilitar() {
        btnConfirm.setEnabled(!tfdDataInicial.getText().isEmpty() && !tfdDataFinal.getText().isEmpty());
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmAuditoriaBackup dialog = new frmAuditoriaBackup(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JPanel pnlPanel;
    private javax.swing.ButtonGroup rbGroup;
    private hotel.support.JDateChooserComboLayout tfdDataFinal;
    private hotel.support.JDateChooserComboLayout tfdDataInicial;
    // End of variables declaration//GEN-END:variables
}
