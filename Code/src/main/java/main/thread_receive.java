package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

class thread_receive extends Thread {  
	 
	  public thread_receive () {   
	  } 
	  
	  public int recevoir() {
			System.out.println("oui");
			return 0;
		}
	  
	  @SuppressWarnings("resource")
	public void run() {  
	    while(true) {
	    	InetAddress group = null;
			try {
				group = InetAddress.getByName("225.6.7.8");
				MulticastSocket mSocket = null;			
				mSocket = new MulticastSocket(3456);		   
				mSocket.joinGroup(group);		
				//on n'est pas sûr 100 suffit
				byte[] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				mSocket.receive(packet);
				this.recevoir();
	    		Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
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