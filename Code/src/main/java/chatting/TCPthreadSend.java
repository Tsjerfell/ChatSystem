package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import Interface.Visuel;
import main.Main;

public class TCPthreadSend extends Thread implements TCPThread{
	public int port;
	public PrintWriter output;
	public boolean convDone = false;
	
	public TCPthreadSend(int port) {
		this.port = port;
	}
	public void sendMessage(String message,boolean fini) {
		if (fini) { 
			this.output.println("0"+message);
		
		} else {
			this.output.println("1"+message);
		}
	}
	
	public void endConversation() {
		this.convDone = true;
	}
	public void run() {
		ServerSocket serSoc;
		try {
			serSoc = new ServerSocket(port);
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
				} else if (messageReceived.charAt(0) == (0)){ //L'autre utilisateur viens de terminé la conversation
					this.convDone = true;
					this.output.println("Terminé");
					soc.close();
					serSoc.close();
				} else {
					Visuel.WriteHistoryField(messageReceived.substring(1));
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
