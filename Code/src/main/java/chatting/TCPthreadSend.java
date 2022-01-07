package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.Main;

public class TCPthreadSend extends Thread{
	public int port;
	
	public TCPthreadSend(int port) {
		this.port = port;
	}
	
	public void run() {
		ServerSocket serSoc;
		try {
			System.out.println(port);
			System.out.println(Main.addressIP);
			serSoc = new ServerSocket(port);
			Socket soc = serSoc.accept();
			
			BufferedReader input  = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			PrintWriter output = new PrintWriter(soc.getOutputStream(), true);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(input.readLine());
			System.out.println(input.readLine());

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
