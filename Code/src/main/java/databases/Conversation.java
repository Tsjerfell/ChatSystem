package databases;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Conversation{
	ArrayList<String> IPSenderList;
	ArrayList<String> IPReceiverList;
	ArrayList<String> msgList;
	ArrayList<Date> dateList;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	
	public Conversation(ArrayList<String> sender, ArrayList<String> receiver, ArrayList<String> msg, ArrayList<Date> date) {
		this.IPSenderList=sender;
		this.IPReceiverList=receiver;
		this.msgList=msg;
		this.dateList=date;
	}
	
	public String toString (Conversation con) {
		ArrayList<String> convList=new ArrayList<String>();
		for (int i=0;i<con.IPSenderList.size();i++) {
			convList.add("From "+con.IPSenderList.get(i)+" to "+con.IPReceiverList.get(i)+" at "+dateFormat.format(con.dateList.get(i))+" : "+con.msgList.get(i)+"\n");
		}
		return String.join(" ",convList);
	}
}