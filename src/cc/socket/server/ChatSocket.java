package cc.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
			if (socket.isConnected() && !socket.isClosed()) {
				// 获取当前Socket输出流，输出数据
				socket.getOutputStream().write(out.getBytes("gbk"));
				System.out.println("转发数据**********" + out);
			} else {
				// 链接已关闭
				ChatManager.getInstance().remove(this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			if (socket.isConnected() && !socket.isClosed()) {
				// 接受客户端数据
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream(), "gbk"));
				// 读取数据
				String line = null;
				while ((line = br.readLine()) != null) {
					// 转发数据
					ChatManager.getInstance().publish(this, line);
					System.out.println("接受数据******" + line);
				}
				br.close();
			} else {
				// 链接已关闭
				ChatManager.getInstance().remove(this);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
