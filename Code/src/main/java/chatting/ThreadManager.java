package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Interface.Visuel;
import connect.otherUser;
import main.Main;

public class ThreadManager extends Thread{		
	public ThreadManager(){}
	
	public void run() {
		
		ServerSocket serSoc = null;
		try {
			serSoc = new ServerSocket(12347);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Socket socket = null;
		while(true) {
	    	
			try {
				
				socket = serSoc.accept();
												
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String IP = input.readLine();
				
				String portFormatString = input.readLine();
				
				int portFormatInt = Integer.parseInt(portFormatString);
				
				TCPthreadReceiver thread= new TCPthreadReceiver(portFormatInt, IP.substring(1));
				thread.start();
				String pseudoFound = "";
				for (otherUser otheruser : Main.listOtherConnectedUsers) {
					if (otheruser.addressIP.toString().substring(1).equals(IP.substring(1))){
						pseudoFound = otheruser.pseudo;
					}
				}
				otherUserTalkingTo oUTT= new otherUserTalkingTo(IP.substring(1),thread,pseudoFound);
				Main.listotherUserTalkingTo.add(oUTT);
				Visuel.currentTalkingWith = oUTT;
				Visuel.updateUserHavingConvWith();

				for (otherUserTalkingTo otheruser : Main.listotherUserTalkingTo) {
					System.out.println(otheruser.pseudo);
				}
				socket.close();
				
			}catch(Exception e){System.out.println(e);}  
			
			
			
		}
	}
}
