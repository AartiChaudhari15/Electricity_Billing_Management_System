package Electricity.Billing.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Payment extends JFrame implements ActionListener {

    JTextField meterField, amountField, cardNumberField, cvvField, upiField;
    JButton payButton, cancelButton;
    JComboBox<String> paymentMethod;
    JLabel lblCardNumber, lblCVV, lblUPI;

    Payment(String meterNo) {
        
        setTitle("Electricity Bill Payment");
        setSize(700, 450);
        setLocation(350, 150);
        setLayout(null);

        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(240, 248, 255));  // Light color background
        p.setBounds(0, 0, 700, 450);  // Set bounds for panel to cover entire frame
        add(p);

        
        JLabel heading = new JLabel("Electricity Bill Payment");
        heading.setBounds(200, 20, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(Color.black);
        p.add(heading);

        
        JLabel lblMeter = new JLabel("Meter Number:");
        lblMeter.setBounds(100, 80, 150, 30);
        lblMeter.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblMeter);

        meterField = new JTextField(meterNo);
        meterField.setBounds(250, 80, 300, 30);
        meterField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        meterField.setEditable(false);  
        p.add(meterField);

        
        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(100, 130, 150, 30);
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(250, 130, 300, 30);
        amountField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(amountField);

        
        JLabel lblPaymentMethod = new JLabel("Payment Method:");
        lblPaymentMethod.setBounds(100, 180, 150, 30);
        lblPaymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblPaymentMethod);

        String[] methods = {"Select", "Credit/Debit Card", "UPI"};
        paymentMethod = new JComboBox<>(methods);
        paymentMethod.setBounds(250, 180, 300, 30);
        paymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
        paymentMethod.addActionListener(this);
        p.add(paymentMethod);

        
        lblCardNumber = new JLabel("Card Number:");
        lblCardNumber.setBounds(100, 230, 150, 30);
        lblCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCardNumber.setVisible(false);  // Initially hidden
        p.add(lblCardNumber);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(250, 230, 300, 30);
        cardNumberField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cardNumberField.setVisible(false);
        p.add(cardNumberField);

       
        lblCVV = new JLabel("CVV:");
        lblCVV.setBounds(100, 280, 150, 30);
        lblCVV.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCVV.setVisible(false);  // Initially hidden
        p.add(lblCVV);

        cvvField = new JTextField();
        cvvField.setBounds(250, 280, 100, 30);
        cvvField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cvvField.setVisible(false);
        p.add(cvvField);

        
        lblUPI = new JLabel("UPI ID:");
        lblUPI.setBounds(100, 230, 150, 30);
        lblUPI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUPI.setVisible(false);  // Initially hidden
        p.add(lblUPI);

        upiField = new JTextField();
        upiField.setBounds(250, 230, 300, 30);
        upiField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        upiField.setVisible(false);
        p.add(upiField);

        
        payButton = new JButton("Pay");
        payButton.setBounds(200, 350, 120, 40);
        payButton.setBackground(new Color(0, 128, 0));  // Green background
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        payButton.addActionListener(this);
        p.add(payButton);

        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 350, 120, 40);
        cancelButton.setBackground(new Color(255, 69, 0));  // Red background
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cancelButton.addActionListener(this);
        p.add(cancelButton);

        add(p);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == paymentMethod) {
            String selectedMethod = (String) paymentMethod.getSelectedItem();
            
            
            if (selectedMethod.equals("Credit/Debit Card")) {
                lblCardNumber.setVisible(true);
                cardNumberField.setVisible(true);
                lblCVV.setVisible(true);
                cvvField.setVisible(true);
                lblUPI.setVisible(false);
                upiField.setVisible(false);
            } else if (selectedMethod.equals("UPI")) {
                lblCardNumber.setVisible(false);
                cardNumberField.setVisible(false);
                lblCVV.setVisible(false);
                cvvField.setVisible(false);
                lblUPI.setVisible(true);
                upiField.setVisible(true);
            } else {
                lblCardNumber.setVisible(false);
                cardNumberField.setVisible(false);
                lblCVV.setVisible(false);
                cvvField.setVisible(false);
                lblUPI.setVisible(false);
                upiField.setVisible(false);
            }
            repaint();  
        } else if (ae.getSource() == payButton) {
            
            if (!validateFields()) return;

            
            String meterNo = meterField.getText();
            String amount = amountField.getText();
            String selectedMethod = (String) paymentMethod.getSelectedItem();
            
            
            JOptionPane.showMessageDialog(null, "Payment of " + amount + " for Meter " + meterNo + " using " + selectedMethod + " was successful!");
            setVisible(false);
        } else if (ae.getSource() == cancelButton) {
            setVisible(false);  
        }
    }

    
    private boolean validateFields() {
        String amount = amountField.getText();
        String selectedMethod = (String) paymentMethod.getSelectedItem();
        
        if (amount.isEmpty() || "Select".equals(selectedMethod) ||
                (selectedMethod.equals("Credit/Debit Card") && (cardNumberField.getText().isEmpty() || cvvField.getText().isEmpty())) ||
                (selectedMethod.equals("UPI") && upiField.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return false;
            }

      
        try {
            if (Double.parseDouble(amount) <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount!");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid amount!");
            return false;
        }

        if ("Select".equals(selectedMethod)) {
            JOptionPane.showMessageDialog(null, "Please select a payment method!");
            return false;
        }

        if (selectedMethod.equals("Credit/Debit Card")) {
            String cardNumber = cardNumberField.getText();
            String cvv = cvvField.getText();

            if (cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Card number must be 16 digits!");
                return false;
            }

            if (cvv.length() != 3 || !cvv.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "CVV must be 3 digits!");
                return false;
            }
        }

        if (selectedMethod.equals("UPI")) {
            String upiId = upiField.getText();

            if (!upiId.contains("@")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid UPI ID!");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        new Payment("123456"); 
    }
}



