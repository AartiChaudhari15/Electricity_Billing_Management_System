package Electricity.Billing.System;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ForgotPass extends JFrame implements ActionListener {
    JTextField tfmeter, tfemail;
    JButton btnRetrieveUsername, btnResetPassword, btnCancel;

    ForgotPass() {
        setTitle("Forgot Username/Password");
        setSize(500, 350);
        setLocation(650, 300);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(230, 230, 250));
        add(p);

        JLabel heading = new JLabel("Recover Your Account");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        heading.setBounds(150, 10, 200, 25);
        p.add(heading);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(50, 80, 100, 20);
        p.add(lblmeter);

        tfmeter = new JTextField();
        tfmeter.setBounds(200, 80, 200, 25);
        p.add(tfmeter);

        JLabel lblemail = new JLabel("Name");
        lblemail.setBounds(50, 130, 100, 20);
        p.add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 130, 200, 25);
        p.add(tfemail);

        btnRetrieveUsername = new JButton("Retrieve Username");
        btnRetrieveUsername.setBounds(50, 200, 150, 30);
        btnRetrieveUsername.setBackground(Color.BLACK);
        btnRetrieveUsername.setForeground(Color.WHITE);
        btnRetrieveUsername.addActionListener(this);
        p.add(btnRetrieveUsername);

        btnResetPassword = new JButton("Reset Password");
        btnResetPassword.setBounds(250, 200, 150, 30);
        btnResetPassword.setBackground(Color.BLACK);
        btnResetPassword.setForeground(Color.WHITE);
        btnResetPassword.addActionListener(this);
        p.add(btnResetPassword);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(150, 250, 150, 30);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        p.add(btnCancel);

        setLayout(new BorderLayout());
        add(p, "Center");

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRetrieveUsername) {
            retrieveUsername();
        } else if (ae.getSource() == btnResetPassword) {
            resetPassword();
        } else if (ae.getSource() == btnCancel) {
            setVisible(false);
        }
    }

    public void retrieveUsername() {
        String meter = tfmeter.getText();
        String name1 = tfemail.getText();

        String query = "SELECT username FROM login WHERE meter_no = '" + meter + "' AND name = '" + name1 + "'";

        try {
            Conn1 c = new Conn1();
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                String username = rs.getString("username");
                JOptionPane.showMessageDialog(this, "Your Username: " + username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Meter Number or Email!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetPassword() {
        String meter = tfmeter.getText();
        String name1 = tfemail.getText();
        String query = "SELECT * FROM login WHERE meter_no = '" + meter + "' AND name = '" + name1 + "'";

        try {
            Conn1 c = new Conn1();
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                String newPassword = JOptionPane.showInputDialog(this, "Enter New Password");
                if (newPassword != null && !newPassword.isEmpty()) {
                    String updateQuery = "UPDATE login SET password = '" + newPassword + "' WHERE meter_no = '" + meter + "'";
                    c.s.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(this, "Password Reset Successfully");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Meter Number or Email!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ForgotPass();
    }
}
