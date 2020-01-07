package bank;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CustomerList extends Frame {

 
 public ArrayList<Customers> custs;
 public Customers cust;
 public MainEx main;
 TextArea ta;
 
 CustomerList(MainEx main){
  this.main = main; 
  custs = main.AL;
  main.setVisible(false);
  System.out.println(custs.size());

  Panel p =new Panel();
  ta=new TextArea("",50,50,3);
  p.add(ta); 
  
  
  
  
  this.add(p);
  
  setSize(500,500);
  setVisible(true);
  
  this.addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e) {
    main.setVisible(true);
    dispose(); //자원해제하는 기능
   }
  });  
  
 }
 
 

}