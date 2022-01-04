package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.lang3.SerializationUtils;

import connect.Paquet;
import main.Main;



public class ThreadTraitementPaquet extends Thread{
	
	public DatagramPacket packet;
	
	public ThreadTraitementPaquet(DatagramPacket packet) {
		this.packet = packet;
	}
	
	public void run() {
		SerializationUtils SerializationUtils = new SerializationUtils();
		byte[] buffer = packet.getData();
    	Paquet paquetDeserialiseReceived = SerializationUtils.deserialize(buffer);
    	
    	
    	if (paquetDeserialiseReceived.pseudo.equals(Main.pseudo)) {
    		//On ne fait rien parce qu'on vient de recevoir notre propre paquet
    	} else if(paquetDeserialiseReceived.type ==  TypedePaquet.Connexion) {
    		
	    	System.out.println("J'ai reçu: Je viens de me connecter et je m'appele " + paquetDeserialiseReceived.pseudo + " et mon address ip est " + paquetDeserialiseReceived.contenu);
	    	
	    	//ajoute du nouveau User
	    	otherUser NewUser = new otherUser(paquetDeserialiseReceived.adressMac,paquetDeserialiseReceived.pseudo,packet.getAddress());
	    	Main.addNewUser(NewUser);
	    	System.out.println("Ce qui sont connecté:");
	    	for (int i = 0; i < Main.listOtherConnectedUsers.size();i++) {
	    		System.out.println(Main.listOtherConnectedUsers.get(i).pseudo);
	    	}
	    	
	    	//construction du paquet de response
	    	
	    	Paquet PaquetResponse = new Paquet(TypedePaquet.AckConnexion, Main.addressMac, Main.pseudo,Main.addressIP);
	    	byte[] bufferResponse = SerializationUtils.serialize(PaquetResponse);
	    	DatagramPacket datagramPaquetDeResponse = new DatagramPacket(bufferResponse, bufferResponse.length, packet.getAddress(),12346);
	    	//System.out.println(packet.getAddress());
	    	try {
				DatagramSocket socket = new DatagramSocket();								
				socket.send(datagramPaquetDeResponse);
				socket.close();
				//System.out.print("paquet envoyé à " + packet.getAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    		    	
	    } else if (paquetDeserialiseReceived.type == TypedePaquet.AckConnexion) {
	    	//System.out.println(paquetDeserialiseReceived.type);
	    	System.out.println("J'ai reçu un paquet de " + paquetDeserialiseReceived.pseudo);
	    	
	    	
	    	otherUser NewUser = new otherUser(paquetDeserialiseReceived.adressMac,paquetDeserialiseReceived.pseudo,packet.getAddress());
	    	Main.addNewUser(NewUser);
	    	System.out.println("Ce qui sont connecté:");
	    	for (int i = 0; i < Main.listOtherConnectedUsers.size();i++) {
	    		System.out.println(Main.listOtherConnectedUsers.get(i).pseudo);
	    	}
	    	
	    	
	    } else if (paquetDeserialiseReceived.type ==  TypedePaquet.ChangementdePseudo) {
	    	System.out.print("Changement de paquet,mais pas traité");
	    	
	    } else {
	    	System.out.print("On n'a pas traité ce cas");
	    }
	
	}    
}
	
	