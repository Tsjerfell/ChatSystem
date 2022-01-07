package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import connect.otherUser;
import main.Main;

public class ThreadManagerSender extends Thread{
		public otherUser otherUser;
		
		public ThreadManagerSender(otherUser otherUser) {
			this.otherUser = otherUser;
		}
		
		public void run() { 
						
			new TCPthreadSend(Main.prochainPort).start();			
			
			Socket socket;
			try {
				socket = new Socket(this.otherUser.addressIP,12347);
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				output.println(Main.addressIP);
				output.println(String.valueOf(Main.prochainPort));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Main.augmentePortNumber();
			
		}
		
}
