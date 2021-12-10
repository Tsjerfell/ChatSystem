package connect;

public class Paquet {

		public TypedePaquet type; 
		int adressMac;
		String pseudo;
		String contenu; //message 
		public byte[] transformeEnByte;
		
		public Paquet(TypedePaquet type, int addressMac, String Pseudo, String contenu) {
			this.type = type;
			this.adressMac = addressMac;
			this.pseudo = Pseudo;
			this.contenu = contenu;
			
		}
		void setTransformEnByte() {
			this.transformeEnByte = 
			
		}
		
		public int length(){
			
			return (type.values().d) ;
		}
}
