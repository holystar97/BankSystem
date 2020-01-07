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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Chatting_Client extends JFrame implements Runnable, ActionListener {

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
 
 private BufferedReader i;
 private PrintWriter o;
 private Thread listener;
 private String host;
 
 
 
 Chatting_Client(MainEx main,String server) {
  // this.setLayout(new BorderLayout());

  super("채팅 프로그램");
  host=server;
  listener= new Thread(this);
  listener.start();
  
  
  this.main = main;
  main.setVisible(false);
  Panel p0 = new Panel();
  Label la1 = new Label("★★★★★★★★채팅★★★★★★★★");
  Font f1 = new Font("Serif", Font.BOLD, 50);
  la1.setFont(f1);
  p0.add(la1);

  Panel p1 = new Panel();
  output = new TextArea("", 20, 30);
  output.setFont(new Font("Serif", Font.BOLD, 15));

  label = new Label("사용자이름");
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

  Label la3 = new Label("mk bank 를 이용해주셔서 감사합니다.");
  la3.setFont(new Font("Serif", Font.BOLD, 20));
  Button send = new Button("확인");
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
  input.addActionListener(this);
  
  
  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    Chatting_Client.this.setVisible(false);
   }
  });

  
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
   }
   
  }catch(IOException e) {}
  
  
  
  while (true) {

   Calendar cal = Calendar.getInstance();
   now = cal.get(Calendar.YEAR) + "년" + (cal.get(Calendar.MONTH) + 1) + "월" + cal.get(Calendar.DATE) + "일"
     + cal.get(Calendar.HOUR) + "시" + cal.get(Calendar.MINUTE) + "분" + cal.get(Calendar.SECOND) + "초";

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

 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  Object c=e.getSource();
  if(c==input) {
   label.setText("메시지");
   o.println(input.getText()); // 값을 네트워크를 통해서 보내라 write 해라 라는 뜻
   input.setText("");
  }
 }

}