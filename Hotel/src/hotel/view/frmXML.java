package hotel.view;

import hotel.controller.*;
import hotel.model.xml.PessoaXML;
import hotel.support.XML;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class frmXML extends javax.swing.JInternalFrame {

    public frmXML() {
        initComponents();

        Set<String> setTable = new TreeSet<>();
        setTable.add("Pessoa");
        setTable.add("Consumível");
        setTable.add("Financeiro");
        setTable.add("Forma de Pagamento");
        setTable.add("Locação");
        setTable.add("Permissão");
        setTable.add("Quarto");
        setTable.add("Reserva");
        setTable.add("Tipo de Cama");
        setTable.add("Usuário");

        cmbTabela.setModel(new DefaultComboBoxModel(setTable.toArray()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroup = new javax.swing.ButtonGroup();
        pnlPanel = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        lblArquivo = new javax.swing.JLabel();
        tfdArquivo = new javax.swing.JTextField();
        pnlOpcao = new javax.swing.JPanel();
        rbImportar = new javax.swing.JRadioButton();
        rbExportar = new javax.swing.JRadioButton();
        cmbTabela = new javax.swing.JComboBox<>();
        lblTabela = new javax.swing.JLabel();
        btnArquivo = new javax.swing.JButton();

        setTitle("Importação e Exportação de Tabelas");

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

        lblArquivo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblArquivo.setForeground(new java.awt.Color(102, 102, 102));
        lblArquivo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblArquivo.setText("<html>Arquivo<font color='red'><b>*</b></font>:</html>");
        lblArquivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfdArquivo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdArquivo.setAutoscrolls(false);
        tfdArquivo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        pnlOpcao.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Opção ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        rbImportar.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbImportar);
        rbImportar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbImportar.setText("Importar");
        rbImportar.setActionCommand("A");

        rbExportar.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rbExportar);
        rbExportar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbExportar.setSelected(true);
        rbExportar.setText("Exportar");
        rbExportar.setActionCommand("U");

        javax.swing.GroupLayout pnlOpcaoLayout = new javax.swing.GroupLayout(pnlOpcao);
        pnlOpcao.setLayout(pnlOpcaoLayout);
        pnlOpcaoLayout.setHorizontalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbImportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbExportar)
                .addGap(6, 6, 6))
        );
        pnlOpcaoLayout.setVerticalGroup(
            pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbImportar)
                .addComponent(rbExportar))
        );

        cmbTabela.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblTabela.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTabela.setForeground(new java.awt.Color(102, 102, 102));
        lblTabela.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTabela.setText("<html>Tabela<font color='red'><b>*</b></font>:</html>");
        lblTabela.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArquivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(btnConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(pnlPanelLayout.createSequentialGroup()
                        .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfdArquivo)
                            .addComponent(pnlOpcao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTabela, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86))
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnArquivo))
                .addGap(21, 21, 21)
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        try {
            if (rbExportar.isSelected()) {
                switch (cmbTabela.getSelectedItem().toString()) {
                    case "Pessoa":
                        PessoaXML pessoaXML = new PessoaXML();
                        pessoaXML.setPessoa(new PessoaController().getReadAll());
                        new XML().createXML(pessoaXML, tfdArquivo.getText());
                        break;
                }
            }
            if (rbExportar.isSelected()) {
                JOptionPane.showMessageDialog(null, "Exportação com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Importação com sucesso!");
            }
        } catch (Exception e) {
            if (rbExportar.isSelected()) {
                JOptionPane.showMessageDialog(null, "Exportação com erro!");
            } else {
                JOptionPane.showMessageDialog(null, "Importação com erro!");
            }
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        JFileChooser file = new JFileChooser();
        if (rbImportar.isSelected()) {
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        } else if (rbExportar.isSelected()) {
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tfdArquivo.setText(file.getSelectedFile().getAbsolutePath());
        } else {
            tfdArquivo.setText("");
        }
    }//GEN-LAST:event_btnArquivoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArquivo;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> cmbTabela;
    private javax.swing.JLabel lblArquivo;
    private javax.swing.JLabel lblTabela;
    private javax.swing.JPanel pnlOpcao;
    private javax.swing.JPanel pnlPanel;
    private javax.swing.JRadioButton rbExportar;
    private javax.swing.ButtonGroup rbGroup;
    private javax.swing.JRadioButton rbImportar;
    private javax.swing.JTextField tfdArquivo;
    // End of variables declaration//GEN-END:variables
}
