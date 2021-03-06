package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ThreadReceiveUDPMulitcast extends Thread {  
	 
	  public ThreadReceiveUDPMulitcast () {   
	  } 
	  
	public void run() {  
	    while(true) {
	    	InetAddress group = null;
			try {
				group = InetAddress.getByName("225.6.7.8");
				MulticastSocket mSocket = new MulticastSocket(12345);		   
				mSocket.joinGroup(group);
				
				//on n'est pas sûr 100 suffit, ca suffisait pas, mais 2000 peut etre?
				byte[] buffer = new byte[2000];
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				mSocket.receive(packet);
				new ThreadTraitementPaquet(packet).start();
				
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }  
	  }  
}