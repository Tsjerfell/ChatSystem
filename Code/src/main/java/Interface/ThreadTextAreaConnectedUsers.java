package Interface;

import java.util.Iterator;

import connect.otherUser;
import main.Main;

public class ThreadTextAreaConnectedUsers implements Runnable{
	private static Object mutexConnectedUsers = new Object();
	
	
	public void updateConnectedUsers() {
		synchronized(ThreadTextAreaConnectedUsers.mutexConnectedUsers) {
		
				Visuel.textAreaConnectedUsers.setText("");		
		

				Iterator<otherUser> itr=Main.listOtherConnectedUsers.iterator();
				while(itr.hasNext()) {
					otherUser otherUser=itr.next();
					Visuel.textAreaConnectedUsers.append(otherUser.pseudo +"\n");
				}
			}
		}
		public void run() {
			updateConnectedUsers();
		}
}
