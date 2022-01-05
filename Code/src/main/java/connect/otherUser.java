package connect;

import java.net.InetAddress;

public class otherUser {
	
	public String pseudo;
	public InetAddress addressIP;
	
	public otherUser(String pseudo, InetAddress addressIP) {
		this.pseudo = pseudo;
		this.addressIP = addressIP;
	}
	
	public void setPseudo(String newPseudo) {
		this.pseudo = newPseudo;
	}
}
