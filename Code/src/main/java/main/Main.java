package main;

import java.util.ArrayList;

import connect.otherUser;

public class Main {
	
	 public static ArrayList<otherUser> listOtherConnectedUsers;
	
	public static void main (String[] args) {
		System.setProperty("java.net.preferIPv4Stack","true");
		User User1 = new User(1234,"Hoplahopla");
		User1.connect();
		
	}
}
