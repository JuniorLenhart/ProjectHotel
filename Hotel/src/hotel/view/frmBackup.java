package hotel.view;

import hotel.support.Unit;
import hotel.support.Validacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

public class frmBackup extends javax.swing.JInternalFrame {

    boolean isImportar;
    JFileChooser file;

    public frmBackup(boolean isImportar) {
        initComponents();
        this.isImportar = isImportar;
        file = new JFileChooser();
        String borderTitle = "";
        if (this.isImportar) {
            borderTitle = "Opção de restore";
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        } else {
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            borderTitle = "Opção de backup";
        }
        pnlOpcaoBackup.setBorder(javax.swing.BorderFactory.createTitledBorder(null, borderTitle, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15)));
    }

    private void backupAndRestoreDatabaseFiles() {
        if (rbnSistema.isSelected()) {
            doFileBackups();
            doDatabaseBackupRestore();
        } else {
            doDatabaseBackupRestore();
        }

    }

    private String[] setComand() {
        if (isImportar) {
            String[] cmds = {
                "chdir C:\\Program files\\postgresql\\10\\bin",
                "set PGPASSWORD=postgres",
                "psql -U postgres -c \"DROP DATABASE IF EXISTS hotel\"",
                "psql -U postgres -c \"CREATE DATABASE hotel\"",
                "psql -U postgres -d hotel -f " + tfdArquivo.getText(),};
            return cmds;
        } else {
            String[] cmds = {
                "chdir C:\\Program files\\postgresql\\10\\bin",
                "set PGPASSWORD=postgres",
                "pg_dump -U postgres -d hotel -f " + tfdArquivo.getText() + "\\" + Unit.getDataHoraAtualConcat() + ".sql"
            };
            return cmds;
        }
    }

    private void doFileBackups() {
        try {
            File srcDir = new File("C:\\Users\\george.mueller\\Documents\\NetBeansProjects\\ProjectHotel\\Hotel");
            File destDir = new File(tfdArquivo.getText());
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException ex) {
            Logger.getLogger(frmBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doDatabaseBackupRestore() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", String.join("& ", setComand()));
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(frmBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgOpcaoBackup = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        pnlOpcaoBackup = new javax.swing.JPanel();
        rbnBanco = new javax.swing.JRadioButton();
        rbnSistema = new javax.swing.JRadioButton();
        pnlSaidaBackup = new javax.swing.JPanel();
        tfdArquivo = new javax.swing.JTextField();
        btnArquivo = new javax.swing.JButton();

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

        pnlOpcaoBackup.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpcaoBackup.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opção de backup", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        rbnBanco.setBackground(new java.awt.Color(255, 255, 255));
        btgOpcaoBackup.add(rbnBanco);
        rbnBanco.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbnBanco.setSelected(true);
        rbnBanco.setText("Banco apenas");

        rbnSistema.setBackground(new java.awt.Color(255, 255, 255));
        btgOpcaoBackup.add(rbnSistema);
        rbnSistema.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rbnSistema.setText("Sistema completo");

        javax.swing.GroupLayout pnlOpcaoBackupLayout = new javax.swing.GroupLayout(pnlOpcaoBackup);
        pnlOpcaoBackup.setLayout(pnlOpcaoBackupLayout);
        pnlOpcaoBackupLayout.setHorizontalGroup(
            pnlOpcaoBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbnBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbnSistema)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOpcaoBackupLayout.setVerticalGroup(
            pnlOpcaoBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcaoBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcaoBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbnBanco)
                    .addComponent(rbnSistema))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSaidaBackup.setBackground(new java.awt.Color(255, 255, 255));
        pnlSaidaBackup.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saída do backup", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N

        tfdArquivo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tfdArquivo.setAutoscrolls(false);
        tfdArquivo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));

        btnArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/research_mini.png"))); // NOI18N
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSaidaBackupLayout = new javax.swing.GroupLayout(pnlSaidaBackup);
        pnlSaidaBackup.setLayout(pnlSaidaBackupLayout);
        pnlSaidaBackupLayout.setHorizontalGroup(
            pnlSaidaBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaidaBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfdArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        pnlSaidaBackupLayout.setVerticalGroup(
            pnlSaidaBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaidaBackupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSaidaBackupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnArquivo)
                    .addComponent(tfdArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlOpcaoBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSaidaBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOpcaoBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSaidaBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (Validacao.validarCampos(pnlSaidaBackup) == 0) {
            backupAndRestoreDatabaseFiles();
        } else {
            JOptionPane.showMessageDialog(this, "Há um campo em branco!");
        }

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tfdArquivo.setText(file.getSelectedFile().getAbsolutePath());
            System.out.println(file.getSelectedFile().getAbsolutePath());
        } else {
            tfdArquivo.setText("");
        }
    }//GEN-LAST:event_btnArquivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgOpcaoBackup;
    private javax.swing.JButton btnArquivo;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlOpcaoBackup;
    private javax.swing.JPanel pnlSaidaBackup;
    private javax.swing.JRadioButton rbnBanco;
    private javax.swing.JRadioButton rbnSistema;
    private javax.swing.JTextField tfdArquivo;
    // End of variables declaration//GEN-END:variables
}
