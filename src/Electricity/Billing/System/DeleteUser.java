//package Electricity.Billing.System;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class DeleteUser extends JFrame implements ActionListener {
//
//    JComboBox<String> meterChoice;
//    JButton delete, cancel;
//
//    DeleteUser() {
//        // Set the frame properties
//        setSize(400, 300);
//        setLocation(450, 200);
//
//        // Create a panel for layout
//        JPanel p = new JPanel();
//        p.setLayout(null);
//        p.setBackground(new Color(173, 216, 230));
//        add(p);
//
//        // Label for Meter ID selection
//        JLabel lblmeter = new JLabel("Select Meter ID:");
//        lblmeter.setBounds(50, 100, 150, 30);
//        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        p.add(lblmeter);
//
//        // ComboBox to display available meter IDs
//        meterChoice = new JComboBox<>();
//        meterChoice.setBounds(180, 100, 150, 30);
//        p.add(meterChoice);
//        
//        // Populate ComboBox with meter IDs from the database
//        try {
//            Conn1 c = new Conn1();
//            String query = "SELECT meter_no FROM customer";
//            ResultSet rs = c.s.executeQuery(query);
//            while (rs.next()) {
//                meterChoice.addItem(rs.getString("meter_no"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Button for deleting user
//        delete = new JButton("Delete User");
//        delete.setBounds(80, 180, 100, 30);
//        delete.setBackground(Color.BLACK);
//        delete.setForeground(Color.WHITE);
//        delete.addActionListener(this);
//        p.add(delete);
//
//        // Button to cancel the operation
//        cancel = new JButton("Cancel");
//        cancel.setBounds(200, 180, 100, 30);
//        cancel.setBackground(Color.BLACK);
//        cancel.setForeground(Color.WHITE);
//        cancel.addActionListener(this);
//        p.add(cancel);
//
//        // Frame layout setup
//        setLayout(new BorderLayout());
//        add(p, "Center");
//
//        getContentPane().setBackground(Color.WHITE);
//
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == delete) {
//            String meter = (String) meterChoice.getSelectedItem();  // Get selected meter ID
//            
//            // SQL query to delete the user by Meter ID
//            String query = "DELETE FROM customer WHERE meter_no = '" + meter + "'";
//            
//            try {
//                Conn1 c = new Conn1();  // Your database connection class
//                int rowsAffected = c.s.executeUpdate(query);
//
//                if (rowsAffected > 0) {
//                    JOptionPane.showMessageDialog(null, "User Deleted Successfully");
//                    meterChoice.removeItem(meter);  // Remove the deleted meter ID from the combo box
//                } else {
//                    JOptionPane.showMessageDialog(null, "No User Found with the given Meter ID");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error while deleting user");
//            }
//
//        } else if (ae.getSource() == cancel) {
//            setVisible(false);  // Close the form if 'Cancel' is clicked
//        }
//    }
//
//    public static void main(String[] args) {
//        new DeleteUser();
//    }
//}
//

package Electricity.Billing.System;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteUser extends JFrame implements ActionListener {

    JComboBox<String> meterChoice;
    JButton delete, cancel;
    JPanel panel;

    DeleteUser() {
        // Frame properties
        setTitle("Delete Customer");
        setSize(500, 400);
        setLocation(400, 150);
        setLayout(new BorderLayout());
        
        // Panel setup with a nice border and background
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 250, 250));  // Light white-pink color
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));  // Add padding
        
        // Heading label
        JLabel heading = new JLabel("Delete Customer ");
        heading.setBounds(50, 30, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(new Color(60, 60, 60));  // Dark gray color
        panel.add(heading);

        // Label for Meter ID selection
        JLabel lblmeter = new JLabel("Select Meter ID:");
        lblmeter.setBounds(50, 100, 150, 30);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblmeter.setForeground(new Color(105, 105, 105));  // Medium gray color
        panel.add(lblmeter);

        // ComboBox for meter IDs
        meterChoice = new JComboBox<>();
        meterChoice.setBounds(220, 100, 200, 30);
        meterChoice.setBackground(Color.WHITE);
        meterChoice.setForeground(new Color(60, 60, 60));
        panel.add(meterChoice);

        // Populate the ComboBox with meter IDs from the database
        try {
            Conn1 c = new Conn1();
            String query = "SELECT meter_no FROM customer";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                meterChoice.addItem(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Delete button
        delete = new JButton("Delete");
        delete.setBounds(100, 200, 120, 40);
        delete.setBackground(new Color(220, 20, 60));  // Crimson color
        delete.setForeground(Color.WHITE);
        delete.setFont(new Font("Tahoma", Font.BOLD, 14));
        delete.setFocusPainted(false);
        delete.setBorder(new LineBorder(Color.BLACK));
        delete.addActionListener(this);
        delete.setToolTipText("Click to delete the selected user.");
        panel.add(delete);

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(260, 200, 120, 40);
        cancel.setBackground(new Color(0, 128, 128));  // Teal color
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancel.setFocusPainted(false);
        cancel.setBorder(new LineBorder(Color.BLACK));
        cancel.addActionListener(this);
        cancel.setToolTipText("Click to cancel and exit.");
        panel.add(cancel);

        // Add a background image (Optional)
//        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/delete.png"));
//        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
//        JLabel imageLabel = new JLabel(new ImageIcon(img));
//        imageLabel.setBounds(170, 260, 150, 150);
//        panel.add(imageLabel);

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);

        // Frame background color
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            String meter = (String) meterChoice.getSelectedItem();  // Get selected meter ID
            
            // SQL query to delete the user by Meter ID
            String query = "DELETE FROM customer WHERE meter_no = '" + meter + "'";
            
            try {
                Conn1 c = new Conn1();  // Your database connection class
                int rowsAffected = c.s.executeUpdate(query);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "User Deleted Successfully");
                    meterChoice.removeItem(meter);  // Remove the deleted meter ID from the combo box
                } else {
                    JOptionPane.showMessageDialog(null, "No User Found with the given Meter ID");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while deleting user");
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);  // Close the form if 'Cancel' is clicked
        }
    }

    public static void main(String[] args) {
        new DeleteUser();
    }
}


