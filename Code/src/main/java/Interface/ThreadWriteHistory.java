package Interface;

public class ThreadWriteHistory implements Runnable{
	String text;
	
	public ThreadWriteHistory(String text){
		this.text = text;
	}
	public void run() {
		Visuel.textHistory.setText("");
		Visuel.textHistory.append(text);
	}
}

