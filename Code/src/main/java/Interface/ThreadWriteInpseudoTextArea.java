package Interface;

public class ThreadWriteInpseudoTextArea implements Runnable{
		String text;
		
		public ThreadWriteInpseudoTextArea(String text){
			this.text = text;
		}
		public void run() {
			Visuel.textChangementDePseudo.setText(this.text);
		}
}
