package bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;



public class Chatting_handler extends Thread{

 private Socket s;
 private BufferedReader i;
 private PrintWriter o;
 private Chatting_Server server;
 
 public Chatting_handler(Chatting_Server server,Socket s) throws IOException{
  this.s=s;
  this.server=server;
  InputStream ins=s.getInputStream();
  OutputStream os=s.getOutputStream();
  i=new BufferedReader(new InputStreamReader(ins));
  o=new PrintWriter(new OutputStreamWriter(os),true);
 
 }
 public void run() {
  String name="";
  try {
   name=i.readLine();
   server.register(this);
   broadcast(name+"님이 방문하셨습니다.");
   while(true) {
    String msg=i.readLine();
    broadcast(name+"-"+msg);
   }
  }catch(IOException ex) {}
  server.unregister(this);
  broadcast(name+"님이 나가셨습니다.");
  
  try {
   i.close();
   o.close();
   s.close();
  }catch(IOException ex) {}
 }
 
 protected void println(String message) {
  o.println(message);
 }
 
 protected void broadcast(String message) {
  server.broadcast(message);
 }
 
 
 
}