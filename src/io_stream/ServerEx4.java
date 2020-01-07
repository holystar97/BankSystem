package io_stream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerEx4 {
	private Vector handlers;
	public ServerEx4(int port) {
		try {
			ServerSocket server= new ServerSocket(port);
			handlers=new Vector();
			System.out.println("ServerEx4 is ready.");
			
			while(true) {
				Socket client=server.accept();
				ChatHandler c=new ChatHandler(this,client);
				c.start();
			}
			
		}catch(Exception e) {}
	}
	
	public Object getHandler(int index) {
		return handlers.elementAt(index); // Ư���� ����ϰ� �޽��� ���� �� 
	}
	
	public void register(ChatHandler c) {
		handlers.addElement(c);
	}
	
	public void unregister(Object o) {
		handlers.removeElement(o);
	}
	public void broadcast (String message) {
		synchronized(handlers) {
			int n=handlers.size();
			for (int i=0; i<n; i++) {
				ChatHandler c= (ChatHandler) handlers.elementAt(i);
				c.println(message);
			}
		}
	}
	
	public static void main(String[] args) {
		new ServerEx4(9830);
	}

}
