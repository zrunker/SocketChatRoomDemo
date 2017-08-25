package cc.socket.server;

import java.util.Vector;

/**
 * ChatSocket������
 * 
 * @author �޷���
 */
public class ChatManager {

	private static ChatManager cm;
	
	// ����
	public static ChatManager getInstance() {
		if (cm == null) {
			cm = new ChatManager();
		}
		return cm;
	}
	
	// ChatSocket����
	Vector<ChatSocket> vector = new Vector<>();
	
	// ���ChatSocket
	public void add(ChatSocket chatSocket) {
		vector.add(chatSocket);
	}
	
	// �Ƴ�ChatSocket
	public void remove(ChatSocket chatSocket) {
		vector.remove(chatSocket);
	}
	
	// ������Ϣ
	public void publish(ChatSocket cs, String out) {
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket chatSocket = vector.get(i);
			if (!cs.equals(chatSocket)) {
				chatSocket.out(out);
			}
		}
	}
}
