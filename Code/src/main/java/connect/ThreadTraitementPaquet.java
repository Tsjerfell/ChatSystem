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
	
	    if(paquetDeserialiseReceived.type ==  TypedePaquet.Connexion) {
	    	System.out.println("Je viens de me connecter et je m'appele " + paquetDeserialiseReceived.pseudo + " et mon address ip est " + paquetDeserialiseReceived.contenu);
	    	
	    	//construction du paquet de response
	    	
	    	Paquet PaquetResponse = new Paquet(TypedePaquet.AckConnexion, Main.addressMac, Main.pseudo,Main.addressIP);
	    	byte[] bufferResponse = SerializationUtils.serialize(PaquetResponse);
	    	DatagramPacket datagramPaquetDeResponse = new DatagramPacket(bufferResponse, bufferResponse.length, packet.getSocketAddress());
	    	try {
				DatagramSocket socket = new DatagramSocket();
				
				socket.connect(packet.getSocketAddress());
				socket.send(datagramPaquetDeResponse);
				socket.close();
				System.out.print("paquet Envoie à " + packet.getSocketAddress());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		    	
	    } else if (paquetDeserialiseReceived.type ==  TypedePaquet.AckConnexion) {
	    	
	    	System.out.print("J'ai recois un paquet de " + paquetDeserialiseReceived.pseudo);
	    		    	
		} else {
	    	System.out.print("On n'a pas traité ce cas");
	    }
	
	}    
}
	
	