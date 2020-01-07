
package io_stream;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class ClientEx4 extends JFrame implements Runnable,ActionListener{

	
	private JTextArea output;
	private JTextField input;
	private JLabel label;
	private BufferedReader i;
	private PrintWriter o;
	private Thread listener;
	private String host;
	JScrollBar jb;
	public ClientEx4(){}
	
	public ClientEx4(String server){
		super("ä�� ���α׷�");
		host=server;
		listener= new Thread(this);
		listener.start();
		output=new JTextArea();
		JScrollPane jp= new JScrollPane(output);
		//JScrollBar new JScrollPane(output).getVerticalScrollBar();
		jb=jp.getVerticalScrollBar();
		add (jp,"Center");
		
		
		
		//add(new JScrollPane(output),"Center");
		
		 //DefaultCaret caret = (DefaultCaret)output.getCaret();
		 //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		output.setEditable(false);
		Panel bottom=new Panel(new BorderLayout());
		label=new JLabel("������̸�");
		bottom.add(label,"West");
		input=new JTextField();
		bottom.add(input,"Center");
		add(bottom,"South");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,300);
		setVisible(true);
		input.addActionListener(this);

	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket s=new Socket(host,9830);
			InputStream ins=s.getInputStream();
			OutputStream os=s.getOutputStream();
			i=new BufferedReader(new InputStreamReader(ins));
			o=new PrintWriter(new OutputStreamWriter(os),true);
			while(true) {
				String line=i.readLine();
				output.append(line+"\n");
				jb.setValue(jb.getMaximum());
			}
			
		}catch(IOException e) {}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object c=e.getSource();
		if(c==input) {
			label.setText("�޽���");
			o.println(input.getText()); // ���� ��Ʈ��ũ�� ���ؼ� ������ write �ض� ��� ��
			input.setText("");
		}
		
	}
	
	
	
	public static void main(String[] args) {
		if(args.length >0) {
			new ClientEx4(args[0]);	
		}else {
			new ClientEx4("localhost");
		}
		
	}
	
}
