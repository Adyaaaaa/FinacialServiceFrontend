package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalaryModel {
	// A common method to connect to the DB
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


	public String insertSalary(String Emp_id,String Salary_id, String Amount,String Allowance,String Emp_fm) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			// create a prepared statement
			String query = " insert into salary (`Emp_id`,`Salary_id`,`Amount`,`Allowance`,`Emp_fm`)"
						+ " values (?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, Emp_id); 
			preparedStmt.setString(2, Salary_id); 
			preparedStmt.setInt(3, Integer.parseInt(Amount)); 
			preparedStmt.setInt(4, Integer.parseInt(Allowance)); 
			preparedStmt.setString(5,Emp_fm); 
			
			// execute the statement

			preparedStmt.execute(); 
			con.close(); 
			String newItems = readSalary();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
     }
	
	public String readSalary() 
	 { 
			String output = ""; 
			
			try
			{ 
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for reading."; } 
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Employee ID</th><th>Salary ID</th>" +
							"<th>Salary</th>" + 
							"<th>Allowance</th>" +
							"<th>Managed by</th>" +
							"<th>Update</th>" +
							"<th>Remove</th></tr>"; 
	 
					 String query = "select * from salary"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String Emp_id =  rs.getString("Emp_id"); 
						 String Salary_id = rs.getString("Salary_id"); 
						 String Amount = Integer.toString(rs.getInt("Amount"));
						 String Allowance = Integer.toString(rs.getInt("Allowance")); 
						 String Emp_fm = rs.getString("Emp_fm"); 
						 
						 // Add into the html table
						 output += "<tr><td>" + Emp_id + "</td>"; 
						 output += "<td>" + Salary_id + "</td>"; 
						 output += "<td>" + Amount + "</td>"; 
						 output += "<td>" + Allowance + "</td>"; 
						 output += "<td>" + Emp_fm + "</td>"; 
						 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
									+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
									+ Emp_id + "'>" + "</td></tr>";
					 }
					 
					 con.close(); 
					 
					 // Complete the html table
					 output += "</table>";
					 output = "{\"status\":\"success\", \"data\": \"" + output + "\"}";
			} 
			
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while reading.\"}";
				 System.err.println(e.getMessage()); 
			 } 
			return output; 
	 }
	
	public String updateSalary(String Emp_id, String Salary_id, String Amount, String Allowance, String Emp_fm) 
	 
	 { 
		
		String output = ""; 
		
		try
		{
			
				Connection con = connect(); 
				
				if (con == null) 
				{return "Error while connecting to the database for updating."; } 
				
				// create a prepared statement
				String query = "UPDATE salary SET Amount=?,Allowance=?,Emp_fm=? WHERE Emp_id=? AND Salary_id=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 
				 preparedStmt.setInt(1, Integer.parseInt(Amount)); 
				 preparedStmt.setInt(2, Integer.parseInt(Allowance)); 
				 preparedStmt.setString(3, Emp_fm); 
				 preparedStmt.setString(4, Emp_id);  
				 preparedStmt.setString(5, Salary_id);  
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newItems = readSalary();
				 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while updating.\"}";
			 System.err.println(e.getMessage()); 
		} 
		return output; 
		
	}
	
	public String deleteSalary(String Emp_id) 
	 { 
		
			String output = ""; 
			
			try
			{ 
				
				Connection con = connect(); 
				
				if (con == null) 
				{return "Error while connecting to the database for deleting."; } 
				
				// create a prepared statement
				String query = "delete from salary where Emp_id=?"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(Emp_id)); 
				
				// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newItems = readSalary();
					output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
				System.err.println(e.getMessage()); 
			} 
			return output; 
	 	} 
	} 

	
	
	
	
