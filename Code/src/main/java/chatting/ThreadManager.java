package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadManager extends Thread{		
	public ThreadManager(){}
	
	public void run() {
		
		ServerSocket serSoc = null;
		try {
			serSoc = new ServerSocket(12347);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Socket socket = null;
		while(true) {
	    	
			try {
				
				socket = serSoc.accept();
												
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String IP = input.readLine();
				System.out.println("Received IP =" +IP.substring(1));
				String portFormatString = input.readLine();
				System.out.println("Received port =" +portFormatString);
				int portFormatInt = Integer.parseInt(portFormatString);
				
				new TCPthreadReceiver(portFormatInt, IP.substring(1)).start();
				
				socket.close();
				
			}catch(Exception e){System.out.println(e);}  
			
			
			
		}
	}
}
