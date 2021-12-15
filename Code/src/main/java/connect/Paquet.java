package connect;

import java.io.Serializable;

public class Paquet implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public TypedePaquet type; 
		public int adressMac;
		public String pseudo;
		public String contenu; //message 
		
		public Paquet(TypedePaquet type, int addressMac, String Pseudo, String Contenu) {
			this.type = type;
			this.adressMac = addressMac;
			this.pseudo = Pseudo;
			this.contenu = Contenu;			
		}
		

}
