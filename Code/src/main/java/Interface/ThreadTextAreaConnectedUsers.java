package Interface;

import connect.otherUser;
import main.Main;

public class ThreadTextAreaConnectedUsers implements Runnable{
	
	public void updateConnectedUsers() {
		Visuel.textAreaconnectedUsers.setText("");
		
		for(otherUser otherUser : Main.listOtherConnectedUsers){
			Visuel.textAreaconnectedUsers.append(otherUser.pseudo +"\n");
		}
		
	}
	
		public void run() {
			updateConnectedUsers();
		}
}
