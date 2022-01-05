package connect;

import java.io.Serializable;

public class Paquet implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public TypedePaquet type; 
		public String pseudo;
		public String contenu; //message 
		
		public Paquet(TypedePaquet type, String Pseudo, String Contenu) {
			this.type = type;
			this.pseudo = Pseudo;
			this.contenu = Contenu;			
		}
		

}
