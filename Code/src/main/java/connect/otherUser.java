package connect;

import java.net.InetAddress;

public class otherUser {
	
	public int addressMac;
	public String pseudo;
	public InetAddress addressIP;
	
	public otherUser(int addressMac, String pseudo, InetAddress addressIP) {
		this.addressMac = addressMac;
		this.pseudo = pseudo;
		this.addressIP = addressIP;
	}
	
	public void setPseudo(String newPseudo) {
		this.pseudo = newPseudo;
	}
}
