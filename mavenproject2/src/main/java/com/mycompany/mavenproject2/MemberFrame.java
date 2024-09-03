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

public class MemberFrame extends javax.swing.JFrame {
     JTextField memberIdField, memberNameField, phoneField, locationField;
    JButton submitButton, backButton, searchButton;
    /**
     * Creates new form MemberFrame
     */
    public MemberFrame() {
        initComponentsMember();
    }
    
    private void initComponentsMember() {
       setTitle("New Member");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 220));

        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(10, 10, 100, 25);
        add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(120, 10, 160, 25);
        add(memberIdField);

        JLabel memberNameLabel = new JLabel("Member Name:");
        memberNameLabel.setBounds(10, 40, 100, 25);
        add(memberNameLabel);

        memberNameField = new JTextField();
        memberNameField.setBounds(120, 40, 160, 25);
        add(memberNameField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(10, 70, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 70, 160, 25);
        add(phoneField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(10, 100, 100, 25);
        add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(120, 100, 160, 25);
        add(locationField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 140, 100, 25);
        add(submitButton);

        backButton = new JButton("Back to Main Menu");
        backButton.setBounds(230, 140, 150, 25);
        add(backButton);


        searchButton = new JButton("Search");
        searchButton.setBounds(290, 170, 90, 25);
        add(searchButton);

        // Action listener for submitButton
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberId = memberIdField.getText();
                String memberName = memberNameField.getText();
                String phone = phoneField.getText();
                String location = locationField.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                     CallableStatement cs = conn.prepareCall("{call add_member(?, ?, ?, ?)}")) {
                    cs.setString(1, memberId);
                    cs.setString(2, memberName);
                    cs.setString(3, phone);
                    cs.setString(4, location);
                    cs.execute();
                    JOptionPane.showMessageDialog(null, "Member added successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding member: " + ex.getMessage());
                }
            }
        });

        // Action listener for backButton
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
                dispose();
            }
        });

        // Action listener for searchButton
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberIdToSearch = memberIdField.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                     CallableStatement cs = conn.prepareCall("{call get_member_details(?, ?, ?, ?)}")) {
                    cs.setString(1, memberIdToSearch);
                    cs.registerOutParameter(2, java.sql.Types.VARCHAR);
                    cs.registerOutParameter(3, java.sql.Types.VARCHAR);
                    cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                    cs.execute();

                    // Retrieve results
                    String memberName = cs.getString(2);
                    String phone = cs.getString(3);
                    String location = cs.getString(4);

                    // Set retrieved values to text fields
                    memberNameField.setText(memberName);
                    phoneField.setText(phone);
                    locationField.setText(location);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving member details: " + ex.getMessage());
                }
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
            java.util.logging.Logger.getLogger(MemberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
