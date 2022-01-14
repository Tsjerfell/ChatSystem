package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Interface.Visuel;

public class TCPthreadReceiver extends Thread implements TCPThread{
	
	public int port;
	public String AddIP;
	public PrintWriter output;
	public boolean convDone = false;
	
	public void sendMessage(String message,boolean fini) {
		if (fini) { 
			this.output.println("0"+message);
		} else {
			this.output.println("1"+message);
		}
	} 
	
	public TCPthreadReceiver(int port,String AddIP) {
		this.port = port;
		this.AddIP = AddIP;
	}
	
	public void endConversation() {
		this.convDone = true;
	}
		
	public void run() {
		try {
			Socket socket = new Socket(this.AddIP,this.port);
				
			BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.output = new PrintWriter(socket.getOutputStream(), true);
				
			String messageReceived = "";
			while(!this.convDone) {
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
				} else if (messageReceived.charAt(0) == (0)){ //L'autre utilisateur viens de terminé la conversation
					this.convDone = true;
					this.output.println("0Terminé");
					socket.close();
				} else {
					Visuel.WriteHistoryField(messageReceived.substring(1));
				}
			}		
				
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
