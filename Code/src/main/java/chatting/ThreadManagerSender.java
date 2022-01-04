package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadManagerSender{
		public int prochainPort = 12348;
	
		public void startConversation() throws UnknownHostException, IOException {
						
			new TCPthreadSend(prochainPort).start();
			
			Socket socket =new Socket("10.1.5.11",12347); 
			BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			output.println("10.1.5.12");
			output.println(String.valueOf(prochainPort));
			
			prochainPort++;
		}
		
		public static void main (String[] args) throws UnknownHostException, IOException {
			ThreadManagerSender test1 = new ThreadManagerSender();
			test1.startConversation();			
		}
}
