package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class billsModel {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/electrogrid_finance", "root", "");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


	public String insertBill(String Bill_id, String Month,String amount,String Unit_rate, String used_units,String Account_number) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			// create a prepared statement
			String query = " insert into bill (`Bill_id`,`Month`,`Amount`,`Unit_rate`,`used_units`,`Account_number`)"
						+ " values (?, ?, ?, ?, ?,?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, Bill_id); 
			preparedStmt.setInt(2, Integer.parseInt(Month)); 
			preparedStmt.setInt(3, Integer.parseInt(amount));
			preparedStmt.setDouble(4, Double.parseDouble(used_units)); 
			preparedStmt.setInt(5, Integer.parseInt(used_units)); 
			preparedStmt.setInt(6, Integer.parseInt(Account_number)); 
			
			// execute the statement

			preparedStmt.execute(); 
			con.close(); 
			String newItems = readBill(Account_number,Month);
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
     }
	
	public String readBill(String Accno,String month) {
		String output = "";
		int acn=Integer.parseInt(Accno);
		int mon=Integer.parseInt(month);
		double amount=0;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Bill ID</th><th>Month</th>" + "<th>Unit Rate</th>"
					+ "<th>Units Used</th>" + "<th>Amount</th><th>Account Number</th></tr>";

			String query = "select * from bill where Account_number="+acn+" and month="+mon+"";			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
			while (rs.next()) {
				amount=rs.getInt("used_units") * rs.getDouble("Unit_rate");
				String Amount = Double.toString(amount);
				
				String BillID = rs.getString("Bill_id");
				String Month = Integer.toString(rs.getInt("Month"));
				String UnitRate = Double.toString(rs.getDouble("Unit_rate"));
				String usedUnits = Integer.toString(rs.getInt("used_units"));
				String Account = Integer.toString(rs.getInt("Account_number"));
				
				// Add into the html table
				output += "<tr><td>" + BillID + "</td>";
				output += "<td>" + Month + "</td>";
				output += "<td>" + UnitRate + "</td>";
				output += "<td>" + usedUnits + "</td>";
				output += "<td>" + Amount + "</td>";
				output += "<td>" + Account + "</td>";
				// buttons
				output += "</tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
			
			 output = "{\"status\":\"success\", \"data\": \"" + output + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while reading.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
