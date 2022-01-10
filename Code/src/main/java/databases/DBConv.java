package databases;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;

public class DBConv{
	Statement stmt;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	Connection con;
	
	public DBConv(){
		try
	    {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
			this.con=DriverManager.getConnection("jdbc:odbc:DBConv");
		    this.stmt=con.createStatement();
			String sql = "CREATE TABLE Conversations " +
		            "( IPSender VARCHAR(255), " + 
		            " IPReceiver VARCHAR(255), Message VARCHAR(255), date Date)"; 
		    this.stmt.executeUpdate(sql);
	    }
	    catch(Exception e){ 
	    	System.out.println(e);
	    }
	}
	
	public void addMessage(String send, String receive, String msg, Date date) {
		try {
			this.stmt.executeUpdate("INSERT INTO Users (IPSender, IPReceiver, Message, date)" + "VALUES ('"+send+"', '"+receive+"', '"+msg+"', '"+dateFormat.format(date)+"')"); 
		}
		catch (Exception e) {
			System.out.println(e);
		}
	} 
	
	public Conversation getConv(String user1, String user2) {
		Conversation result=null;
		ArrayList<String> IPSenderList=new ArrayList<String>();
		ArrayList<String> IPReceiverList=new ArrayList<String>();
		ArrayList<String> msgList=new ArrayList<String>();
		ArrayList<Date> dateList=new ArrayList<Date>();
		try {
			ResultSet rs = this.stmt.executeQuery("SELECT * FROM Users WHERE IPSender='"+user1+"' AND IPReceiver='"+user2+"' OR IPSender='"+user2+"' AND IPReceiver='"+user1+"' ORDE BY Date DESC");
			while (rs.next()) {
				IPSenderList.add(rs.getString("IPSender"));
				IPReceiverList.add(rs.getString("IPReceiver"));
				msgList.add(rs.getString("msg"));
				dateList.add(rs.getDate("date"));
			}
			result=new Conversation (IPSenderList, IPReceiverList, msgList, dateList);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public void dropTable() {
		try {
			this.stmt.executeUpdate("DROP TABLE Conv");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}