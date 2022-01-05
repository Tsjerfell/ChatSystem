package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import connect.otherUser;
import main.Main;

public class ThreadManagerSender{
		
	
		public void startConversation() throws UnknownHostException, IOException { //Ajoute comme paramettre otherUser otherUser
						
			new TCPthreadSend(Main.prochainPort).start();
			
			Socket socket =new Socket("10.1.5.11",12347); //Change l'addresseIP en otherUSer.addresseIP
			BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			output.println(Main.addressIP);
			output.println(String.valueOf(Main.prochainPort));
			
			Main.augmentePortNumber();
		}
		
		public static void main (String[] args) throws UnknownHostException, IOException {
			ThreadManagerSender test1 = new ThreadManagerSender();
			//test1.startConversation();			
		}
}
