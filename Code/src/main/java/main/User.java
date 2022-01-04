package main;

import org.apache.commons.lang3.SerializationUtils;
import java.io.BufferedReader;
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
import connect.Paquet;
import connect.ThreadProperIPAddress;
import connect.TypedePaquet;

public class User {
	public String addressIP;
	public int addressMac;
	public String pseudo;
	MulticastSocket mSocket;
	ArrayList<otherUser> listOtherUsers = new ArrayList<>();

	
	public User() {	 
	}
		
	public void connect() {		
		try {
			
		    InetAddress group = InetAddress.getByName("225.6.7.8");
		    MulticastSocket socket = new MulticastSocket();
		    new thread_receive().start();
		    
		    Paquet paquetNonSerialise = new Paquet(TypedePaquet.Connexion,Main.addressMac,Main.pseudo,Main.addressIP);
		    SerializationUtils SerializationUtils = new SerializationUtils(); 		    
		    byte[] paquetSerialise = SerializationUtils.serialize(paquetNonSerialise);
		    
		    DatagramPacket datagramPacket = new DatagramPacket(paquetSerialise,paquetSerialise.length, group, 12345);
			socket.send(datagramPacket);
			
			ThreadProperIPAddress ThreadOwnIP = new ThreadProperIPAddress();
			ThreadOwnIP.start();
					
			
			
			//Receive UDPs from all other users
			//byte[] buffer = new byte[100];
	    	//DatagramPacket paquetSerialiseReceived = new DatagramPacket(buffer,buffer.length);
	    	//socket.receive(paquetSerialiseReceived);

	    	//Paquet paquetDeserialiseReceived = SerializationUtils.deserialize(buffer);
	    	
	    	//System.out.println(paquetDeserialiseReceived.type.name() + paquetDeserialiseReceived.pseudo);

		} catch (Exception e) {e.printStackTrace();}
		
    	
	}

}