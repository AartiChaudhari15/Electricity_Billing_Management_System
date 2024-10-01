package Electricity.Billing.System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn1 {

    Connection c;
    Statement s;
    
    Conn1() {
    	//Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "2003");
            
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}