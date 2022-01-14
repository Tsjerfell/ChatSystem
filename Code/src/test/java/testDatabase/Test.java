package testDatabase;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import databases.Conversation;
import databases.DBConv;

public class Test{
	public static void main (String[] args) {
		DBConv DB1=new DBConv();		
		DB1.addMessage("j", "h","guguy","01/03/15");
		DB1.addMessage("Vladimir", "h","hgjh","01/02/15");
		DB1.addMessage("Giancarlo", "8.9.10.11","hiuhu","01/02/15");
		DB1.addMessage("j", "10.1.5.6","guguy","01/02/15");
		DB1.addMessage("Vladimir", "10.11.12.13","oijoij","01/02/15");
		DB1.addMessage("j", "h","joji","01/02/15");
		DB1.addMessage("Jean-François", "10.1.5.6","jojioji","01/02/15");
		DB1.addMessage("h", "j","kpokpok","01/02/15");
		DB1.addMessage("Giancarlo", "8.9.10.11","pojpokop","01/02/15");
		DB1.addMessage("Jean-François", "10.1.5.6","guguy","01/02/15");
		DB1.addMessage("h", "j","jiojioj","01/02/15");
		DB1.addMessage("Giancarlo", "8.9.10.11","kpopkopokp","01/02/15");
		System.out.println(DB1.getConv("h","j").toString());
		try {
			DB1.stmt.executeUpdate("TRUNCATE TABLE Conversations") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB1.closeConnection();
	}
}