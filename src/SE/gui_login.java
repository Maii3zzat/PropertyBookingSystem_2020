/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE;

import javax.swing.JOptionPane;

public class gui_login extends javax.swing.JFrame {

    /**
     * Creates new form gui_login
     */
    public gui_login() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        LOGIN = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        RegisterButton = new javax.swing.JButton();
        loginPW = new javax.swing.JPasswordField();
        loginMail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LOGIN.setBackground(new java.awt.Color(0, 0, 0));
        LOGIN.setFont(new java.awt.Font("Sylfaen", 0, 36)); // NOI18N
        LOGIN.setText("Login");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        RegisterButton.setText("Register");
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });

        loginPW.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loginPW.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginPW.setText("PASSWORD");
        loginPW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginPWMouseClicked(evt);
            }
        });
        loginPW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPWActionPerformed(evt);
            }
        });

        loginMail.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loginMail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginMail.setText("ENTER  EMAIL");
        loginMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMailMouseClicked(evt);
            }
        });
        loginMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginPW)
                    .addComponent(loginMail))
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(LOGIN)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LOGIN)
                .addGap(23, 23, 23)
                .addComponent(loginMail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(loginPW, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        try {

            Account acc = new Account();
            String login_mail = (String) loginMail.getText();
            String login_PW = new String(loginPW.getPassword());
            if (acc.login(login_mail, login_PW)) {
                if (Account.access_type.equals("Admin")) {
                    new gui_admin().setVisible(true);
                    this.setVisible(false);
                    disable();
                } else if (Account.access_type.equals("Tenant")) {
                    new gui_tenant().setVisible(true);
                    this.setVisible(false);
                    disable();
                } else if (Account.access_type.equals("Buyer")) {
                    new gui_buyer().setVisible(true);
                    this.setVisible(false);
                    disable();
                } else if (Account.access_type.equals("Landlord")) {
                    new gui_landlord().setVisible(true);
                    this.setVisible(false);
                    disable();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Invailed login", "Something is wrong", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EEROR", "Something is wrong", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterButtonActionPerformed
        try {
            new gui_register().setVisible(true);
            this.setVisible(false);
            disable();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_RegisterButtonActionPerformed

    private void loginPWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginPWMouseClicked
        loginPW.setText("");
    }//GEN-LAST:event_loginPWMouseClicked

    private void loginMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMailMouseClicked
        loginMail.setText("");
    }//GEN-LAST:event_loginMailMouseClicked

    private void loginMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMailActionPerformed

    }//GEN-LAST:event_loginMailActionPerformed

    private void loginPWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginPWActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGIN;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginMail;
    private javax.swing.JPasswordField loginPW;
    // End of variables declaration//GEN-END:variables
}
