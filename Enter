package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import gui.ValueObject;

public class Enter extends Frame {

 public Dialog dialog;
 public MainEx main;
 public Customers cust;
 public ArrayList<Customers> custs;
 ArrayList<ValueObject> arr_vo_enter;
 File file;
 BufferedReader br;
 BufferedWriter bw;

 Enter(MainEx main) {

  this.main = main;
  custs = main.AL;
  // main=new MainEx();
  // main.setVisible(false);
  Panel p0 = new Panel();
  Label la1 = new Label("---------- 회원가입------------");

  p0.add(la1);

  Panel p1 = new Panel();
  p1.setLayout(new GridLayout(7, 2));

  Label name = new Label("이름");
  TextField name1 = new TextField("", 10);

  CheckboxGroup ch = new CheckboxGroup();
  Checkbox ch1 = new Checkbox("남자", true, ch);
  Checkbox ch2 = new Checkbox("여자", false, ch);

  Label id = new Label("아이디");
  TextField id1 = new TextField("", 10);

  Label pw = new Label("패스워드");
  TextField pw1 = new TextField("", 10);

  Label phone = new Label("휴대폰 번호");
  TextField phone1 = new TextField("", 10);

  Label email = new Label("이메일");
  TextField email1 = new TextField("", 10);

  Label address = new Label("주소");
  TextField address1 = new TextField("", 10);

  p1.add(name);
  p1.add(name1);
  p1.add(ch1);
  p1.add(ch2);
  p1.add(id);
  p1.add(id1);
  p1.add(pw);
  p1.add(pw1);
  p1.add(phone);
  p1.add(phone1);
  p1.add(email);
  p1.add(email1);
  p1.add(address);
  p1.add(address1);

  Panel p3 = new Panel();
  // p3.setLayout(new BorderLayout());
  // Label la2=new Label(" ");
  Label la3 = new Label("mk bank 를 이용해주셔서 감사합니다.");
  Button send = new Button("전송");


  send.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    cust = new Customers();

    cust.setName(name1.getText());
    // 체크 박스 일단 생략
    cust.setId(id1.getText());
    cust.setPw(pw1.getText());
    cust.setPhone(phone1.getText());
    cust.setEmail(email1.getText());
    // custs.get(0).setName(address1.getText());
    cust.setAccount(pw1.getText());
    custs.add(cust);
    dialog = new MyDialog3(Enter.this, main);
    // dialog.add(new JButton("로그인"));
    dialog.setSize(400, 400);

    //name1.setText("");
    //id1.setText("");
    //pw1.setText("");
    //phone1.setText("");
    //email1.setText("");
    //address1.setText("");

    arr_vo_enter = new ArrayList<>();

    file = new File("C:/IO/enter.txt");
    
    try {
     br = new BufferedReader(new FileReader(file));

     while (br.ready()) {

      String userInfo = br.readLine();
      StringTokenizer st = new StringTokenizer(userInfo, "@");
      if (st.countTokens() > 1) {
       ValueObject vo = new ValueObject(id1.getText(), pw1.getText(),phone1.getText(),email1.getText());
       arr_vo_enter.add(vo);
      }
     }

    } catch (IOException ex) {
     // TODO Auto-generated catch block
     ex.printStackTrace();
    }
    
    BufferedWriter writer=null;
    
    try {
     writer=new BufferedWriter(new FileWriter("C:/IO/enter.txt"));
  
     ValueObject vo = new ValueObject(id1.getText(),pw1.getText(),phone1.getText(),email1.getText());
     arr_vo_enter.add(vo);
     for (ValueObject value : arr_vo_enter) {
      writer.write(value.getVal1() + "@" + value.getVal2()+ "@" +value.getVal3() + "@" + value.getVal4());
     
     }
     writer.close();
     
    } catch (IOException ex) {

    }

    
    dialog.setVisible(true);

   }
  });

  // Label la4=new Label(" ");
  // Label la5=new Label(" ");

  // p3.add(la2,BorderLayout.NORTH);
  p3.add(la3, BorderLayout.CENTER);
  p3.add(send, BorderLayout.SOUTH);
  /// p3.add(la4,BorderLayout.SOUTH);
  // p3.add(la5,BorderLayout.WEST);

  setLayout(new BorderLayout());

  Color color = new Color(242, 215, 213);

  this.setBackground(color);

  this.add(p1, BorderLayout.CENTER);
  this.add(p0, BorderLayout.NORTH);
  this.add(p3, BorderLayout.SOUTH);

  setSize(400, 400);
  setVisible(false);

  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    // setVisible(false);
    dispose();
   }
  });

 }

 public static void main(String[] args) {
  // TODO Auto-generated method stub

//  new Enter();

 }

 class MyDialog3 extends Dialog {

  MainEx main;

  public ArrayList<Customers> custs;
  // public Customers cust;

  public MyDialog3(Frame frame, MainEx main) {
   super(frame);
   this.main = main;
   add(new Label("저장되었습니다."));
   add(new Label("고객님의 계좌번호는 " + cust.getAccount() + "입니다."));

   addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     // main.setVisible(true);
     dispose(); // 자원해제하는 기능
    }
   });

  }

 }
 

 
 
}
