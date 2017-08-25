package cc.socket.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * 服务端接收到的Socket
 * 
 * @author 邹峰立
 */
public class ChatSocket extends Thread {
	private Socket socket;

	public ChatSocket(Socket socket) {
		this.socket = socket;
	}

	// 服务端传值给客户端
	public void out(String out) {
		try {
			// 获取当前Socket输出流
			socket.getOutputStream().write(out.getBytes("gbk"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			// 输出值
			int count = 0;
			while (socket != null && socket.isConnected() && !socket.isClosed()) {
				out("服务端传值：" + count++);
				sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
