package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.lang3.SerializationUtils;

import Interface.ThreadTextAreaConnectedUsers;
import Interface.Visuel;
import chatting.ThreadManagerSender;
import chatting.otherUserTalkingTo;
import connect.Paquet;
import connect.TypedePaquet;
import connect.otherUser;
import databases.DBConv;

public class Main {
	
	public static String pseudo;
	public static String addressIP;
	public static ArrayList<otherUser> listOtherConnectedUsers = new ArrayList<otherUser>();
	public static ArrayList<otherUserTalkingTo> listotherUserTalkingTo = new ArrayList<otherUserTalkingTo>();
	//Pour chatting
	public static int prochainPort = 12348;
	public static DBConv DB1=new DBConv();
	
	public Main(String pseudo) throws SocketException, UnknownHostException {
		this.pseudo = pseudo;
		
	    try(final DatagramSocket socketForIP = new DatagramSocket()){
	    	  socketForIP.connect(InetAddress.getByName("8.8.8.8"), 10002);
	    	  this.addressIP = "/"+socketForIP.getLocalAddress().getHostAddress();
	    	} 
	}
	
	public static boolean changementPseudo(String nouveauPseudo) throws UnknownHostException {
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
		return AutreAvecMemeNom;
		
	}
	
	public static String findIp(String pseudo) {
        for (otherUserTalkingTo otherUserTalkingTo : Main.listotherUserTalkingTo) {
            if (otherUserTalkingTo.pseudo.equalsIgnoreCase(pseudo)) {
                return otherUserTalkingTo.addIP;
            }
        }
        return Main.addressIP;
    }
	
	public static String findPseudo(String IP) {
        for (otherUserTalkingTo otherUserTalkingTo : Main.listotherUserTalkingTo) {
            if (otherUserTalkingTo.addIP.equalsIgnoreCase(IP)) {
                return otherUserTalkingTo.pseudo;
            }
        }
        return Main.pseudo;
    }
	
	public static void augmentePortNumber() {
		prochainPort++;
	}
	
	public static synchronized void addNewUser(otherUser theotherUser) {
			listOtherConnectedUsers.add(theotherUser);		
	}
	
	public static synchronized void changepseudoOtherUser(String oldPseudo, String newPseudo,InetAddress addressIP) {
		
		for (otherUser otherUser : listOtherConnectedUsers) {
			if (otherUser.addressIP.equals(addressIP)) {
				otherUser.pseudo = newPseudo;
			}
		}
		
		for (otherUserTalkingTo otherUser : listotherUserTalkingTo) {
			if (otherUser.addIP.equals(addressIP.toString().substring(1))) {
				otherUser.pseudo = newPseudo;
			}
		}
		
	}
	public static void printUsers() { //Pour tester
		System.out.println("Ceux qui sont connecté:");
		for (otherUser otherUser : listOtherConnectedUsers) {			
				System.out.println("	"+otherUser.pseudo);
		}
	}
	
	public static void deconnect() {
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
	
	public static void main (String[] args) throws UnknownHostException, SocketException {
		System.setProperty("java.net.preferIPv4Stack","true");
		try(final DatagramSocket socketForIP = new DatagramSocket()){
	    	  socketForIP.connect(InetAddress.getByName("8.8.8.8"), 10002);
		Main newMain = new Main("/"+socketForIP.getLocalAddress().getHostAddress()); }//l'addressMac n'existe plus		
		User User1 = new User();
		
		
		 JFrame frame = new JFrame ("Visuel");
	        frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
	        frame.getContentPane().add (new Visuel());
	        frame.pack();
	        frame.setVisible (true);	
	     
	        User1.connect();
	}
}
