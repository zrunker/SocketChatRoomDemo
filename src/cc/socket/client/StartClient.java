package cc.socket.client;

import java.awt.EventQueue;

public class StartClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow frame = new MainWindow();
				frame.setVisible(true);
				ChatManager.getInstance().setMainWindow(frame);
			}
		});
	}
}
