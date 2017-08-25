package cc.socket.server;


/**
 * 简易聊天室服务端
 * 
 * @author 邹峰立
 */
public class MyServerSocket {

	public static void main(String[] args) {
		// 开启服务端监听
		new ServerListener().start();
	}
}
