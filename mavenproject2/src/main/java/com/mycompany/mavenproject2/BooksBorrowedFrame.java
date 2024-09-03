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
public class BooksBorrowedFrame extends javax.swing.JFrame {
    private JTextField memberIdField;
    private JButton searchButton, backButton,issueDateButton,dueDateButton;
    private JLabel jLabel1;
    private JTextArea booksTextArea;
    /**
     * Creates new form BooksBorrowedFrame
     */
    public BooksBorrowedFrame() {
        initComponentsBorrowed();
    }
    private void initComponentsBorrowed() {

        memberIdField = new javax.swing.JTextField();
        booksTextArea = new javax.swing.JTextArea();
        searchButton = new javax.swing.JButton();
        issueDateButton = new javax.swing.JButton();
        dueDateButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Books Borrowed");
        getContentPane().setBackground(new Color(245, 245, 220));

        jLabel1.setText("Value:");

        searchButton.setText("Search by Member ID");
        issueDateButton.setText("Search by Issue Date");
        dueDateButton.setText("Search by Due Date");
        backButton.setText("Back to Main Menu");

        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        issueDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueDateButtonActionPerformed(evt);
            }
        });

        dueDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueDateButtonActionPerformed(evt);
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(booksTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(memberIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(issueDateButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dueDateButton)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(memberIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(issueDateButton)
                                        .addComponent(dueDateButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(booksTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backButton)
                                .addContainerGap())
        );

        pack();
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
         private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String memberId = memberIdField.getText();
        if (memberId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a member ID");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
             PreparedStatement ps = conn.prepareStatement("SELECT book_id, issue_date, due_date FROM lending WHERE member_id = ?")) {
            ps.setString(1, memberId.trim());
            ResultSet rs = ps.executeQuery();
            StringBuilder booksInfo = new StringBuilder();

            while (rs.next()) {
                String bookId = rs.getString("book_id");
                Date issueDate = rs.getDate("issue_date");
                Date dueDate = rs.getDate("due_date");
                booksInfo.append("Book ID: ").append(bookId)
                        .append(", Issue Date: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(issueDate))
                        .append(", Due Date: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(dueDate))
                        .append("\n");
            }

            if (booksInfo.length() == 0) {
                booksInfo.append("No books currently borrowed.");
            }

            booksTextArea.setText(booksInfo.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching borrowed books: " + ex.getMessage());
        }
    }

    private void issueDateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String issueDateStr = memberIdField.getText();
        if (issueDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an issue date (dd-MMM-yyyy)");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
             PreparedStatement ps = conn.prepareStatement("SELECT book_id, issue_date, due_date FROM lending WHERE issue_date = TO_DATE(?, 'dd-MON-yyyy')")) {
            ps.setString(1, issueDateStr.trim());
            ResultSet rs = ps.executeQuery();
            StringBuilder booksInfo = new StringBuilder();

            while (rs.next()) {
                String bookId = rs.getString("book_id");
                Date issueDate = rs.getDate("issue_date");
                Date dueDate = rs.getDate("due_date");
                booksInfo.append("Book ID: ").append(bookId)
                        .append(", Issue Date: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(issueDate))
                        .append(", Due Date: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(dueDate))
                        .append("\n");
            }

            if (booksInfo.length() == 0) {
                booksInfo.append("No books issued on ").append(issueDateStr);
            }

            booksTextArea.setText(booksInfo.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching borrowed books: " + ex.getMessage());
        }
    }

    private void dueDateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String dueDateStr = memberIdField.getText();
        if (dueDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a due date (dd-MMM-yyyy)");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
             PreparedStatement ps = conn.prepareStatement("SELECT book_id, issue_date, due_date FROM lending WHERE due_date = TO_DATE(?, 'dd-MON-yyyy')")) {
            ps.setString(1, dueDateStr.trim());
            ResultSet rs = ps.executeQuery();
            StringBuilder booksInfo = new StringBuilder();

            while (rs.next()) {
                String bookId = rs.getString("book_id");
                Date issueDate = rs.getDate("issue_date");
                Date dueDate = rs.getDate("due_date");
                booksInfo.append("Book ID: ").append(bookId)
                        .append(", Issue Date: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(issueDate))
                        .append(", Due Date: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(dueDate))
                        .append("\n");
            }

            if (booksInfo.length() == 0) {
                booksInfo.append("No books due on ").append(dueDateStr);
            }

            booksTextArea.setText(booksInfo.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching borrowed books: " + ex.getMessage());
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new MainFrame().setVisible(true);
        dispose();
    }

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
            java.util.logging.Logger.getLogger(BooksBorrowedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksBorrowedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksBorrowedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksBorrowedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksBorrowedFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
