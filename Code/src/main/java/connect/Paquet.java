package connect;

import java.io.Serializable;

public class Paquet implements Serializable {

		public TypedePaquet type; 
		public int adressMac;
		public String pseudo;
		public String contenu; //message 
		
		//public byte[] transformeEnByte;
		
		public Paquet(TypedePaquet type, int addressMac, String Pseudo, String Contenu) {
			this.type = type;
			this.adressMac = addressMac;
			this.pseudo = Pseudo;
			this.contenu = Contenu;			
		}
		
		
		//public int length(){			
		//	return (type.values().d) ;
		//}
}
