package main;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

import Interface.ThreadTextAreaConnectedUsers;
import Interface.Visuel;
import chatting.ThreadManagerSender;
import connect.otherUser;

public class Main {
	
	public static String pseudo;
	public static String addressIP;
	public static ArrayList<otherUser> listOtherConnectedUsers = new ArrayList<otherUser>();
	
	//Pour chatting
	public static int prochainPort = 12348;
	
	public Main(String pseudo) throws SocketException, UnknownHostException {
		this.pseudo = pseudo;
		
	    try(final DatagramSocket socketForIP = new DatagramSocket()){
	    	  socketForIP.connect(InetAddress.getByName("8.8.8.8"), 10002);
	    	  this.addressIP = "/"+socketForIP.getLocalAddress().getHostAddress();
	    	} 
	}
	
	public static void augmentePortNumber() {
		prochainPort++;
	}
	
	public static synchronized void addNewUser(otherUser theotherUser) {
			listOtherConnectedUsers.add(theotherUser);		
	}
	
	public static synchronized void changePsuedoOtherUser(String oldPseudo, String newPseudo,InetAddress addressIP) {
		
		for (otherUser otherUser : listOtherConnectedUsers) {
			if (otherUser.addressIP.equals(addressIP)) {
				otherUser.pseudo = newPseudo;
			}
		}
		
	}
	public static void printUsers() {
		System.out.println("Ceux qui sont connecté:");
		for (otherUser otherUser : listOtherConnectedUsers) {			
				System.out.println("	"+otherUser.pseudo);
		}
	}
		
	public static void main (String[] args) throws UnknownHostException, SocketException {
		System.setProperty("java.net.preferIPv4Stack","true");
		Main newMain = new Main("Erik"); //l'addressMac n'existe plus		

		 JFrame frame = new JFrame ("Interfacetest2");
	        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add (new Visuel());
	        frame.pack();
	        frame.setVisible (true);

		User User1 = new User();
		User1.connect();		
	
	}
}
