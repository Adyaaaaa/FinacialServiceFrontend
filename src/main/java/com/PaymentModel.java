package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentModel {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/electrogrid_customer", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public String readPayment() 
	 { 
			String output = ""; 
			
			try
			{ 
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for reading."; } 
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Payment ID</th><th>Payment Method</th>" +
							"<th>Bill ID</th>" + 
							"<th>Customer ID</th>" + "</tr>"; 
	 
					 String query = "select * from payment"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String payment_id =  rs.getString("payment_id"); 
						 String payment_method = rs.getString("payment_method"); 
						 String Bill_id= rs.getString("Bill_id"); 
						 String cus_id = rs.getString("cus_id"); 
						 
						 // Add into the html table
						 output += "<tr><td>" + payment_id + "</td>"; 
						 output += "<td>" + payment_method + "</td>"; 
						 output += "<td>" + Bill_id + "</td>"; 
						 output += "<td>" + cus_id + "</td>"; 
						
						 
						 // buttons
						 output += "</tr>"; 
					 }
					 
					 con.close(); 
					 
					 // Complete the html table
					 output += "</table>";
					 
			} 
			
			 catch (Exception e) 
			 { 
				 output = "Error while reading the items."; 
				 System.err.println(e.getMessage()); 
			 } 
			return output; 
	 }
	
}