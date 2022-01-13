package chatting;

public class otherUserTalkingTo {
	public String addIP;
	public TCPThread thread;
	public String psuedo;
	
	public otherUserTalkingTo() {
	}
	
	public otherUserTalkingTo(String addIP,TCPThread thread,String psuedo) {
		this.addIP = addIP;
		this.thread = thread;
		this.psuedo = psuedo;
	}
}
