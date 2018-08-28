package hotel.view;

import hotel.config.HibernateUtil;
import hotel.controller.ParametroController;
import hotel.controller.UsuarioController;
import hotel.model.Parametro;
import hotel.model.Usuario;
import hotel.support.Criptografia;
import hotel.support.Validacao;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmLogin extends javax.swing.JFrame {

    UsuarioController usuarioController;
    ParametroController parametroController;

    public frmLogin() {
        initComponents();
        new ParametroController().loadClass();
        usuarioController = new UsuarioController();
        parametroController = new ParametroController();

        setLocationRelativeTo(null); //centralizando o form

        pnlLogin.setBackground(new Color(0, 0, 0, 100)); //setando opacidade do panel
        try {
            lblBackground.setIcon(readingAndCreatingResizeImage());
        } catch (IOException ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        definindoAperenciaButton();

        setShape(new RoundRectangle2D.Double(0, 0, 800, 550, 60, 60));
    }

    public void definindoAperenciaButton() { //Deixando um JButton com cara de JLabel
        btnFechar.setFocusPainted(false);
        btnFechar.setMargin(new Insets(0, 0, 0, 0));
        btnFechar.setContentAreaFilled(false);
        btnFechar.setBorderPainted(false);
        btnFechar.setOpaque(false);
    }

    public void definirIcone(String caminho) {
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource(caminho)));
    }

    public ImageIcon readingAndCreatingResizeImage() throws IOException {
        Image img = new ImageIcon(getClass().getResource("/hotel/images/LoginBackGround5.jpg")).getImage()
                .getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img);
        return imageIcon;
    }

    public void logar() {
        if (Validacao.validarCampos(pnlLogin) == 0) {
            String senha = new Criptografia().criptografar(tfdSenha.getText());
            String login = tfdLogin.getText();
            if (usuarioController.validaLogin(login, senha) != null) {
                this.dispose();
                Usuario usuario = usuarioController.getUserWithLogin(login);
                Parametro.setUser(usuario);
                if (senha.equals(Parametro.DES_SENHA_DEFAULT)) {
                    new frmCadastrarNovaSenha(usuario).setVisible(true);
                } else {
                    new frmPrincipal(usuario).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Login e/ou senha incorreto(s)!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos obrigatórios não preenchidos!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JLayeredPane();
        lblIntegrador = new javax.swing.JLabel();
        lblHotel = new javax.swing.JLabel();
        lblFrase = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        pnlLogin = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        tfdLogin = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        btnLogar = new javax.swing.JButton();
        tfdSenha = new javax.swing.JPasswordField();
        lblLogarse = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIntegrador.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblIntegrador.setForeground(new java.awt.Color(255, 255, 255));
        lblIntegrador.setText("Integrador");
        pnlBackground.add(lblIntegrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 380, -1));

        lblHotel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblHotel.setForeground(new java.awt.Color(255, 255, 255));
        lblHotel.setText("Hotel");
        pnlBackground.add(lblHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 380, -1));

        lblFrase.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFrase.setForeground(new java.awt.Color(255, 255, 255));
        lblFrase.setText("Um novo conceito de hotel está surgindo!");
        pnlBackground.add(lblFrase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 360, -1));

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/error.png"))); // NOI18N
        btnFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFecharMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFecharMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnFecharMouseReleased(evt);
            }
        });
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        pnlBackground.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 30, 30));

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setText("Login");

        tfdLogin.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        tfdLogin.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(12, 91, 160)));
        tfdLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdLoginKeyPressed(evt);
            }
        });

        lblSenha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha.setText("Senha");

        btnLogar.setBackground(new java.awt.Color(12, 91, 160));
        btnLogar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnLogar.setForeground(new java.awt.Color(255, 255, 255));
        btnLogar.setText("Entrar");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        tfdSenha.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        tfdSenha.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(12, 91, 160)));
        tfdSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdLoginKeyPressed(evt);
            }
        });

        lblLogarse.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblLogarse.setForeground(new java.awt.Color(255, 255, 255));
        lblLogarse.setText("Logar-se");

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdLogin)
                    .addComponent(btnLogar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogin)
                            .addComponent(lblSenha))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfdSenha)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblLogarse, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogarse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(lblLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBackground.add(pnlLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 102, -1, 340));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/images/LoginBackGround4.jpeg"))); // NOI18N
        pnlBackground.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
        logar();
    }//GEN-LAST:event_btnLogarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseEntered
        definirIcone("/hotel/images/errorClicked.png");
        definindoAperenciaButton();
    }//GEN-LAST:event_btnFecharMouseEntered

    private void btnFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseExited
        definirIcone("/hotel/images/error.png");
        definindoAperenciaButton();
    }//GEN-LAST:event_btnFecharMouseExited

    private void btnFecharMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMousePressed
        definirIcone("/hotel/images/errorEntered.png");
        definindoAperenciaButton();
    }//GEN-LAST:event_btnFecharMousePressed

    private void btnFecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseReleased
        definirIcone("/hotel/images/error.png");
        definindoAperenciaButton();
    }//GEN-LAST:event_btnFecharMouseReleased

    private void tfdLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();
        }
    }//GEN-LAST:event_tfdLoginKeyPressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnLogar;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblFrase;
    private javax.swing.JLabel lblHotel;
    private javax.swing.JLabel lblIntegrador;
    private javax.swing.JLabel lblLogarse;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLayeredPane pnlBackground;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JTextField tfdLogin;
    private javax.swing.JPasswordField tfdSenha;
    // End of variables declaration//GEN-END:variables
}
