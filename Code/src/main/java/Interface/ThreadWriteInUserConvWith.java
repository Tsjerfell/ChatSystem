package Interface;

import chatting.otherUserTalkingTo;
import connect.otherUser;
import main.Main;

public class ThreadWriteInUserConvWith implements Runnable{

	public void updateConnectedUsers() {
		Visuel.textUsersHavingConnexionWith.setText("");		
		
		for(otherUserTalkingTo otherUser : Main.listotherUserTalkingTo){
			Visuel.textUsersHavingConnexionWith.append(otherUser.pseudo +"\n");
		}
		
	}
		public void run() {
			updateConnectedUsers();
		}
}
