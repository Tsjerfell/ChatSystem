package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;

public class ThreadProperIPAddress extends Thread {
	
	public ThreadProperIPAddress() {}

	public void run() {
		try {
			DatagramSocket Socket = new DatagramSocket(12346);
			byte[] buffer = new byte[20000];
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			
			Socket.receive(packet);
			
			new ThreadTraitementPaquet(packet).start();
		}catch (UnknownHostException Exception) {			
			Exception.printStackTrace();
			
		} catch (IOException Exception) {			
			Exception.printStackTrace();
		}
	
	}
}
