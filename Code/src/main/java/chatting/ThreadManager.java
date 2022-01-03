package chatting;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadManager extends Thread{		
	public ThreadManager(){}
	
	public void run() {
		
		while(true) {
	    	
			try {
				
				ServerSocket serSoc = new ServerSocket(12347);
				Socket socket = serSoc.accept();
												
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String IP = input.readLine();
				String portFormatString = input.readLine();
				int portFormatInt = Integer.parseInt(portFormatString);
				
				new TCPthread(portFormatInt, IP).start();
				
				serSoc.close();
				socket.close();
				
			}catch(Exception e){System.out.println(e);}  
			
			
		}
	}
}
