package cc.socket.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * ����˼���
 * 
 * @author �޷���
 */
public class ServerListener extends Thread {

	@Override
	public void run() {
		try {
			// 1������ServerScoket�����ö˿�
			ServerSocket serverSocket = new ServerSocket(12345);
			while (true) {
				// 2��accept���������³�������
				Socket socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null, "�пͻ�������12345�˿�");
				// 3����socket���ݸ����߳�
				new ChatSocket(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
