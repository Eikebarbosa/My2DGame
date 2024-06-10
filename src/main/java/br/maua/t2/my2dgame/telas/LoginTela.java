/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.maua.t2.my2dgame.telas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.maua.t2.my2dgame.Main;
import br.maua.t2.my2dgame.persistencia.db.UsuarioDAO;

/**
 *
 * @author schix
 */
public class LoginTela extends javax.swing.JFrame {

    private JPanel interactivePanel = new JPanel();

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginTela().setVisible(true);
            }
        });
    }

    /**
     * Creates new form LoginTela
     */
    public LoginTela() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoginTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            var backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/telaLogin.png"));
            setContentPane(new JPanel(new java.awt.BorderLayout()) {
                @Override
                public void paintComponent(java.awt.Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, getSize().width, getSize().height, null);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
        setMinimumSize(new Dimension(900, 700));
        setMaximumSize(getMinimumSize());
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        loginTextField = new javax.swing.JTextField();
        senhaPasswordField = new javax.swing.JPasswordField();
        cancelarButton = new javax.swing.JButton();
        entrarButton = new javax.swing.JButton();
        criarContaButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        senhaToggleButton = new javax.swing.JToggleButton();

        jLabel2.setText("jLabel2");

        button1.setLabel("button1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginTextField.setBorder(javax.swing.BorderFactory
                .createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Digite seu login: "));
        loginTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginTextFieldActionPerformed(evt);
            }
        });

        senhaPasswordField.setBorder(javax.swing.BorderFactory
                .createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Digite sua senha:"));

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        entrarButton.setText("Entrar");
        entrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarButtonActionPerformed(evt);
            }
        });

        criarContaButton.setText("Criar Conta");
        criarContaButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        criarContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarContaButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Não tem login?");

        senhaToggleButton.setText("👁️");
        senhaToggleButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        senhaToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(interactivePanel);
        interactivePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(279, 279, 279)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addComponent(senhaPasswordField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 299,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(senhaToggleButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(loginTextField)
                                                        .addComponent(criarContaButton,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(cancelarButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 171,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(entrarButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 174,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(186, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(senhaPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 50,
                                                Short.MAX_VALUE)
                                        .addComponent(senhaToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(entrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(criarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        interactivePanel.setOpaque(false);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 500, 0, 0);
        getContentPane().add(interactivePanel, c);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loginTextFieldActionPerformed
    }// GEN-LAST:event_loginTextFieldActionPerformed

    private void criarContaButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_criarContaButtonActionPerformed
        new CadastrarTela().setVisible(true);
        dispose();
    }// GEN-LAST:event_criarContaButtonActionPerformed

    private void entrarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_entrarButtonActionPerformed

        try {
            var login = loginTextField.getText();
            var senha = new String(senhaPasswordField.getPassword());

            if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
                JOptionPane.showMessageDialog(null, "Seja bem-vindo ao painel admin!");

                new AdminTela().setVisible(true);
                this.dispose();
                return;
            }

            var usuario = UsuarioDAO.authenticate(login, senha);
            if (usuario != null) {
                Main.iniciarJogo(usuario);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Conta não existente, crie uma e tente novamente!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "falhou");
        }
    }// GEN-LAST:event_entrarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelarButtonActionPerformed
        dispose();
    }// GEN-LAST:event_cancelarButtonActionPerformed

    private void senhaToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_senhaToggleButtonActionPerformed
        if (senhaToggleButton.isSelected()) {
            senhaPasswordField.setEchoChar((char) 0); // Mostrar a senha
        } else {
            senhaPasswordField.setEchoChar('*'); // Ocultar a senha
        }

    }// GEN-LAST:event_senhaToggleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton criarContaButton;
    private javax.swing.JButton entrarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JPasswordField senhaPasswordField;
    private javax.swing.JToggleButton senhaToggleButton;
    // End of variables declaration//GEN-END:variables
}
