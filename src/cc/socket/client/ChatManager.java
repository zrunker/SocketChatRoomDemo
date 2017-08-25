package cc.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Scoket管理类
 * 
 * @author 邹峰立
 */
public class ChatManager {
	private static ChatManager cm;

	// 单例
	public static synchronized ChatManager getInstance() {
		if (cm == null) {
			cm = new ChatManager();
		}
		return cm;
	}
	
	MainWindow mainWindow;
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		mainWindow.appendText("文本框已经和ChatManager绑定了。");
	}

	// 连接
	public void connect(final String ip) {
		new Thread(){
			@Override
			public void run() {
				try {
					// 建立连接
					socket = new Socket(ip, 12345);
					writer = new PrintWriter(
							new OutputStreamWriter(
									socket.getOutputStream()));
					reader = new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("客户端接受数据******" + line);
						mainWindow.appendText("他说" + line);
					}
					writer.close();
					reader.close();
					writer = null;
					reader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	// 发送
	public void send(String out) {
		if (writer != null) {
			writer.write(out + "\n");
			writer.flush();
		} else {
			mainWindow.appendText("当前连接已经中断");
		}
	}
	
}
