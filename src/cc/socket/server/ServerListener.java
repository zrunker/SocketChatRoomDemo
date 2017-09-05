package cc.socket.server;

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
				JOptionPane.showMessageDialog(null, "有客户端连接到本机的12345端口");
				// 3、将socket传递给新线程
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				// 4、使用Chatmanager进行管理
				ChatManager.getInstance().add(cs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
