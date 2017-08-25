package cc.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Scoket������
 * 
 * @author �޷���
 */
public class ChatManager {
	private static ChatManager cm;

	// ����
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
		mainWindow.appendText("�ı����Ѿ���ChatManager���ˡ�");
	}

	// ����
	public void connect(final String ip) {
		new Thread(){
			@Override
			public void run() {
				try {
					// ��������
					socket = new Socket(ip, 12345);
					writer = new PrintWriter(
							new OutputStreamWriter(
									socket.getOutputStream()));
					reader = new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("�ͻ��˽�������******" + line);
						mainWindow.appendText("��˵" + line);
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
	
	// ����
	public void send(String out) {
		if (writer != null) {
			writer.write(out + "\n");
			writer.flush();
		} else {
			mainWindow.appendText("��ǰ�����Ѿ��ж�");
		}
	}
	
}
