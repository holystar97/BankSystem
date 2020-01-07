package io_stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatHandler extends Thread{

	private Socket s;
	private BufferedReader i;
	private PrintWriter o;
	private ServerEx4 server;
	
	public ChatHandler(ServerEx4 server,Socket s) throws IOException{
		this.s=s;
		this.server=server;
		InputStream ins=s.getInputStream();
		OutputStream os=s.getOutputStream();
		i=new BufferedReader(new InputStreamReader(ins));
		o=new PrintWriter(new OutputStreamWriter(os),true);
	
	}
	public void run() {
		String name="";
		try {
			name=i.readLine();
			server.register(this);
			broadcast(name+"���� �湮�ϼ̽��ϴ�.");
			while(true) {
				String msg=i.readLine();
				broadcast(name+"-"+msg);
			}
		}catch(IOException ex) {}
		server.unregister(this);
		broadcast(name+"���� �����̽��ϴ�.");
		
		try {
			i.close();
			o.close();
			s.close();
		}catch(IOException ex) {}
	}
	
	public void println(String message) {
		o.println(message);
	}
	
	protected void broadcast(String message) {
		server.broadcast(message);
	}
	
	
}
