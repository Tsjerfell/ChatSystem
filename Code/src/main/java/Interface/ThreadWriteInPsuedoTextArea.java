package Interface;

public class ThreadWriteInPsuedoTextArea implements Runnable{
		String text;
		
		public ThreadWriteInPsuedoTextArea(String text){
			this.text = text;
		}
		public void run() {
			Visuel.textChangementDePseudo.setText(this.text);
		}
}
