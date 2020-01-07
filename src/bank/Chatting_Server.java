package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class Chatting_Server extends JFrame implements Runnable {

 public Label la;
 public Panel p0, p1, p2, p3, p4;
 public TextArea output;
 private JTextField input;
 private Label label;
 public MyWatch myWatch;
 String now = "";
 public Thread thread;
 public Label clock;
 public MainEx main;
 private Vector handlers;

 Chatting_Server(MainEx main, int port) {
  // this.setLayout(new BorderLayout());

  this.main = main;
  main.setVisible(false);

  Panel p0 = new Panel();
  Label la1 = new Label("Chatting");
  Font f1 = new Font("Serif", Font.BOLD, 50);
  la1.setFont(f1);
  p0.add(la1);

  Panel p1 = new Panel();
  // p1.setLayout(new GridLayout(3, 2));

  output = new TextArea("", 20, 30);
  output.setFont(new Font("Serif", Font.BOLD, 15));

  label = new Label("User");
  Font f2 = new Font("Serif", Font.BOLD, 15);
  label.setFont(f2);
  input = new JTextField("", 10);

  p1.add(output);
  p1.add(label);
  p1.add(input);

  Panel p3 = new Panel();
  clock = new Label(now);

  if (thread == null) {
   thread = new Thread(this);
   thread.start();

  }

  Label la3 = new Label("Welcome to visit OKDOL BANK.");
  la3.setFont(new Font("Serif", Font.BOLD, 20));
  Button send = new Button("SEND");
  send.setBackground(new Color(255, 160, 122));

  Panel p4 = new Panel();
  myWatch = new MyWatch();
  Thread th = new Thread(myWatch);
  th.start();

  p3.add(la3, BorderLayout.CENTER);
  p3.add(send, BorderLayout.SOUTH);
  p3.add(clock);
  p4.add(myWatch);

  this.setLayout(new BorderLayout());
  Color color = new Color(242, 215, 213);
  this.getContentPane().setBackground(color);

  this.add(p0, BorderLayout.NORTH);
  this.add(p1);

  this.add(p3, BorderLayout.SOUTH);
  this.add(p4, BorderLayout.EAST);

  setSize(600, 600);
  setVisible(true);

  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    Chatting_Server.this.setVisible(false);
   }
  });

  try {
   ServerSocket server = new ServerSocket(port);
   handlers = new Vector();
   System.out.println("Chatting_Server is ready.");

   while (true) {
    Socket client = server.accept();
    Chatting_handler c = new Chatting_handler(this, client);
    c.start();
   }

  } catch (Exception e) {
  }
 }

 public Object getHandler(int index) {
  return handlers.elementAt(index); // 특정된 사람하고 메시지 보낼 때
 }

 public void register(Chatting_handler c) {
  handlers.addElement(c);
 }

 public void unregister(Object o) {
  handlers.removeElement(o);
 }

 public void broadcast(String message) {
  synchronized (handlers) {
   int n = handlers.size();
   for (int i = 0; i < n; i++) {
    Chatting_handler c = (Chatting_handler) handlers.elementAt(i);
    c.println(message);
   }
  }
 }

 @Override
 public void run() {
  // TODO Auto-generated method stub

  while (true) {

   Calendar cal = Calendar.getInstance();
   now = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + " "
     + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + " ";

   clock.setText(now);
   clock.setFont(new Font("Serif", Font.BOLD, 12));

   try {
    Thread.sleep(1000); // 1초마다
   } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }

 }

}