package getMACaddress;

import java.net.*;

public class Essaie1 {
	public Essaie1(){
		
	}
	
	public static void main(String[] args) {
		try {
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte[] mac = network.getHardwareAddress();
		
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < mac.length; i++) {
	        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	      }
	      System.out.println(sb.toString());
		
		} catch (Exception e) {e.printStackTrace();}
		
	}
}
