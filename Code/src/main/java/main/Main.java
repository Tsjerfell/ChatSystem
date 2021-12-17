package main;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import connect.otherUser;

public class Main {
	
	public static String pseudo;
	public static int addressMac;
	public static String addressIP;
	public static ArrayList<otherUser> listOtherConnectedUsers;
	
	public Main() {
	}
	
	public static synchronized void addNewUser(otherUser theotherUser) {
			listOtherConnectedUsers.add(theotherUser);		
	}
	public static void main (String[] args) throws UnknownHostException, SocketException {
		System.setProperty("java.net.preferIPv4Stack","true");
		User User1 = new User(1234,"Hoplahopla");
		User1.connect();
		
	}
}
