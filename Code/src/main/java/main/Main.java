package main;

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
	
	public Main() {
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
		User User1 = new User(1234,"Hoplahopla");
		User1.connect();
		
	}
}
