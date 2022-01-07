package main;

import org.apache.commons.lang3.SerializationUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress; //on va l'utiliser pour Mac jcrois
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import connect.otherUser;
import connect.ThreadReceiveUDPMulitcast;
import connect.Paquet;
import connect.ThreadProperIPAddress;
import connect.TypedePaquet;

public class User {
	public String addressIP;
	public int addressMac;
	public String pseudo;
	MulticastSocket mSocket;

	
	public User() {	 
	}
		
	public void connect() {		
		try {
			
		    InetAddress group = InetAddress.getByName("225.6.7.8");
		    MulticastSocket socket = new MulticastSocket();
		    new ThreadReceiveUDPMulitcast().start();
		    
		    Paquet paquetNonSerialise = new Paquet(TypedePaquet.Connexion,Main.pseudo,Main.addressIP);
		    SerializationUtils SerializationUtils = new SerializationUtils(); 		    
		    byte[] paquetSerialise = SerializationUtils.serialize(paquetNonSerialise);
		    
		    DatagramPacket datagramPacket = new DatagramPacket(paquetSerialise,paquetSerialise.length, group, 12345);
			socket.send(datagramPacket);
			
			ThreadProperIPAddress ThreadOwnIP = new ThreadProperIPAddress();
			ThreadOwnIP.start();
			
			socket.close(); //Might bug
							
		} catch (Exception e) {e.printStackTrace();}
    	
	}
	
	public void changementPseudo(String nouveauPseudo) throws UnknownHostException {
		boolean AutreAvecMemeNom = false;
		
		for (otherUser otherUser : Main.listOtherConnectedUsers) {
			if (otherUser.pseudo.equalsIgnoreCase(nouveauPseudo)) {
				AutreAvecMemeNom = true;
			}
		}
		if (AutreAvecMemeNom) {
			System.out.println(nouveauPseudo +"est déjà utilisé par un autre uilisateur");
		} else {
		
			try {
				InetAddress group = InetAddress.getByName("225.6.7.8");
				MulticastSocket socket = new MulticastSocket();
				SerializationUtils SerializationUtils = new SerializationUtils(); 	
			
				Paquet paquetNonSerialise = new Paquet(TypedePaquet.ChangementdePseudo,Main.pseudo,nouveauPseudo);
				byte[] paquetSerialise = SerializationUtils.serialize(paquetNonSerialise);
				DatagramPacket datagramPacket = new DatagramPacket(paquetSerialise,paquetSerialise.length, group, 12345);
			
				socket.send(datagramPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.pseudo = nouveauPseudo;
		}
	}
	
	public void deconnect() {
	    try {
	    	InetAddress group = InetAddress.getByName("225.6.7.8");
			MulticastSocket socket = new MulticastSocket();
			SerializationUtils SerializationUtils = new SerializationUtils(); 	
			
			Paquet paquetNonSerialise = new Paquet(TypedePaquet.Deconnexion,Main.pseudo,Main.addressIP);
			byte[] paquetSerialise = SerializationUtils.serialize(paquetNonSerialise);
			DatagramPacket datagramPacket = new DatagramPacket(paquetSerialise,paquetSerialise.length, group, 12345);
			
			socket.send(datagramPacket);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}