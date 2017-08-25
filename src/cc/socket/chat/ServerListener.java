package cc.socket.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * 服务端监听
 * 
 * @author 邹峰立
 */
public class ServerListener extends Thread {

	@Override
	public void run() {
		try {
			// 1、创建ServerScoket，设置端口
			ServerSocket serverSocket = new ServerSocket(12345);
			while (true) {
				// 2、accept方法将导致程序阻塞
				Socket socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null, "有客户端连接12345端口");
				// 3、将socket传递给新线程
				new ChatSocket(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
