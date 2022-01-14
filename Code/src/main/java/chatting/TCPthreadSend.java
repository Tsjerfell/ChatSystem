package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Interface.Visuel;
import main.Main;

public class TCPthreadSend extends Thread implements TCPThread{
	public int port;
	public PrintWriter output;
	public boolean convDone = false;
	
	public TCPthreadSend(int port) {
		this.port = port;
	}
	public void sendMessage(String message) {
		this.output.println(message);
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
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
				} else {
					Visuel.WriteHistoryField(messageReceived);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
