package databases;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBConv{
	public Statement stmt;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	public Connection con;
	
	public DBConv(){
		try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			this.con=DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_017?useSSL=false&seJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","tp_servlet_017", "thaiJoh5");
		    this.stmt=con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Conversations ( IPSender VARCHAR(255), IPReceiver VARCHAR(255), Message VARCHAR(255), date VARCHAR(255))"; 
		    this.stmt.executeUpdate(sql);
	    }
	    catch(Exception e){ 
	    	System.out.println(e);
	    }
	}
	
	public void addMessage(String send, String receive, String msg, String date) {
		try {
			this.stmt.executeUpdate("INSERT INTO Conversations (IPSender, IPReceiver, Message, date)" + "VALUES ('"+send+"', '"+receive+"', '"+msg+"', '"+date+"')"); 
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
		ArrayList<String> dateList=new ArrayList<String>();
		try {
			ResultSet rs = this.stmt.executeQuery("SELECT * FROM Conversations WHERE ((IPSender='"+user1+"' AND IPReceiver='"+user2+"') OR (IPSender='"+user2+"' AND IPReceiver='"+user1+"')) ORDER BY Date ASC");
			while (rs.next()) {
				IPSenderList.add(rs.getString(1));
				IPReceiverList.add(rs.getString(2));
				msgList.add(rs.getString(3));
				dateList.add(rs.getString(4));
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
			this.stmt.executeUpdate("DROP TABLE Conversations");
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