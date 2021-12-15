package main;

import org.apache.commons.lang3.SerializationUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress; //on va l'utiliser pour Mac jcrois
import java.net.MulticastSocket;
import java.util.ArrayList;
import connect.otherUser; 
import connect.Paquet;
import connect.TypedePaquet;
//ArrayList<otherUser> listOtherUsers = new ArrayList<otherUser>(); 



public class User {
	public int addressMac;
	public String pseudo;
	MulticastSocket mSocket;
	otherUser[] listOtherUsers;
	
	public User(int addressMac, String psuedo) {	
		this.addressMac = addressMac;
		this.pseudo = psuedo;
	}
		
	public void connect() {		
		try {
			thread_receive threadRecevoir = new thread_receive();
			
		    InetAddress group = InetAddress.getByName("225.6.7.8");
		    MulticastSocket socket = new MulticastSocket();
		    Paquet paquetNonSerialise = new Paquet(TypedePaquet.Connexion,addressMac,pseudo,"" );
		    SerializationUtils SerializationUtils = new SerializationUtils(); 		    
		    byte[] paquetSerialise = SerializationUtils.serialize(paquetNonSerialise);
		    
		    DatagramPacket datagramPacket = new DatagramPacket(paquetSerialise,paquetSerialise.length, group, 3456);
			socket.send(datagramPacket);
			
			//recieve UDPs from all other users
			byte[] buffer = new byte[100];
	    	DatagramPacket paquetSerialiseReceived = new DatagramPacket(buffer,buffer.length);
	    	socket.receive(paquetSerialiseReceived);

	    	Paquet paquetDeserialiseReceived = SerializationUtils.deserialize(buffer);
	    	
	    	System.out.println(paquetDeserialiseReceived.type.name() + paquetDeserialiseReceived.pseudo);
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
		User User1 = new User(1234,"Hoplahopla");
		User1.connect();
		
	}
}