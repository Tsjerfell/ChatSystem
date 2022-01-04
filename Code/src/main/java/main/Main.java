package main;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import connect.otherUser;

public class Main {
	
	public static String pseudo;
	public static int addressMac;
	public static String addressIP;
	public static ArrayList<otherUser> listOtherConnectedUsers = new ArrayList<otherUser>();
	
	public Main(String pseudo,int addressMac) throws SocketException, UnknownHostException {
		this.addressMac = addressMac;
		this.pseudo = pseudo;
		
	    try(final DatagramSocket socketForIP = new DatagramSocket()){
	    	  socketForIP.connect(InetAddress.getByName("8.8.8.8"), 10002);
	    	  this.addressIP = socketForIP.getLocalAddress().getHostAddress();
	    	} 
	}
	
	public static synchronized void addNewUser(otherUser theotherUser) {
			listOtherConnectedUsers.add(theotherUser);		
	}
	
	public static synchronized void changePsuedoOtherUser(int addressMac,String oldPseudo, String newPseudo,InetAddress addressIP) {
		int i = listOtherConnectedUsers.indexOf(new otherUser(addressMac,oldPseudo, addressIP));
		listOtherConnectedUsers.get(i).setPseudo(newPseudo);
	}
	
	public static void main (String[] args) throws UnknownHostException, SocketException {
		System.setProperty("java.net.preferIPv4Stack","true");
		Main newMain = new Main("Erik",1234); //l'addressMac n'a actuellement aucun importance
		
		User User1 = new User();
		User1.connect();
		
	}
}
