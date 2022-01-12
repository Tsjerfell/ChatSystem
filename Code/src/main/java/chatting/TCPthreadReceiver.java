package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPthreadReceiver extends Thread implements TCPThread{
	
	public int port;
	public String AddIP;
	
	public void sendMessage(String Message) {
		System.out.println("Not yet implemented");
	} 
	
		public TCPthreadReceiver(int port,String AddIP) {
			this.port = port;
			this.AddIP = AddIP;
		}
		
		public void run() {
			try {
				Socket socket = new Socket(this.AddIP,this.port);
				
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				output.println("Hello! How are you?");
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
