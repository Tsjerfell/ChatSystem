package test.Multicast;
import java.net.*;

public class testClient {

    public static void main (String[] args){
	System.setProperty("java.net.preferIpv4Stack","true");

	try {
	    InetAddress group = InetAddress.getByName("225.6.7.8");	    
	    MulticastSocket mSocket = new MulticastSocket(3456);
	    mSocket.joinGroup(group);

	    int i = 0;
	    while(i<10) {
	    	byte[] buffer = new byte[100];
	    	DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
	    	mSocket.receive(packet);
	    	System.out.println(new String(buffer));
	    	i++;
	    }
	    mSocket.close();
	    
	} catch (Exception e) {e.printStackTrace();}

    }
}
