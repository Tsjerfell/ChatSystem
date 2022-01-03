package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {

		public Listener() {
			
		}
		
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(3458);
				Socket clientSocket = serverSocket.accept();
				
				BufferedReader input  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				System.out.println(input.readLine());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		public static void main(String[] argv) {
			
			
		}
}