package chatting;

public class ThreadManagerSender{
		public int prochainPort = 12348;
	
		public void startConversation() {
			new TCPthreadSend(prochainPort);
			prochainPort++;
		}
		
		public static void main (String[] args) {
			ThreadManagerSender test1 = new ThreadManagerSender();
			test1.startConversation();			
		}
}
