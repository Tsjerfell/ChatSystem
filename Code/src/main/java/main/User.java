package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
//on va l'utiliser pour Mac jcrois
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import connect.otherUser; 
import connect.Paquet;
import connect.TypedePaquet;
//ArrayList<otherUser> listOtherUsers = new ArrayList<otherUser>(); 



public class User {
	int addressMac;
	String pseudo;
	//otherUser[] listOtherUsers;
	
	public User(int addressMac, String psuedo 	) {	
		this.addressMac = addressMac;
		this.psdo = psuedo;
	}
		
	public static void connect() {		
		try {
		    InetAddress group = InetAddress.getByName("225.6.7.8");
		    MulticastSocket socket = new MulticastSocket();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    Paquet paquet = new Paquet(TypedePaquet.Connexion, );
		    
		    DatagramPacket datagramPacket = new DatagramPacket(paquet,paquet.length(), group, 3456);
			socket.send(datagramPacket);	    
		    socket.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public int recevoirPaquet(Paquet paquet) {
		if (paquet.type == TypedePaquet.Message ) {
			return 0;
		} else if (paquet.type == TypedePaquet.Connexion){
			
		}
		
		return 0;
	}
	
	public void addOtherUser(String psuedo, int AdresseMacc) {
		
	};
	
	public static void main (String[] args) {
		System.setProperty("java.net.preferIPv4Stack","true");
		this.Connect();	
	}
}