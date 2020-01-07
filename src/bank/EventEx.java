package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import bank.Withdraw.MyInsufficientDialog;
import bank.Withdraw.MyWithdrawDialog;
import exception.BalanceInsufficientException;

public class EventEx extends Frame {

 public MainEx main;
 MyCanvas myCanvas;
 TextField jumin1;
 public Panel p0;
 public Label la1;
 public Dialog dialog, dialog2;
 public EventEx2 eventEx2;
 public EventPerson person;
 public ArrayList<EventPerson> persons = new ArrayList<EventPerson>();

 EventEx(MainEx main) {

  this.main = main;
  eventEx2 = new EventEx2(main);

  main.setVisible(false);
  p0 = new Panel();
  la1 = new Label("Event");
  p0.add(la1);

  Panel p1 = new Panel();
  p1.setLayout(new GridLayout(3, 2));

  Label jumin = new Label("Enter your identification number>> ");
  jumin1 = new TextField("", 10);

  p1.add(jumin);
  p1.add(jumin1);

  this.setLayout(new BorderLayout());
  this.add(p0, BorderLayout.NORTH);
  this.add(p1, BorderLayout.CENTER);

  Color color = new Color(242, 215, 213);

  Panel p3 = new Panel();
  // p3.setLayout(new BorderLayout());
  // Label la2=new Label(" ");
  Label la3 = new Label("Welcome to visit OKDOL BANK.");
  Button send = new Button("SEND");

  p3.add(la3);
  p3.add(send);
  this.add(p3, BorderLayout.SOUTH);

  this.setBackground(color);

  setSize(300, 200);
  setVisible(true);

  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    dispose(); // 자원해제하는 기능
   }
  });

  dialog = new MyJuminDialog(this);
  dialog.setSize(200, 200);

  dialog2 = new MyInsufficientJuminDialog(this);
  dialog2.setSize(200, 200);

  send.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {

    String regExp = "^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|[3][01])\\-[1-4][0-9]{6}$";

    // String regExp ="\\d{6}\\-[1-4]\\d{6}";

    boolean result = Pattern.matches(regExp, jumin.getText());
    if (!result) {
     dialog.setVisible(true);
     System.out.println("Correct");
    } else {

     dialog2.setVisible(true);
     System.out.println("Incorrect");
    }

    Calendar today = Calendar.getInstance();
    System.out.println("Today is ");
    System.out.print(today.get(Calendar.YEAR) + "/");
    System.out.print(today.get(Calendar.MONTH) + 1 + "/");
    System.out.println(today.get(Calendar.DATE) + " ");

    String gender = "";
    String we = "";
    String jumin = jumin1.getText();

    person = new EventPerson();

    person.setPersonalId(jumin);

    int age = Integer.parseInt(jumin.substring(0, 2));

    char ch = jumin.charAt(7);

    // 성별

    if (ch == '1' || ch == '3') {

     person.setGender("Male");

    } else if (ch == '2' || ch == '4') {

     person.setGender("Female");

    } else {

     person.setGender("Foreign");

    }

    // 나이

    if (ch == '1' || ch == '2') {

     age = 2019 - (1900 + age) + 1;
     person.setAge(age);

    } else if (ch == '3' || ch == '4') {

     age = 2019 - (1900 + age) + 1;
     person.setAge(age);

    }

    String ch2 = jumin.substring(2, 4);

    // 계절

    switch (ch2) {

    case "12":
    case "01":
    case "02":

     we = "Winter";
     person.setWeather(we);
     break;

    case "03":
    case "04":
    case "05":

     we = "Spring";
     person.setWeather(we);
     break;

    case "06":
    case "07":
    case "08":

     we = "Summer";
     person.setWeather(we);
     break;

    case "09":
    case "10":
    case "11":

     we = "Autumn";
     person.setWeather(we);
     break;

    }

    persons.add(person);

    System.out.println(person.getAge());
    System.out.println(person.getGender());
    System.out.println(person.getPersonalId());
    System.out.println(person.getWeather());

    personId(EventEx2.pId1);
    personGender(EventEx2.gender1);
    personAge(EventEx2.age1);
    personWeather(EventEx2.we1);
    personGift(EventEx2.gift);

    System.out.println("Entered identfication number is  " + jumin);

    System.out.println("Gender is  " + gender);

    System.out.println("Age is " + age);

    System.out.println("Born Weather is " + we);

    // 970615
    if (Integer.parseInt(jumin.substring(2, 4)) == today.get(Calendar.MONTH) + 1) {

     if (Integer.parseInt(jumin.substring(4, 6)) == today.get(Calendar.DATE)) {
      System.out.println("Happy Birthday to you.");
     }

    } else {
     System.out.println("Next chance");
    }

   }
  });

 }

 class MyJuminDialog extends Dialog {

  public MyJuminDialog(Frame frame) {
   super(frame);
   add(new Label("Correct."));
   addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     eventEx2.setVisible(true);
     dispose(); // 자원해제하는 기능
    }
   });

  }
 }

 class MyInsufficientJuminDialog extends Dialog {

  public MyInsufficientJuminDialog(Frame frame) {
   super(frame);
   add(new Label("Please enter the correct number "));

   addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     main.setVisible(true);
     dispose(); // 자원해제하는 기능
    }
   });

  }
 }

 public void personId(TextField pId1) {
  pId1.setText(person.getPersonalId());
 }

 public void personGender(TextField pGender) {

  pGender.setText(person.getGender());
 }

 public void personAge(TextField age) {
  String temp_age = Integer.toString(person.getAge());
  age.setText(temp_age);
 }

 public void personWeather(TextField we) {
  we.setText(person.getWeather());
 }

 public void personGift(TextField gift) {

  Calendar today = Calendar.getInstance();
  if (Integer.parseInt(person.getPersonalId().substring(2, 4)) == today.get(Calendar.MONTH) + 1) {

   if (Integer.parseInt(person.getPersonalId().substring(4, 6)) == today.get(Calendar.DATE)) {
    gift.setText("Gift coupon is send for you");
   }

  } else {
   gift.setText("Next chance");
  }

 }

}