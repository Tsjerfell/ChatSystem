package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPthread extends Thread{
	
	public int port;
	public String AddIP;
	
		public TCPthread(int port,String AddIP) {
			this.port = port;
			this.AddIP = AddIP;
		}
		
		public void run() {
			try {
				Socket socket = new Socket(AddIP,port);
				
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
