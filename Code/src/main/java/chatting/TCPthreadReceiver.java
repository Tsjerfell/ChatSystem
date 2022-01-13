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
	
	public void sendMessage(String message) {
		this.output.println(message);
	} 
	
		public TCPthreadReceiver(int port,String AddIP) {
			this.port = port;
			this.AddIP = AddIP;
		}
		
		public void run() {
			try {
				Socket socket = new Socket(this.AddIP,this.port);
				
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.output = new PrintWriter(socket.getOutputStream(), true);
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String messageReceived = "";
				while(true) {
					messageReceived = input.readLine();
					if (messageReceived == null) {
						//on a rien reçu, donc on ne fait rien
					} else {
						Visuel.addToHistoryField(messageReceived);
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
