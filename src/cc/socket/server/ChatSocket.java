package cc.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
			if (socket.isConnected() && !socket.isClosed()) {
				// ��ȡ��ǰSocket��������������
				socket.getOutputStream().write(out.getBytes("gbk"));
				System.out.println("ת������**********" + out);
			} else {
				// �����ѹر�
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
				// ���ܿͻ�������
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream(), "gbk"));
				// ��ȡ����
				String line = null;
				while ((line = br.readLine()) != null) {
					// ת������
					ChatManager.getInstance().publish(this, line);
					System.out.println("��������******" + line);
				}
				br.close();
			} else {
				// �����ѹر�
				ChatManager.getInstance().remove(this);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
