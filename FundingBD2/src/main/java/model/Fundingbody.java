package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fundingbody {

	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fundingbody", "root", "Root@123");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	
	
	
	
	public String insertfd(String fname, String fcountry, String fyears, String fcategory, String email)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into fundingbd(`fid`,`fname`,`fcountry`,`fyears`,`fcategory`,`frequirements`)"
	 + " values (?, ?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, fname);
	 preparedStmt.setString(3, fcountry);
	 preparedStmt.setString(4, fyears);
	 preparedStmt.setString(5, fcategory);
	 preparedStmt.setString(6, email);

	// execute the statement
	
	 preparedStmt.execute();
	 con.close();
	 String newItems = readfd();
	 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	public String readfd()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }

	 output = "<table border='1'><tr><th>fname</th>" +"<th>fcountry</th>" +"<th>fyears</th>" +"<th>fcategory</th><th>frequirements</th></tr>";

	 String query = "select * from fundingbd";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String fid = Integer.toString(rs.getInt("fid"));
	 String fname = rs.getString("fname");
	 String fcountry = rs.getString("fcountry");
	 String fyears = rs.getString("fyears");
	 String fcategory = rs.getString("fcategory");
	 String frequirements = rs.getString("frequirements");
	
	 // Add into the html table
	 
	 output += "<tr><td><input id='hidfidUpdate' name='hidfidUpdate'type='hidden' value='" + fid
				+ "'>" + fname + "</td>";
	// output += "<tr><td>" + fid + "</td>";
	 //output += "<td>" + fname + "</td>";
	 output += "<td>" + fcountry + "</td>";
	 output += "<td>" + fyears + "</td>";
	 output += "<td>" + fcategory + "</td>";
	 output += "<td>" + frequirements + "</td>";

	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "+ "class='btnUpdate btn btn-secondary' data-fidd='" + fid + "'></td>"

	 + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-fidd='" + fid + "'>" +"</td>";
		
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
	
	
	
	
	public String updatefd(String ffid,String fname, String fcountry, String fyears, String fcategory, String frequirements)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE fundingbd SET fname=?,fcountry=?,fyears=?,fcategory=?,frequirements=? WHERE fid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, fname);
	 preparedStmt.setString(2, fcountry);
	 preparedStmt.setString(3,fyears);
	 preparedStmt.setString(4, fcategory);
	 preparedStmt.setString(5, frequirements);
	
	 preparedStmt.setInt(6, Integer.parseInt(ffid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItems = readfd();
	 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}"; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
		 	System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	
	
	
	public String deletefd(String fid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from fundingbd where fid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItems = readfd();
	 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	//login
	
	
	
	
	
}
