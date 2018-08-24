package hotel.view;

import javax.swing.*;

public class frmTornarUsuario extends JDialog {

    private String[] FResult;

    public frmTornarUsuario(JFrame pOwner) {
        super(pOwner, true);
        setLocationRelativeTo(null);

        FResult = new String[3];
        FResult[0] = "Closed";

        JLabel lblLogin = new JLabel();
        JTextField tfdLogin = new JTextField();
        JLabel lblTipo = new JLabel();
        ButtonGroup grbTipo = new ButtonGroup();
        JRadioButton rbTipoAdministrador = new JRadioButton();
        JRadioButton rbTipoUsuario = new JRadioButton();
        JButton btnConfirmar = new JButton();
        JButton btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Tornar Usuário");
        setResizable(false);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FResult[0] = "Confirm";
                FResult[1] = tfdLogin.getText();
                FResult[2] = grbTipo.getSelection().getActionCommand();
                dispose();
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FResult[0] = "Cancel";
                dispose();
            }
        });

        lblLogin.setText("Login:");
        lblTipo.setText("Tipo:");

        grbTipo.add(rbTipoAdministrador);
        rbTipoAdministrador.setText("Administrador");
        rbTipoAdministrador.setActionCommand("A");

        grbTipo.add(rbTipoUsuario);
        rbTipoUsuario.setText("Usuário");
        rbTipoUsuario.setActionCommand("U");
        rbTipoUsuario.setSelected(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnConfirmar, 100, 100, 100)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar, 100, 100, 100))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(lblTipo)
                                                                .addGap(12, 12, 12)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(rbTipoUsuario)
                                                                        .addComponent(rbTipoAdministrador)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblLogin)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLogin)
                                        .addComponent(tfdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTipo)
                                        .addComponent(rbTipoAdministrador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbTipoUsuario)
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar, 35, 35, 35)
                                        .addComponent(btnConfirmar, 35, 35, 35))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public String[] getResult() {
        return FResult;
    }
}
