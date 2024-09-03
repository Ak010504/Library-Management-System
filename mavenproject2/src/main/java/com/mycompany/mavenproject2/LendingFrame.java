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
import java.text.SimpleDateFormat;

public class LendingFrame extends javax.swing.JFrame {
     JTextField memberIdField, bookIdsField, issueDateField, dueDateField;
    JButton submitButton, backButton, searchButton;
    /**
     * Creates new form LendingFrame
     */
    public LendingFrame() {
        initComponentsLending();
    }
    private void initComponentsLending() {
        setTitle("Library Lending");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245,245,220));

        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(10, 10, 100, 25);
        add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(120, 10, 160, 25);
        add(memberIdField);

        JLabel bookIdsLabel = new JLabel("Book IDs (comma separated):");
        bookIdsLabel.setBounds(10, 40, 160, 25);
        add(bookIdsLabel);

        bookIdsField = new JTextField();
        bookIdsField.setBounds(180, 40, 160, 25);
        add(bookIdsField);
        
        JButton searchButton = new JButton("BookHolder");
        searchButton.setBounds(120, 200, 100, 25);
        add(searchButton);
        
        JLabel issueDateLabel = new JLabel("Issue Date (dd-MMM-yyyy):");
        issueDateLabel.setBounds(10, 70, 160, 25);
        add(issueDateLabel);

        issueDateField = new JTextField();
        issueDateField.setBounds(180, 70, 100, 25);
        add(issueDateField);

        JLabel dueDateLabel = new JLabel("Due Date (dd-MMM-yyyy):");
        dueDateLabel.setBounds(10, 100, 160, 25);
        add(dueDateLabel);

        dueDateField = new JTextField();
        dueDateField.setBounds(180, 100, 100, 25);
        add(dueDateField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 140, 100, 25);
        add(submitButton);

        backButton = new JButton("Back to Main Menu");
        backButton.setBounds(230, 140, 150, 25);
        add(backButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberId = memberIdField.getText();
                String bookIdsStr = bookIdsField.getText();
                String[] bookIds = bookIdsStr.split(",");
                String issueDateStr = issueDateField.getText();
                String dueDateStr = dueDateField.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                     CallableStatement cs = conn.prepareCall("{call lend_book(?, ?, ?, ?)}")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                    java.util.Date issueDateUtil = sdf.parse(issueDateStr);
                    java.util.Date dueDateUtil = sdf.parse(dueDateStr);
                    java.sql.Date issueDate = new java.sql.Date(issueDateUtil.getTime());
                    java.sql.Date dueDate = new java.sql.Date(dueDateUtil.getTime());

                    for (String bookId : bookIds) {
                        cs.setString(1, memberId.trim());
                        cs.setString(2, bookId.trim());
                        cs.setDate(3, issueDate);
                        cs.setDate(4, dueDate);
                        cs.execute();
                    }
                    JOptionPane.showMessageDialog(null, "Books lent successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error lending books: " + ex.getMessage());
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String bookIdToSearch = bookIdsField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
             CallableStatement cs = conn.prepareCall("{call get_lending_details(?, ?, ?, ?, ?)}")) {

            cs.setString(1, bookIdToSearch);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR); // Member ID
            cs.registerOutParameter(3, java.sql.Types.DATE);    // Issue Date
            cs.registerOutParameter(4, java.sql.Types.DATE);    // Due Date
            cs.registerOutParameter(5, java.sql.Types.VARCHAR); // Book Name (optional)

            cs.execute();

            String memberId = cs.getString(2);
            Date issueDate = cs.getDate(3);
            Date dueDate = cs.getDate(4);
            String bookName = cs.getString(5); // If book name is returned

            if (memberId != null) {
                memberIdField.setText(memberId);
                issueDateField.setText(issueDate.toString());
                dueDateField.setText(dueDate.toString());
                // Optionally set book name in a field if it's returned
                
                JOptionPane.showMessageDialog(null, "Lending details found!");
            } else {
                JOptionPane.showMessageDialog(null, "No lending details found for book ID: " + bookIdToSearch);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving lending details: " + ex.getMessage());
        }
    }
});
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
                dispose();
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
            java.util.logging.Logger.getLogger(LendingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LendingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LendingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LendingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LendingFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
