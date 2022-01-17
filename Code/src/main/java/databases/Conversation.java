package databases;

import main.Main;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Conversation{
	public ArrayList<String> IPSenderList;
	ArrayList<String> IPReceiverList;
	ArrayList<String> msgList;
	ArrayList<String> dateList;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	
	public Conversation(ArrayList<String> sender, ArrayList<String> receiver, ArrayList<String> msg, ArrayList<String> date) {
		this.IPSenderList=sender;
		this.IPReceiverList=receiver;
		this.msgList=msg;
		this.dateList=date;
	}
	
	public String toString () {
        ArrayList<String> convList=new ArrayList<String>();
        for (int i=0;i<this.IPSenderList.size();i++) {
            convList.add("From "+Main.findPseudo(this.IPSenderList.get(i))+" to "+Main.findPseudo(this.IPReceiverList.get(i))+" at "+this.dateList.get(i)+" : "+this.msgList.get(i)+"\n");
        }
        return String.join("",convList);
    }
}