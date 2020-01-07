package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import gui.ValueObject;

public class Partner extends Frame implements ItemListener, Runnable {

 ArrayList<ValueObject> arr_vo;
 public MyWatch myWatch;
 //public MyPartnerWatch myPartnerWatch;
 Panel p1, p2, p3, p4;
 Label la[], before_time;
 CheckboxGroup cg;
 Checkbox ch1, ch2, ch3, ch4, ch5, ch6;
 TextArea ta;
 Choice cho, item;
 Button submit;
 File file;
 BufferedReader br;
 PrintWriter pw; 
 Label clock;
 String main_now = "";
 String start = "";
 String now = "";
 Date strA, strB, strC, curDate;
 String before = "";
 public Thread thread;
 public MainEx main;
 Calendar cal = Calendar.getInstance();

 public Partner(MainEx main) {

  this.main = main;

  arr_vo = new ArrayList<>();
  
  item = new Choice();

  if (!arr_vo.isEmpty()) {
   addUserInfo();
  }
  
  
  
  submit = new Button("Send");

  p1 = new Panel();
  p2 = new Panel();
  p3 = new Panel();
  p4 = new Panel();
  la = new Label[10];
  for (int i = 0; i < la.length; i++) {
   la[i] = new Label("");
  }

  cg = new CheckboxGroup();
  ch1 = new Checkbox("Counselor A", cg, true);
  ch2 = new Checkbox("Counselor B", cg, false);
  ch3 = new Checkbox("Counselor C", cg, false);
  ch4 = new Checkbox("Break");
  ch5 = new Checkbox("Work");
  ch6 = new Checkbox("Meal");
  ta = new TextArea(" <Counselor Time> ", 20, 20, TextArea.SCROLLBARS_NONE);
  ta.setFont(new Font("Serif", Font.BOLD, 20));
  before_time = new Label();
  cho = new Choice();
  cho.add("Counselor A");
  cho.add("Counselor B");
  cho.add("Counselor C");

  item = new Choice();

  p1.setLayout(new GridLayout(2, 4));
  p1.setBackground(new Color(242, 215, 213));
  p1.add(la[8]);
  p1.add(ch1);
  p1.add(ch2);
  p1.add(ch3);
  p1.add(la[9]);
  p1.add(ch4);
  p1.add(ch5);
  p1.add(ch6);

  p2.setLayout(new BorderLayout());
  p2.setBackground(new Color(255, 160, 122));
  p2.add(ta, BorderLayout.CENTER);
  p2.add(before_time, BorderLayout.NORTH);
  p2.add(item, BorderLayout.SOUTH);
  p2.add(la[2], BorderLayout.WEST);
  p2.add(la[3], BorderLayout.EAST);

  p3.setLayout(new BorderLayout());
  p3.setBackground(new Color(242, 215, 213));
  p3.add(cho, BorderLayout.CENTER);

  p3.add(la[4], BorderLayout.WEST);
  p3.add(la[5], BorderLayout.EAST);

  p3.add(la[6], BorderLayout.NORTH);
  p3.add(submit, BorderLayout.SOUTH);

  if (thread == null) {
   thread = new Thread(this);
   thread.start();

  }

  myWatch = new MyWatch();
  Thread th = new Thread(myWatch);
  th.start();

  // myPartnerWatch=new MyPartnerWatch();
  // Thread th2=new Thread();
  // th2.start();

  p4.setLayout(new BorderLayout());
  p4.setBackground(new Color(242, 215, 213));
  p4.add(myWatch, BorderLayout.NORTH);
  clock = new Label(main_now);
  p4.add(clock, BorderLayout.SOUTH);
  
  
  
  

  add("North", p1);
  add("Center", p2);
  add("South", p3);
  add("East", p4);

  setSize(600, 600);
  setVisible(true);

  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    saveTofile();
    setVisible(false);
   }
  });

  ch1.addItemListener(this);
  ch2.addItemListener(this);
  ch3.addItemListener(this);
  ch4.addItemListener(this);
  ch5.addItemListener(this);
  ch6.addItemListener(this);
  cho.addItemListener(this);

  item.addItemListener((e) -> {
   ValueObject vo = arr_vo.get(item.getSelectedIndex());

   ta.setText("<User Info>\n\n\n ");
   ta.setFont(new Font("Serif", Font.BOLD, 20));
   ta.append(vo.getVal1() + "\n\n");
   ta.setFont(new Font("Serif", Font.BOLD, 20));
   StringTokenizer st = new StringTokenizer(vo.getVal2(), "☆");

   while (st.hasMoreTokens()) {
    ta.append(st.nextToken() + "\n\n");
    ta.setFont(new Font("Serif", Font.BOLD, 18));
   }
   ta.append("=================\n");

   /*
    * //curDate= new Date(); SimpleDateFormat dateFormat = new
    * SimpleDateFormat("YYYYMMddHHmm"); try { strA =
    * dateFormat.parse(dateFormat.format(strA)); System.out.println(strA); } catch
    * (ParseException e2) { // TODO Auto-generated catch block
    * e2.printStackTrace(); } long strATime=strA.getTime();
    * System.out.println(strATime); /* try { curDate =
    * dateFormat.parse(dateFormat.format(curDate)); System.out.println(curDate); }
    * catch (ParseException e1) { // TODO Auto-generated catch block
    * e1.printStackTrace(); } long curDateTime = curDate.getTime();
    */

   /*
    * long curDateTime=System.currentTimeMillis();
    * 
    * System.out.println(curDateTime);
    * 
    * long time =((curDateTime - strATime)/60);
    * 
    * System.out.println(time);
    * 
    * String str=String.valueOf(time);
    * 
    * ta.append(str);
    * 
    */

  });

  submit.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {

    StringBuilder sb = new StringBuilder();
    Calendar scal = Calendar.getInstance();
    if (ch4.getState()) {
     sb.append(ch4.getLabel() + "☆");

     start = scal.get(Calendar.HOUR) + ":" + scal.get(Calendar.MINUTE) + ":" + scal.get(Calendar.SECOND)
       + "";

     strA = new Date();

     Calendar calA = Calendar.getInstance();
     calA.setTime(strA);
     int min = calA.get(Calendar.MINUTE) - cal.get(Calendar.MINUTE);
     int sec = calA.get(Calendar.SECOND) - cal.get(Calendar.SECOND);

     before = (Integer.toString(min) + ":" + Integer.toString(sec) + " ");

     before_time.setText(before);

     try {
      Thread.sleep(1000); // 1초마다
     } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
     }

     sb.append(start);

    } else if (ch5.getState()) {
     sb.append(ch5.getLabel() + "☆");
     start = scal.get(Calendar.HOUR) + ":" + scal.get(Calendar.MINUTE) + ":" + scal.get(Calendar.SECOND)
       + " ";

     strB = new Date();
     sb.append(start);
    }

    else if (ch6.getState()) {
     sb.append(ch6.getLabel());
     start = scal.get(Calendar.HOUR) + ":" + scal.get(Calendar.MINUTE) + ":" + scal.get(Calendar.SECOND)
       + " ";
     strC = new Date();
     sb.append(start);

    }

    Checkbox temp = cg.getSelectedCheckbox();
    ValueObject vo = new ValueObject(temp.getLabel(), sb.toString());
    arr_vo.add(vo);

    for (ValueObject value : arr_vo) {
     ta.append("<" + value.getVal1() + ">\n");
     ta.append("<" + value.getVal2() + ">\n");
     ta.append("=============\n");

    }
    addUserInfo();
    /*
     * for(int i=0; i<arr_vo.size();i++) {
     * ta.append("<"+arr_vo.get(i).getVal1()+">\n");
     * ta.append("<"+arr_vo.get(i).getVal2()+">\n"); }
     */
   }

  });

 }

 public void addUserInfo() {
  item.removeAll();
  for (int i = 0; i < arr_vo.size(); i++) {
   item.add("Counselor" + (i + 1));
  }

 }

 public void saveTofile() {

  try {
   PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)), true);

   for (ValueObject value : arr_vo) {
    pw.println(value.getVal1() + "@" + value.getVal2());
   }
   pw.close();

  } catch (IOException e) {

  }

 }
 
 
 @Override
 public void itemStateChanged(ItemEvent e) {
  // TODO Auto-generated method stub

  Object obj = e.getSource();
  // Choice c= (Choice) obj;
  if (obj == cho) {
   // 객체를 비교하는 역할만 하므로 c로 안바꿔줘도 된다
   String str = cho.getSelectedItem();
   if (str.equals("CounselorA")) {
    ch1.setState(true);
   } else if (str.equals("CounselorB")) {
    ch2.setState(true);
   } else if (str.equals("CounselorC")) {
    ch3.setState(true);
   }

  }

  Checkbox temp = cg.getSelectedCheckbox();
  ta.setText("  << " + temp.getLabel() + ">> \n\n");
  ta.append("1. Break : " + ch4.getState() + " \n");
  ta.append("2. Work : " + ch5.getState() + " \n");
  ta.append("3. Meal : " + ch6.getState() + " \n");

  cho.select(temp.getLabel());

 }

 @Override
 public void run() {
  // TODO Auto-generated method stub
  while (true) {
   Calendar mcal = Calendar.getInstance();

   main_now = mcal.get(Calendar.YEAR) + "/" + (mcal.get(Calendar.MONTH) + 1) + "/" + mcal.get(Calendar.DATE)
     + " " + mcal.get(Calendar.HOUR) + ":" + mcal.get(Calendar.MINUTE) + ":" + mcal.get(Calendar.SECOND)
     + " ";

   
   
    
   // before_time.setText(before);

   clock.setText(main_now);
   clock.setFont(new Font("Serif", Font.BOLD, 25));

   try {
    Thread.sleep(1000); // 1초마다
   } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 }

 /*
  * 
  * class MyPartnerWatch implements Runnable{
  * 
  * 
  * public MyPartnerWatch() {
  * 
  * }
  * 
  * 
  * @Override public void run() { // TODO Auto-generated method stub while (true)
  * { Calendar calA = Calendar.getInstance(); calA.setTime(strA); int min =
  * calA.get(Calendar.MINUTE) - cal.get(Calendar.MINUTE); int sec =
  * calA.get(Calendar.SECOND) - cal.get(Calendar.SECOND);
  * 
  * before = (Integer.toString(min) + "분" + Integer.toString(sec) + "초");
  * 
  * before_time.setText(before);
  * 
  * 
  * try { Thread.sleep(1000); // 1초마다 } catch (InterruptedException e) { // TODO
  * Auto-generated catch block e.printStackTrace(); }
  * 
  * } }
  * 
  * 
  * }
  */

}