package chatting;

public class otherUserTalkingTo {
	public String addIP;
	public TCPThread thread;
	
	public otherUserTalkingTo() {
	}
	
	public otherUserTalkingTo(String addIP,TCPThread thread) {
		this.addIP = addIP;
		this.thread = thread;
	}
}
