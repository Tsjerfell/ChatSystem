package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import Interface.ThreadDisplayHistory;
import Interface.Visuel;
import databases.DBConv;
import main.Main;

public class TCPthreadSend extends Thread implements TCPThread{
	public int port;
	public PrintWriter output;
	public boolean convDone = false;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	public String addrIPdistant;
	
	public TCPthreadSend(int port, String addrIPdistant) {
		this.port = port;
		this.addrIPdistant = addrIPdistant;
	}
	public void sendMessage(String message,boolean fini) {
		if (fini) { 
			this.output.println("0"+message);
		
		} else {
			this.output.println("1"+message);
			Main.DB1.addMessage(Main.addressIP, this.addrIPdistant, "1"+message, dtf.format(LocalDateTime.now()));
            Runnable runnable = new ThreadDisplayHistory(this.addrIPdistant,Main.addressIP);
            Thread thread = new Thread(runnable);
            thread.start();
		}
	}
	
	public void endConversation() {
		this.convDone = true;
	}
	public void run() {
		ServerSocket serSoc;
		try {
			serSoc = new ServerSocket(this.port);
			Socket soc = serSoc.accept();
			
			BufferedReader input  = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			this.output = new PrintWriter(soc.getOutputStream(), true);
			
			String messageReceived = "";
			while(!this.convDone) {
				System.out.println(this.convDone);
				
				try { // Comme on a une thread avec un while(true) pour chaque conversation, on s'est dit que ça va occuper beaucoup des resources dans le CPU.
					  // Alors on a mit un sleep(1 sec) pour pas trop occuper 
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
				messageReceived = input.readLine();
				if (messageReceived == null) {
					//on a rien reçu, donc on ne fait rien
				} else if (messageReceived.charAt(0) == '0'){ //L'autre utilisateur viens de terminé la conversation
					this.convDone = true;
					this.output.println("0Terminé");
					Visuel.WriteHistoryField(Main.findPseudo(this.addrIPdistant)+" have ended the conversation");
					Iterator<otherUserTalkingTo> itr=Main.listotherUserTalkingTo.iterator();
					while(itr.hasNext()) {
						otherUserTalkingTo otheruser=itr.next();
						if (otheruser.addIP.equals(this.addrIPdistant)) {
							otheruser.thread.endConversation();
					        itr.remove();		
						}
					}
					Visuel.updateUserHavingConvWith();
					soc.close();
					serSoc.close();
				} else {
					Main.DB1.addMessage(this.addrIPdistant, Main.addressIP, messageReceived, dtf.format(LocalDateTime.now()));
                    Runnable runnable = new ThreadDisplayHistory(this.addrIPdistant,Main.addressIP);
                    Thread thread = new Thread(runnable);
                    thread.start();
				}
			}
			
			if(convDone) {
				System.out.println("fini");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block	
		}
		
		
	}
}
