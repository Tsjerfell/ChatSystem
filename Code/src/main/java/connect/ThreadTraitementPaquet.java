package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import org.apache.commons.lang3.SerializationUtils;

import connect.Paquet;



public class ThreadTraitementPaquet extends Thread{
	
	public DatagramPacket packet;
	
	public ThreadTraitementPaquet(DatagramPacket packet) {
		this.packet = packet;
	}
	
	public void run() {
		SerializationUtils SerializationUtils = new SerializationUtils();
		byte[] buffer = packet.getData();
    	Paquet paquetDeserialiseReceived = SerializationUtils.deserialize(buffer);
	
	    if(paquetDeserialiseReceived.type == TypedePaquet.Connexion ) {
	    	System.out.println("Je viens de me connecter");
	    	
	    } else {
	    	System.out.print("Det var ikke det jeg hapet pa");
	    }
	
	}    
	}
	
}
	