package hotel.view;

import hotel.controller.NovidadeController;
import hotel.model.Novidade;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class frmNovidade extends javax.swing.JInternalFrame {

    Novidade novidade;
    NovidadeController novidadeController;

    public frmNovidade() {
        initComponents();
        novidade = new Novidade();
        novidadeController = new NovidadeController();

        tblNovidade.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilitar();
            }
        });

        novidadeController.popularTabela(tblNovidade, 0, "");
    }

    private void habilitar() {
        btnDetalhe.setEnabled(tblNovidade.getSelectedRow() != -1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        btnDetalhe = new javax.swing.JButton();
        tbpListagem = new javax.swing.JTabbedPane();
        pnlNovidade = new javax.swing.JPanel();
        scpNovidade = new javax.swing.JScrollPane();
        tblNovidade = new javax.swing.JTable();

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

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDetalhe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFechar)
            .addComponent(btnDetalhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tbpListagem.setBackground(new java.awt.Color(255, 255, 255));
        tbpListagem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pnlNovidade.setBackground(new java.awt.Color(255, 255, 255));
        pnlNovidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Novidades ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        tblNovidade.setModel(new javax.swing.table.DefaultTableModel(
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
        scpNovidade.setViewportView(tblNovidade);

        javax.swing.GroupLayout pnlNovidadeLayout = new javax.swing.GroupLayout(pnlNovidade);
        pnlNovidade.setLayout(pnlNovidadeLayout);
        pnlNovidadeLayout.setHorizontalGroup(
            pnlNovidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNovidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpNovidade)
                .addContainerGap())
        );
        pnlNovidadeLayout.setVerticalGroup(
            pnlNovidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNovidadeLayout.createSequentialGroup()
                .addComponent(scpNovidade, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpListagem.addTab("Listagem", pnlNovidade);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbpListagem, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpListagem)
                .addContainerGap())
        );

        tbpListagem.getAccessibleContext().setAccessibleName("Listagem");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalheActionPerformed
        novidade = novidadeController.getReadId(Integer.parseInt(tblNovidade.getModel().getValueAt(tblNovidade.getSelectedRow(), 0).toString()));
        new frmNovidadeDetalhe((JFrame) SwingUtilities.getWindowAncestor(this), true, novidade, false);
    }//GEN-LAST:event_btnDetalheActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalhe;
    private javax.swing.JButton btnFechar;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlNovidade;
    private javax.swing.JScrollPane scpNovidade;
    private javax.swing.JTable tblNovidade;
    private javax.swing.JTabbedPane tbpListagem;
    // End of variables declaration//GEN-END:variables
}
