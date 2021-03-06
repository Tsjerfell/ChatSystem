package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.lang3.SerializationUtils;

import Interface.Visuel;
import chatting.otherUserTalkingTo;
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
		
    	//Pour veifier dans le if
    	String addressIPEnString = String.valueOf(packet.getAddress());
    	
    	if (addressIPEnString.equals(Main.addressIP) || paquetDeserialiseReceived.pseudo.equals(Main.pseudo)) {
    		//On ne fait rien parce qu'on vient de recevoir notre propre paquet
    	} else if(paquetDeserialiseReceived.type ==  TypedePaquet.Connexion) {
    		
	    	System.out.println(paquetDeserialiseReceived.pseudo + " vient de se connecter");
	    	//ajoute du nouveau User
	    	otherUser NewUser = new otherUser(paquetDeserialiseReceived.pseudo,packet.getAddress());
	    	Main.addNewUser(NewUser);
	    	Visuel.updateConnectedUsers();
	    	
	    	//construction du paquet de response
	    	
	    	Paquet PaquetResponse = new Paquet(TypedePaquet.AckConnexion, Main.pseudo,Main.addressIP);
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
	    	//System.out.println(paquetDeserialiseReceived.type);;
	    	
	    	otherUser NewUser = new otherUser(paquetDeserialiseReceived.pseudo,packet.getAddress());
	    	Main.addNewUser(NewUser);
	    	Visuel.updateConnectedUsers();
	    		    	
	    } else if (paquetDeserialiseReceived.type == TypedePaquet.ChangementdePseudo) {
	    	System.out.println(paquetDeserialiseReceived.pseudo + " a changé son nom à " + paquetDeserialiseReceived.contenu);
    		Main.changepseudoOtherUser(paquetDeserialiseReceived.pseudo, paquetDeserialiseReceived.contenu, packet.getAddress());  
    		Visuel.updateConnectedUsers();
    		Visuel.updateUserHavingConvWith();
	    } else if (paquetDeserialiseReceived.type == TypedePaquet.Deconnexion){
	    	System.out.println("Received deconnexion");
	    	int i = 0;
			for (otherUser otherUser : Main.listOtherConnectedUsers) {
				if (otherUser.addressIP.equals(packet.getAddress())) {
					i = Main.listOtherConnectedUsers.indexOf(otherUser);
				}
			}
			Main.listOtherConnectedUsers.remove(i);
			
			otherUserTalkingTo potenitialyTalkingTo = null;
			for (otherUserTalkingTo otheruser : Main.listotherUserTalkingTo) {
				if (otheruser.addIP.equals(packet.getAddress().toString().substring(1))) {
					potenitialyTalkingTo = otheruser;
					
				}
			}
			
			if (potenitialyTalkingTo == null) {
				//on n'a pas trouvé donc on ne lui parle pas
			} else {
				potenitialyTalkingTo.thread.endConversation();
				Main.listotherUserTalkingTo.remove(potenitialyTalkingTo);
				Visuel.updateUserHavingConvWith();
			}
			
			if (potenitialyTalkingTo == Visuel.currentTalkingWith) {
				Visuel.WriteHistoryField("You currently have no other conversations ");
			}
	    	//otherUser onvaEnlever = new otherUser(paquetDeserialiseReceived.pseudo,packet.getAddress());
	    	//Main.listOtherConnectedUsers.remove(onvaEnlever);
	    	
	    	System.out.println(paquetDeserialiseReceived.pseudo +" vient de se deconnecter");
	    	Visuel.updateConnectedUsers();
			
	    } else { //Ca vient pas de nous 
	    	System.out.print("C'est qoui ce paquet?");
	    }
	
	}    
}
	
	