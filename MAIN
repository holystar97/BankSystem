package bank;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.ValueObject;
import io_stream.ClientEx4;
import io_stream.ServerEx4;

class MyCanvas extends Canvas {
 public MyCanvas() {
  setSize(750, 750);
 }

 public void paint(Graphics g) {
  Image img;
  Toolkit tool = Toolkit.getDefaultToolkit();
  img = tool.getImage("D:/img/bank.jpg");
  g.drawImage(img, 20, 20, this);
//  g.drawRect(10, 10, 100, 100);
 }
}

public class MainEx extends JFrame implements ActionListener {

 public MenuBar mb;
 public Menu m1, m2, m3, m4, m5;
 public MenuItem[] mi1, mi2, mi3, mi4;
 public Button[] b, b1, b2;
 public Panel p0, p1, p2, p3, p_main;
 CardLayout cards;
 public Label la, la1, la2, la3, la4;
 JButton mb1, mb2, mb3;
 public Label l1, l2, l3, l4;
 public Login login;
 Image img;
 MyCanvas myCanvas;

 public Deposit deposit;
 public Withdraw withdraw;
 public Transfer transfer;
 public CustomerList list;
 public Balance balance;
 public EventEx event;
 public Partner partner;
 public SwingCalendar cal;
 public Chatting_Server cs;
 public Chatting_Client cc;

 public Note note;
 public ArrayList<Customers> AL = new ArrayList<Customers>();
 // public ArrayList<EventPerson> AL2= new ArrayList<EventPerson>();

 ArrayList<ValueObject> arr_vo_custlist;
 File file;
 BufferedReader br;
 BufferedWriter bw; 
 
 MainEx() {

  login = new Login(this);
  login.setVisible(false);

  list = new CustomerList(this);
  list.setVisible(false);

  deposit = new Deposit(this);
  deposit.setVisible(false);

  withdraw = new Withdraw(this);
  withdraw.setVisible(false);

  transfer = new Transfer(this);
  transfer.setVisible(false);

  balance = new Balance(this);
  balance.setVisible(false);

  event = new EventEx(this);
  event.setVisible(false);

  partner = new Partner(this);
  partner.setVisible(false);

  cs=new Chatting_Server(this,9830);
  cs.setVisible(false);
  
  cc=new Chatting_Client(this,"localhost");
  cc.setVisible(false);
  
  
  cal = new SwingCalendar(this);
  cal.setVisible(false);

  note = new Note(this);
  note.setVisible(false);

  cards = new CardLayout();
  mb1 = new JButton("관리자모드");
  mb2 = new JButton("일반고객 모드");
  mb3 = new JButton("고객센터");

  p0 = new Panel(); // 처음
  p0.setBackground(Color.WHITE);

  p1 = new Panel(); // 관리자 모드
  p1.setBackground(Color.WHITE);
  p2 = new Panel(); // 고객 모드
  p2.setBackground(Color.WHITE);
  p3 = new Panel(); // 고객센터
  p3.setBackground(Color.WHITE);

  p_main = new Panel(); // 메인 패널
  p_main.setLayout(cards);

  ////////////////////// 첫화면

  // p0.setLayout(new BorderLayout());
  // la=new Label("mk bank에 오신것을 환영합니다.");
  p0.add(mb1);
  p0.add(mb2);
  p0.add(mb3);

  // p0.add(la,BorderLayout.CENTER);

  /////////////////////// 관리자 모드

  b = new Button[11];

  // la1=new Label("<<<관리자 모드>>>");

  b[0] = new Button("로그인");

  b[0].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    login.setVisible(true);

   }
  });

  b[1] = new Button("(상담원용) 채팅");
  b[1].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    cs.setVisible(true);
   }
  });
  
  b[2] = new Button("(고객용) 채팅");
  b[2].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    cc.setVisible(true);
    
    
   }
  });
  
  
  b[3] = new Button("전체 고객 정보 출력");

  b[3].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    list.setVisible(true);
    if (AL != null && AL.size() > 0) {
     custsList(list.ta);
    } else {
     list.ta.setText("정보가 없습니다.");
    }

   }
  });

  b[4] = new Button("예금");

  b[4].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    deposit.setVisible(true);

   }
  });

  b[5] = new Button("출금");

  b[5].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    withdraw.setVisible(true);

   }
  });

  b[6] = new Button("이체");

  b[6].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    transfer.setVisible(true);
    if (AL != null && AL.size() > 0) {
     custsAccount(transfer.ta);
    } else {
     transfer.ta.setText("정보가 없습니다.");
    }

   }
  });

  b[7] = new Button("잔고");

  b[7].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    balance.setVisible(true);

   }
  });

  b[8] = new Button("이벤트");

  b[8].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    event.setVisible(true);

   }
  });

  b[9] = new Button("종료");

  b[10] = new Button("상담원 현황");

  b[10].addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    partner.setVisible(true);

   }
  });

  // p1.add(la1);
  for (int i = 0; i < b.length; i++) {
   p1.add(b[i]);
  }

  myCanvas = new MyCanvas();
  Toolkit tool = Toolkit.getDefaultToolkit();
  img = tool.getImage("D:/img/bank.jpg");

  p1.add(myCanvas);
  // p1.add(myCanvas,"Center");

  /////////////////////// 일반고객모드

  b1 = new Button[7];

  // p2.setLayout(new GridLayout(4,3));

  // la2=new Label("<<<일반 고객 모드>>>");

  b1[0] = new Button("고객정보 입력");
  b1[1] = new Button("고객정보 출력");
  b1[2] = new Button("예금");
  b1[3] = new Button("출금");
  b1[4] = new Button("이체");
  b1[5] = new Button("잔고");
  b1[6] = new Button("종료");

  // p2.add(la2);
  for (int i = 0; i < b1.length; i++) {
   p2.add(b1[i]);
  }

  myCanvas = new MyCanvas();
  tool = Toolkit.getDefaultToolkit();
  img = tool.getImage("D:/img/bank.jpg");

  p2.add(myCanvas);

  /////////////////////// 고객센터

  b2 = new Button[4];

  // la3=new Label("<<<고객센터>>>");

  b2[0] = new Button("이벤트");
  b2[1] = new Button("수수료");
  b2[2] = new Button("채팅");
  b2[3] = new Button("위치");

  for (int i = 0; i < b2.length; i++) {
   p3.add(b2[i]);
  }

  myCanvas = new MyCanvas();
  tool = Toolkit.getDefaultToolkit();
  img = tool.getImage("D:/img/bank.jpg");

  p3.add(myCanvas);

  // p3.add(la3);

  p_main.add(p1, "p1");
  p_main.add(p2, "p2");
  p_main.add(p3, "p3");

  add(p0, "North");
  add(p_main, "Center");
  cards.show(p_main, "p1");
  mb1.addActionListener(this);
  mb2.addActionListener(this);
  mb3.addActionListener(this);

  /////////////////////// 메뉴 바

  mb = new MenuBar();
  m1 = new Menu("관리자 모드 ");
  mi1 = new MenuItem[7];
  mi1[0] = new MenuItem("고객정보 출력");
  mi1[1] = new MenuItem("전체 고객정보 출력");
  mi1[2] = new MenuItem("예금");
  mi1[3] = new MenuItem("출금 ");
  mi1[4] = new MenuItem("이체");
  mi1[5] = new MenuItem("잔고");
  mi1[6] = new MenuItem("종료");

  for (int i = 0; i < mi1.length; i++) {
   m1.add(mi1[i]);
   mi1[i].addActionListener(this);
   // mi[i].addActionListener(this);
   if (i == 1 || i == 4 || i == 5)
    m1.addSeparator();
  }
  // mi[1].setEnabled(false); // 컴포넌트를 비활성화 시키는 녀석

  m2 = new Menu("일반 고객 모드");
  mi2 = new MenuItem[6];
  mi2[0] = new MenuItem("고객정보 입력");
  mi2[1] = new MenuItem("예금");
  mi2[2] = new MenuItem("출금");
  mi2[3] = new MenuItem("이체");
  mi2[4] = new MenuItem("잔고");
  mi2[5] = new MenuItem("종료");

  for (int i = 0; i < mi2.length; i++) {
   m2.add(mi2[i]);
   mi2[i].addActionListener(this);
   // mi[i].addActionListener(this);
   if (i == 0 || i == 4)
    m2.addSeparator();
  }

  m3 = new Menu("고객센터");
  mi3 = new MenuItem[3];
  mi3[0] = new MenuItem("이벤트");
  mi3[1] = new MenuItem("수수료");
  mi3[2] = new MenuItem("채팅");

  m4 = new Menu("일정관리");
  mi4 = new MenuItem[3];
  mi4[0] = new MenuItem("달력");
  mi4[1] = new MenuItem("수첩");
  mi4[2] = new MenuItem("시계");

  for (int i = 0; i < mi3.length; i++) {
   mi3[i].addActionListener(this);
   m3.add(mi3[i]);
   // mi[i].addActionListener(this);
   m3.addSeparator();
  }

  for (int i = 0; i < mi4.length; i++) {
   mi4[i].addActionListener(this);
   m4.add(mi4[i]);
   // mi[i].addActionListener(this);
   m4.addSeparator();
  }

  mb.add(m1);
  mb.add(m2);
  mb.add(m3);
  mb.add(m4);

  setMenuBar(mb);
  setSize(300, 200);
  setVisible(true);

  setSize(600, 600);
  // setVisible(true);
  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    System.exit(0);
   }
  });

 }

 public static void main(String[] args) {
  // TODO Auto-generated method stub

  new MainEx();
  /*
  if(args.length >0) {
   (args[0]); 
  }else {
   new Chatting_Client("localhost");
  }
  
  new Chatting_Server(9830);*/

 }

 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub

  Object obj = e.getSource();
  if (obj == mb1) {
   cards.show(p_main, "p1");
  }

  else if (obj == mb2) {
   cards.show(p_main, "p2");
  }

  else if (obj.equals(mb3)) {
   cards.show(p_main, "p3");
  }

  else if (obj.equals(mi2[1])) {

   deposit.setVisible(true);
  }

  else if (obj.equals(mi4[0])) {

   cal.setVisible(true);
  }

  else if (obj.equals(mi4[1])) {

   note.setVisible(true);
  }

  else if (obj.equals(mi4[2])) {

   // watch.setVisible(true);
  }

 }

 public void custsList(TextArea ta) {

  /*
  System.out.println("read1");
  
  arr_vo_custlist = new ArrayList<>();
  file = new File("C:/IO/enter.txt");
  try {
   br = new BufferedReader(new FileReader(file));
   System.out.println("read");
   while (br.ready()) {

    String userInfo = br.readLine();
    StringTokenizer st = new StringTokenizer(userInfo, "@");
    if (st.countTokens() > 1) {
     ValueObject vo = new ValueObject(st.nextToken(), st.nextToken(),st.nextToken(), st.nextToken());
     arr_vo_custlist.add(vo);
     System.out.printf("%s",vo.getVal1() );
     ta.append("아이디: ");
     ta.append(vo.getVal1());
    }
   }

  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
   */
  Iterator<Customers> data = AL.iterator();
  while (data.hasNext()) {
   Customers cust = data.next();
   ta.append("이름: ");
   ta.append(cust.getName());
   ta.append("\n\n");
   ta.append("아이디: ");
   ta.append(cust.getId());
   ta.append("\n\n");
   ta.append("비밀번호: ");
   ta.append(cust.getPw());
   ta.append("\n\n");
   ta.append("핸드폰 번호: ");
   ta.append(cust.getPhone());
   ta.append("\n\n");
   ta.append("이메일: ");
   ta.append(cust.getEmail());
   ta.append("\n\n");
   ta.append("계좌번호: ");
   ta.append(cust.getAccount());
   ta.append("\n\n");
   ta.append("잔액: ");
   String money = Integer.toString(cust.getBalance());
   ta.append(money);
   ta.append("\n\n");
   ta.append("---------------\n");
  }
 }

 public void custsAccount(TextArea ta) {

  Iterator<Customers> data = AL.iterator();
  while (data.hasNext()) {
   Customers cust = data.next();
   ta.append(cust.getAccount());
   ta.append("\n\n");
   ta.append("---------------\n");

  }

 }

}
