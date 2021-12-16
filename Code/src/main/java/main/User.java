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
//ArrayList<otherUser> listOtherUsers = new ArrayList<otherUser>(); 



public class User {
	public String addressIP;
	public int addressMac;
	public String pseudo;
	MulticastSocket mSocket;

	
	public User(int addressMac, String psuedo) throws UnknownHostException, SocketException {	
		this.addressMac = addressMac;
		this.pseudo = psuedo;
		
	    try(final DatagramSocket socketForIP = new DatagramSocket()){
	    	  socketForIP.connect(InetAddress.getByName("8.8.8.8"), 10002);
	    	  this.addressIP = socketForIP.getLocalAddress().getHostAddress();
	    	} 
	}
		
	public void connect() {		
		try {
			ThreadProperIPAddress ThreadOwnIP = new ThreadProperIPAddress();
			ThreadOwnIP.start();
			
		    InetAddress group = InetAddress.getByName("225.6.7.8");
		    MulticastSocket socket = new MulticastSocket();
		    
		    Paquet paquetNonSerialise = new Paquet(TypedePaquet.Connexion,addressMac,pseudo,addressIP);
		    SerializationUtils SerializationUtils = new SerializationUtils(); 		    
		    byte[] paquetSerialise = SerializationUtils.serialize(paquetNonSerialise);
		    
		    DatagramPacket datagramPacket = new DatagramPacket(paquetSerialise,paquetSerialise.length, group, 3456);
			socket.send(datagramPacket);
			
			System.out.print(addressIP);
			new thread_receive().start();
			
			//Receive UDPs from all other users
			//byte[] buffer = new byte[100];
	    	//DatagramPacket paquetSerialiseReceived = new DatagramPacket(buffer,buffer.length);
	    	//socket.receive(paquetSerialiseReceived);

	    	//Paquet paquetDeserialiseReceived = SerializationUtils.deserialize(buffer);
	    	
	    	//System.out.println(paquetDeserialiseReceived.type.name() + paquetDeserialiseReceived.pseudo);
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

	public static void main (String[] args) throws UnknownHostException, SocketException {
		Main newMain = new Main();
		System.setProperty("java.net.preferIPv4Stack","true");
		User User1 = new User(1234,"Erik");
		User1.connect();
		
	}

}