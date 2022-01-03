package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender {
	
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
	
    
    public void startConnversation(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.print("Ca marche?");
        
        
        
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.print(in.readLine());
    }
    
    public void sendMessage(String message) {
    	out.print("Ca marche?");
    }
    
    public static void main(String[] argv) throws IOException {
    	Sender sender = new Sender();
    	sender.startConnversation("10.1.5.81",12347);
    	
    	Sender sender2 = new Sender();
    	sender.startConnversation("10.1.5.81",12348);
    	sender2.sendMessage("Cela marche?");
    }
}
