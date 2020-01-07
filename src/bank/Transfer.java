package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
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
import java.util.ArrayList;
import java.util.Calendar;

import bank.Deposit.MyDepositDialog;

public class Transfer extends Frame implements Runnable {

 public Dialog dialog;
 public MainEx main;
 public Panel p0, p1,p2 ,p3,p1_1,p1_2,p1_3;
 public Label la0, la1, la2, la3, la4, la5, la6, gla1, gla2, gla3,clock;
 public TextField tf1, tf2, tf3, tf4, tf5;
 public TextArea ta;
 public Button send, check;
 public MyWatch myWatch;
 public Thread thread;
 String now = "";
 
 
 
 public ArrayList<Customers> custs;
 public Customers cust;

 Transfer(MainEx main) {
  // main=new MainEx();
  this.main = main;
  custs = main.AL;
  main.setVisible(false);
  System.out.println(custs.size());

  p0 = new Panel();
  //p0.setLayout(new GridLayout(1,2));
  //p0.setLayout(new BorderLayout());
  
  la0 = new Label("Transfer");
  Font f1=new Font("Serif",Font.BOLD,50);
  la0.setFont(f1);
  
  
  p0.add(la0);
  
  
  

  p1 = new Panel();
  p1_1=new Panel();
  p1_2=new Panel();
  p1_3=new Panel();
  
  //p1.setLayout(new BorderLayout());
  la1 = new Label("Which account? ");
  ta = new TextArea("", 20, 20, 3);
  tf1 = new TextField("", 20);

  gla1 = new Label("\n\n");
  la6 = new Label("What is your account? ");
  tf5 = new TextField("", 20);
  gla2 = new Label("\n\n");

  la2 = new Label("Money> ");
  tf2 = new TextField("", 10);
  gla3 = new Label("\n\n");

  
  
  
  
  
  check = new Button("SEND");

  // String target= "0000";
  // String money="10000";
  // la3=new Label("선택하신 계좌" + target +"의 현재 잔액 : " +money);
  //p1.add(la0);
  p1_1.add(la1,BorderLayout.NORTH);
  p1_1.add(ta,BorderLayout.CENTER);
  //p1.add(gla1);
  p1_1.add(tf1,BorderLayout.SOUTH);

  p1_2.add(gla1,BorderLayout.NORTH);
  p1_2.add(la6,BorderLayout.CENTER);
  p1_2.add(tf5,BorderLayout.SOUTH);

  p1_3.add(la2,BorderLayout.NORTH);
  p1_3.add(tf2,BorderLayout.CENTER);
  p1_3.add(gla2,BorderLayout.SOUTH);

  // p1.add(la3);

  Color color = new Color(242, 215, 213);
  //this.setLayout(new BorderLayout());
  p1.setBackground(color);
  
  p1.add(p1_1,BorderLayout.NORTH);
  p1.add(p1_2,BorderLayout.CENTER);
  p1.add(p1_3,BorderLayout.SOUTH);
  
  p2 = new Panel();
 
  clock=new Label(now);
  
  if (thread == null) {
   thread = new Thread(this);
   thread.start();

  }

  myWatch = new MyWatch();
  Thread th = new Thread(myWatch);
  th.start();
  
  
  
  p3 = new Panel();

  Label la3 = new Label("Welcome to visit OKDOL BANK.");
  la3.setFont(new Font("Serif",Font.BOLD,20));
  send = new Button("SEND");

  dialog = new MyTranferDialog(this);
  // dialog.add(new JButton("로그인"));
  dialog.setSize(100, 100);
  send.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

    String targetAddr = tf1.getText();
    String currentAddr = tf5.getText();

    int transferAmount = Integer.parseInt(tf2.getText());
    int idx,i;
    for (i = 0; i < custs.size(); i++) {

     if (currentAddr.equals(custs.get(i).getAccount())) {
      idx = i;
      custs.get(idx).setBalance(custs.get(idx).getBalance() - transferAmount);
      System.out.println("Found account.");
     }
     
     
     
    }
    
    
    for(i=0; i<custs.size();i++) {
     
     if (targetAddr.equals(custs.get(i).getAccount())) {
      custs.get(i).setBalance(custs.get(i).getBalance() + transferAmount);
      System.out.println("Complete.");
     }
     
    }
    

    dialog.setVisible(true);

   }
  });
  p3.add(la3, BorderLayout.NORTH);
  p3.add(send, BorderLayout.SOUTH);
  /// p3.add(la4,BorderLayout.SOUTH);
  // p3.add(la5,BorderLayout.WEST);
  p3.add(clock);
  p2.add(myWatch, BorderLayout.NORTH);
  //p2.add(clock,BorderLayout.SOUTH);
  
  
  this.add(p2,BorderLayout.EAST);
  this.add(p1, BorderLayout.CENTER);
  this.add(p0, BorderLayout.NORTH);
  this.add(p3, BorderLayout.SOUTH);

  
  Color color2 = new Color(242, 215, 213);
  //this.setLayout(new BorderLayout());
  setBackground(color2);
  
  setSize(900, 600);
  setVisible(true);

  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    setVisible(false);
   }
  });
 }

 class MyTranferDialog extends Dialog {

  public MyTranferDialog(Frame frame) {
   super(frame);
   add(new Label("Transfered."));

   addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     main.setVisible(true);
     dispose(); // 자원해제하는 기능
    }
   });

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
   clock.setFont(new Font("Serif",Font.BOLD,12));

   try {
    Thread.sleep(1000); // 1초마다
   } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 }

}