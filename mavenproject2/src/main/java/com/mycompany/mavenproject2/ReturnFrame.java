/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author amrit
 */
package com.mycompany.mavenproject2;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class ReturnFrame extends javax.swing.JFrame {
     JTextField memberIdField, bookIdField;
    JLabel fineLabel;
    JButton returnButton, backButton, clearFineButton;
    /**
     * Creates new form ReturnFrame
     */
    public ReturnFrame() {
        initComponentsReturn();
    }
    private void initComponentsReturn() {
        setTitle("Return Book");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245,245,220));
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(10, 10, 100, 25);
        add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(120, 10, 160, 25);
        add(memberIdField);

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(10, 40, 100, 25);
        add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(120, 40, 160, 25);
        add(bookIdField);

        fineLabel = new JLabel("Fine: 0 Rs");
        fineLabel.setBounds(10, 70, 200, 25);
        add(fineLabel);

        returnButton = new JButton("Return Book");
        returnButton.setBounds(120, 100, 120, 25);
        add(returnButton);

        backButton = new JButton("Back to Main Menu");
        backButton.setBounds(250, 100, 150, 25);
        add(backButton);

        clearFineButton = new JButton("Clear Fine");
        clearFineButton.setBounds(10, 130, 100, 25);
        add(clearFineButton);

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberId = memberIdField.getText();
                String bookId = bookIdField.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                     CallableStatement cs = conn.prepareCall("{call calculate_fine(?, ?, ?)}")) {
                    cs.setString(1, memberId);
                    cs.setString(2, bookId);
                    cs.registerOutParameter(3, java.sql.Types.NUMERIC);
                    cs.execute();

                    int fine = cs.getInt(3);
                    fineLabel.setText("Fine: " + fine + " Rs");

                    // Assuming there is a delete command to remove the returned book entry
                    String deleteSQL = "DELETE FROM lending WHERE member_id = ? AND book_id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(deleteSQL)) {
                        ps.setString(1, memberId);
                        ps.setString(2, bookId);
                        ps.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Book returned successfully! Fine: " + fine + " Rs");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error returning book: " + ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
                dispose();
            }
        });

        clearFineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fineLabel.setText("Fine: 0 Rs");
                JOptionPane.showMessageDialog(null, "Fine cleared!");
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ReturnFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
