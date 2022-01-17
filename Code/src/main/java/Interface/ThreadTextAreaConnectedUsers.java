package Interface;

import connect.otherUser;
import main.Main;

public class ThreadTextAreaConnectedUsers implements Runnable{
	private static Object mutexConnectedUsers = new Object();
	
	
	public void updateConnectedUsers() {
		synchronized(ThreadTextAreaConnectedUsers.mutexConnectedUsers) {
		
				Visuel.textAreaConnectedUsers.setText("");		
		
				for(otherUser otherUser : Main.listOtherConnectedUsers){
					Visuel.textAreaConnectedUsers.append(otherUser.pseudo +"\n");
				}
			}
		}
		public void run() {
			updateConnectedUsers();
		}
}
