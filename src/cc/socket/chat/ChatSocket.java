package cc.socket.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * ����˽��յ���Socket
 * 
 * @author �޷���
 */
public class ChatSocket extends Thread {
	private Socket socket;

	public ChatSocket(Socket socket) {
		this.socket = socket;
	}

	// ����˴�ֵ���ͻ���
	public void out(String out) {
		try {
			// ��ȡ��ǰSocket�����
			socket.getOutputStream().write(out.getBytes("gbk"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			// ���ֵ
			int count = 0;
			while (socket != null && socket.isConnected() && !socket.isClosed()) {
				out("����˴�ֵ��" + count++);
				sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
