package chatting;

import java.io.PrintWriter;

public interface TCPThread {
	public void sendMessage(String message, boolean b);
	public void endConversation();
}
