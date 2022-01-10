package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ThreadProperIPAddress extends Thread {
	
	DatagramSocket Socket;
	
	public ThreadProperIPAddress() {
		
	}

	public void run() {
		try {
			this.Socket = new DatagramSocket(12346);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			try {
				
				byte[] buffer = new byte[20000];
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			
				this.Socket.receive(packet);
			
				new ThreadTraitementPaquet(packet).start();
			}catch (UnknownHostException Exception) {			
				Exception.printStackTrace();
			
			} catch (IOException Exception) {			
				Exception.printStackTrace();
			}
		}
	
	}
}
