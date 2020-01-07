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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class Deposit extends Frame implements Runnable{

 public Dialog dialog;
 public MainEx main;
 public ArrayList<Customers> custs;
 public Customers cust;
 public TextField deposit1,id1;
 public MyWatch myWatch;
 public Thread thread;
 public Label clock;
 String now = "";
 
 
 Deposit(MainEx main){
  //main=new MainEx();
  this.main = main; 
  custs = main.AL;
  
  main.setVisible(false);
  
  Panel p0= new Panel();
  Label la1 = new Label("★★★★★★★★DEPOSIT★★★★★★★★");
  Font f1=new Font("Serif",Font.BOLD,50);
  la1.setFont(f1);
  
  p0.add(la1);
 
  Panel p1=new Panel();
  p1.setLayout(new GridLayout(3,2));
  
 
  Label deposit=new Label("예금액");
  deposit1=new TextField("",10);
  
  
  Label id=new Label("ID 입력");
  id1=new TextField("",10);
  
  
  p1.add(id);
  p1.add(id1); 
  p1.add(deposit);
  p1.add(deposit1);
 
  
  Color color =new Color(242,215,213);
  
  this.setLayout(new BorderLayout());
  
  this.setBackground(color);
  
  
  Panel p3=new Panel();
  //p3.setLayout(new BorderLayout());
  //Label la2=new Label(" ");
  Label la3=new Label("mk bank 를 이용해주셔서 감사합니다.");
  Button send=new Button("전송");
  
  
  Panel p4 = new Panel();
  clock=new Label(now);
  
  
  if(thread==null) {
   thread=new Thread(this);
   thread.start();
 
  
   
  }
  
  myWatch = new MyWatch();
  Thread th=new Thread(myWatch);
  th.start();
 
  p3.add(clock);
  p4.add(myWatch);
 
  
  
  dialog=new MyDepositDialog(this);
  //dialog.add(new JButton("로그인"));
  dialog.setSize(200,200);
  
  
  
  send.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    dialog.setVisible(true);
    int amount=Integer.parseInt(deposit1.getText());
    
    for (int i = 0; i < custs.size(); i++) {


     if (custs.get(i).getId().equals(id1.getText())) {

      int balance = custs.get(i).getBalance() + amount;

      custs.get(i).setBalance(balance);

     }

    }
    
    id1.setText("");
    deposit1.setText("");
    
   }
  });
  
  
  
  
  
  
  p3.add(la3,BorderLayout.CENTER);
  p3.add(send,BorderLayout.SOUTH);
  ///p3.add(la4,BorderLayout.SOUTH);
  //p3.add(la5,BorderLayout.WEST);
  
  this.add(p1,BorderLayout.CENTER);
  this.add(p0,BorderLayout.NORTH);
  this.add(p3,BorderLayout.SOUTH);
  this.add(p4, BorderLayout.EAST);
  
  setSize(600, 600);
  setVisible(true);

  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    Deposit.this.setVisible(false);
   }
  });

  
 }
 
 
 
 /*
  * public static void main(String[] args) { // TODO Auto-generated method stub
  * 
  * new Deposit();
  * 
  * 
  * }
  */
 class MyDepositDialog extends Dialog{
   
  
  public MyDepositDialog(Frame frame) {
   super(frame);  
   add(new Label("이체되었습니다."));
  
   addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {   
     main.setVisible(true);
     dispose(); //자원해제하는 기능
    }
   });
   
  }
 }



 @Override
 public void run() {
  // TODO Auto-generated method stub
  
  while (true) {

   Calendar cal = Calendar.getInstance();
   now = cal.get(Calendar.YEAR) + "년" + (cal.get(Calendar.MONTH) + 1) + "월" + cal.get(Calendar.DATE) + "일"
     + cal.get(Calendar.HOUR) + "시" + cal.get(Calendar.MINUTE) + "분" + cal.get(Calendar.SECOND) + "초";

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
