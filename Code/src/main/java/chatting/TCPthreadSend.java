package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.Main;

public class TCPthreadSend extends Thread implements TCPThread{
	public int port;
	public PrintWriter output;
	
	public TCPthreadSend(int port) {
		this.port = port;
	}
	public void sendMessage(String message) {
		this.output.println(message);
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
			
			output.println("Hello, how are you?");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
