package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Interface.Visuel;
import connect.otherUser;
import main.Main;

public class ThreadManagerSender extends Thread{
		public otherUser otherUser;
		
		public ThreadManagerSender(otherUser otherUser) {
			this.otherUser = otherUser;
		}
		
		public void run() { 
						
			TCPthreadSend thread = new TCPthreadSend(Main.prochainPort, (otherUser.addressIP).toString().substring(1));
			thread.start();
			
			otherUserTalkingTo oUTT= new otherUserTalkingTo((otherUser.addressIP).toString().substring(1),thread,otherUser.pseudo);
			Main.listotherUserTalkingTo.add(oUTT);
			Visuel.currentTalkingWith = oUTT;
			Visuel.updateUserHavingConvWith();
			
			for (otherUserTalkingTo otheruser : Main.listotherUserTalkingTo) {
				System.out.println(otheruser.pseudo);
			}
			Socket socket;
			try {
				socket = new Socket(this.otherUser.addressIP,12347);
				
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				output.println(Main.addressIP);
				output.println(String.valueOf(Main.prochainPort));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Main.augmentePortNumber();
			
		}
		
}
