package main;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

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
		//int i = listOtherConnectedUsers.indexOf(new otherUser(oldPseudo, addressIP));
		//listOtherConnectedUsers.get(i).setPseudo(newPseudo);
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
		Main newMain = new Main("ErikVenstre"); //l'addressMac n'existe plus
		
		InetAddress addr1 =  InetAddress.getByName("127.0.0.1");
		otherUser otherUser1 = new otherUser("User1",addr1);
		Main.listOtherConnectedUsers.add(otherUser1);				
		
		JFrame Frame = new JFrame();		
		Visuel interface1 = new Visuel(Frame);
		interface1.run();
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		InetAddress addr2 =  InetAddress.getByName("127.0.0.1");
		otherUser otherUser2 = new otherUser("User2",addr2);
		Main.listOtherConnectedUsers.add(otherUser2);
		interface1.updateConnectedUsers();
		
		User User1 = new User();
		User1.connect();		
		

		
		for (otherUser otherUser : Main.listOtherConnectedUsers)
		{System.out.println(otherUser.pseudo);}
		
		interface1.setVisible(true);
		System.out.print("Fini");
		
		
	
	}
}
