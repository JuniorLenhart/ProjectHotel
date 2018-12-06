package hotel.view;

import hotel.controller.NovidadeController;
import hotel.controller.VistoController;
import hotel.model.Novidade;
import hotel.model.Parametro;
import hotel.model.Visto;
import hotel.support.Formatacao;
import hotel.support.Validacao;
import java.sql.Timestamp;
import javax.swing.JFrame;

public class frmNovidadeDetalhe extends javax.swing.JDialog {

    Visto visto;
    VistoController vistoController;
    Novidade novidade;
    boolean isForce;

    public frmNovidadeDetalhe(JFrame frame, boolean modal, Novidade pNovidade, boolean pForce) {
        super(frame, modal);
        initComponents();
        setLocationRelativeTo(null);

        isForce = pForce;
        ckbVisto.setVisible(isForce);

        visto = new Visto();
        vistoController = new VistoController();
        novidade = pNovidade;

        if (isForce) {
            novidade = new NovidadeController().getLastNew();
            if (!Validacao.nullOrEmpty(novidade)) {
                visto = vistoController.getReadByUserAndNew(Parametro.USUARIO, novidade);
                if (Validacao.nullOrEmpty(visto)) {
                    popularTelaNovidade(novidade);
                    this.setVisible(true);
                }
            }
        } else {
            popularTelaNovidade(novidade);
            this.setVisible(true);
        }
    }

    private void popularTelaNovidade(Novidade novidade) {
        lblCodigoValor.setText(novidade.getCodNovidade().toString());
        lblDataValor.setText(Formatacao.ajustaDataDMA(Timestamp.valueOf(novidade.getDtaNovidade().toString()).toString()));
        lblVersaoValor.setText(novidade.getVerNovidade());
        lblDescricaoValor.setText(novidade.getDesNovidade());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPanel = new javax.swing.JPanel();
        pnlAuditoria = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblVersao = new javax.swing.JLabel();
        lblCodigoValor = new javax.swing.JLabel();
        lblVersaoValor = new javax.swing.JLabel();
        lblDataValor = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblDescricaoValor = new javax.swing.JLabel();
        ckbVisto = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pnlPanel.setBackground(new java.awt.Color(255, 255, 255));

        pnlAuditoria.setBackground(new java.awt.Color(255, 255, 255));
        pnlAuditoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Novidade ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("Código:");
        lblCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblData.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblData.setForeground(new java.awt.Color(102, 102, 102));
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblData.setText("Data:");
        lblData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblVersao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblVersao.setForeground(new java.awt.Color(102, 102, 102));
        lblVersao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVersao.setText("Versão:");
        lblVersao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCodigoValor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblVersaoValor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblDataValor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(102, 102, 102));
        lblDescricao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescricao.setText("Descrição:");
        lblDescricao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDescricaoValor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescricaoValor.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlAuditoriaLayout = new javax.swing.GroupLayout(pnlAuditoria);
        pnlAuditoria.setLayout(pnlAuditoriaLayout);
        pnlAuditoriaLayout.setHorizontalGroup(
            pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                        .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescricaoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                                .addComponent(lblVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblVersaoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblCodigoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblDataValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAuditoriaLayout.setVerticalGroup(
            pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo)
                    .addComponent(lblCodigoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblData)
                    .addComponent(lblDataValor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVersao)
                    .addComponent(lblVersaoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(lblDescricaoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ckbVisto.setBackground(new java.awt.Color(255, 255, 255));
        ckbVisto.setText("Não exibir mensagem novamente");

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbVisto)
                    .addComponent(pnlAuditoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(ckbVisto)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(pnlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(pnlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        pnlPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (isForce && ckbVisto.isSelected()) {
            visto = new Visto();
            visto.setNovidade(novidade);
            visto.setUsuario(Parametro.USUARIO);
            visto.setIndVisto("S");
            vistoController.save(visto);
        }
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckbVisto;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoValor;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDataValor;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricaoValor;
    private javax.swing.JLabel lblVersao;
    private javax.swing.JLabel lblVersaoValor;
    private javax.swing.JPanel pnlAuditoria;
    private javax.swing.JPanel pnlPanel;
    // End of variables declaration//GEN-END:variables
}
