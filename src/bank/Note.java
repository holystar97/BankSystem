package bank;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class Note extends Frame implements ActionListener{
 
 public MainEx main;
 
 Note(MainEx main) {
  
  this.main = main; 
  main.setVisible(false);
  
  Calendar cal=Calendar.getInstance();
  
  setLayout(new GridLayout(10,10));

   int year=2019;
   int month=10;
   cal.set(year,month-1,1);
   int startDay=cal.get(Calendar.DAY_OF_WEEK);
   int lastDay=cal.getActualMaximum(Calendar.DATE);
   Button[] b=new Button[lastDay+startDay];
   
   for (int i=0; i<lastDay+startDay; i++) {
    if(i<startDay) {
     b[i]=new Button("" +"\t");
     continue;
    }
    
    b[i]=new Button("" +"\t");
    
   b[i]=new Button(""+((i-startDay +1)+ "\t"));
   
   
   if(i%7==0) {
    System.out.println();
   }
  }
  
  
  for (int i=0; i<lastDay+startDay; i++) {
   this.add(b[i]);
   b[i].addActionListener(this);
  }
 
 
  
  
  setSize(300,300);
  setVisible(true);
 
  
  
  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {   
    main.setVisible(true);
    dispose(); //자원해제하는 기능
   }
  });
  
  
 }

 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  
  new newWindow();
  
 }
 
 

}

class newWindow extends Frame{
 
 newWindow(){
  
  setTitle("일정 관리표");
  
  Panel p =new Panel();
  TextArea ta=new TextArea("TextArea",60,60,3);
  p.add(ta);
  
  this.add(p);
  
  setSize(500,500);
  setVisible(true);
  
  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {   
    dispose(); //자원해제하는 기능
   }
  });
  
 }
 
 
 
 
}