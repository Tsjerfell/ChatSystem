package test.Multicast;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class testServer {
    public static void main(String[] args) {
	System.setProperty("java.net.preferIPv4Stack","true");

	try {
	    InetAddress group = InetAddress.getByName("225.6.7.8");
	    MulticastSocket socket = new MulticastSocket(3456);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    String message = "INSA Toulouse";
	    for (int i = 0; i<10;i++){
	    	message = reader.readLine();
	    	DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(), group, 3456);
		    socket.send(packet);
	    }	    
	    socket.close();
	} catch (Exception e) {e.printStackTrace();}

    }
}
