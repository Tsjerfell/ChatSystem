package chatting;

public class otherUserTalkingTo {
	public String addIP;
	public TCPThread thread;
	public String pseudo;
	
	public otherUserTalkingTo() {
	}
	
	public otherUserTalkingTo(String addIP,TCPThread thread,String pseudo) {
		this.addIP = addIP;
		this.thread = thread;
		this.pseudo = pseudo;
	}
}
