package cc.socket.server;

import java.util.Vector;

/**
 * ChatSocket管理类
 * 
 * @author 邹峰立
 */
public class ChatManager {

	private static ChatManager cm;
	
	// 单例
	public static ChatManager getInstance() {
		if (cm == null) {
			cm = new ChatManager();
		}
		return cm;
	}
	
	// ChatSocket集合
	Vector<ChatSocket> vector = new Vector<>();
	
	// 添加ChatSocket
	public void add(ChatSocket chatSocket) {
		vector.add(chatSocket);
	}
	
	// 移除ChatSocket
	public void remove(ChatSocket chatSocket) {
		vector.remove(chatSocket);
	}
	
	// 发送消息
	public void publish(ChatSocket cs, String out) {
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket chatSocket = vector.get(i);
			if (!cs.equals(chatSocket)) {
				chatSocket.out(out);
			}
		}
	}
}
