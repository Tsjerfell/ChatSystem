package Interface;

import connect.otherUser;
import main.Main;

public class ThreadTextAreaConnectedUsers implements Runnable{
	
	public void updateConnectedUsers() {
		Visuel.textAreaConnectedUsers.setText("");		
		
		for(otherUser otherUser : Main.listOtherConnectedUsers){
			Visuel.textAreaConnectedUsers.append(otherUser.pseudo +"\n");
		}
		
	}
		public void run() {
			updateConnectedUsers();
		}
}
