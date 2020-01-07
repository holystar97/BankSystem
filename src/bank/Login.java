package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JTextField;

import bank.Deposit.MyDepositDialog;
import gui.ValueObject;

class MyWatch extends Canvas implements Runnable{

	private GregorianCalendar time;
	private int width=300;
	private int hour=0;
	private int min=0;
	private int sec=0;
	private Graphics gp;

public void paint(Graphics gp) {
		
		time=new GregorianCalendar();
		min=time.get(Calendar.MINUTE);
		hour=time.get(Calendar.HOUR);
		sec=time.get(Calendar.SECOND);
		
		if(sec ==60) {
			sec=0;
			min++;
		}
		
		if(min==60) {
			min=0;
			hour++;
		}
		if(min==60 && hour==12) {
			hour=0;
		}
		
		gp.clearRect(0, 0, width, width);
		
		rec_draw();
		
		draw(150,150,150,50,100,sec*6);
		draw(150, 150, 150, 50, 70, min * 6);
	    draw(150, 150, 150, 50, 50, hour * 30 + min / 2);
	}
	
	public void rec_draw() {
		   int j = 1;
	        for (int i = 1; i <= 60; i++) {
	            // 5 �� ������ �ð��� ǥ���ϸ�, ���� ũ�� ǥ���Ͽ� ������ �Ѵ�.
	            if (i % 5 == 0) {
	                draw_t(150, 50, i * 6, 3);
	                draw_time(150, 50, i * 6, j + "");
	                j++;
	            } else {
	                draw_t(150, 50, i * 6, 1);
	            }
	        }
		
	}
	
	
	public void draw_t(int x, int y, int angle, int width) {
		  x = 150 + (int) (120 * Math.sin(angle * Math.PI / 180));
	      y = 150 - (int) (120 * Math.cos(angle * Math.PI / 180));	    
	      if(gp == null) gp = getGraphics();
	      gp.fillRect(x, y, width, width);
	      gp.setFont(new Font("Serif",Font.BOLD,20));
		
	}
	
	public void draw_time(int x, int y, int angle, String i) {
	    x = 147 + (int) (105 * Math.sin(angle * Math.PI / 180));
        y = 155 - (int) (100 * Math.cos(angle * Math.PI / 180));
        gp.drawString(i, x, y);
        gp.setFont(new Font("Serif",Font.BOLD,20));
		
	}
	
	
	public void draw(int ox, int oy, int x, int y, int I, int angle) {
	    x = ox + (int) (I * Math.sin(angle * Math.PI / 180));
        y = oy - (int) (I * Math.cos(angle * Math.PI / 180));
        gp.drawLine(ox, oy, x, y);
        gp.setFont(new Font("Serif",Font.BOLD,20));
		
	}
	
	public MyWatch() {
        init();
    }
 
    // frame�� �׸��� ���� �ʱ�ȭ �޼���
    public void init() {
        this.setSize(width, width);
        this.setVisible(true);
        Color color =new Color(242,215,213);
        this.setBackground(color);
        gp = getGraphics();
    }
 
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		WatchEx wh=new WatchEx();
//		Thread th=new Thread(wh);
//		th.start();
//		
//		
//		
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		  while (true) {
				
			  
	            try {
	                Thread.sleep(1000); // 1�ʸ���
	                repaint();
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	}

}


public class Login extends Frame implements Runnable {

	ArrayList<ValueObject> arr_vo_id;
	File file;
	BufferedReader br;
	BufferedWriter bw;	
	public Dialog dialog, dialog2;
	public MainEx main;
	public Label id, pw, clock, usertime;
	public JTextField id1,pw1;
	public Enter enter;
	public Watch watch;
	public MyWatch myWatch;
	String now = "";
	String now2="";
	public Thread thread;

	Login(MainEx main) {

		this.main = main;
		main.setVisible(false);
		// enter.setVisible(false);
		enter = new Enter(main);
		

		Panel p0 = new Panel();
		Label la1 = new Label("LOGIN");
		Font f1=new Font("Serif",Font.BOLD,50);
		la1.setFont(f1);
		p0.add(la1);

		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(3, 2));

		Label id = new Label("ID");
		id.setFont(new Font("Serif",Font.BOLD,30));
		JTextField id1 = new JTextField();
		id1.setBounds(150, 150, 150, 30);

		Label pw = new Label("PW");
		JTextField pw1 = new JTextField();
		pw.setFont(new Font("Serif",Font.BOLD,30));
		pw1.setBounds(150, 300, 150, 30);
		
		p1.add(id);
		p1.add(id1);
		p1.add(pw);
		p1.add(pw1);

		Color color = new Color(242, 215, 213);

		this.setLayout(new BorderLayout());

		this.setBackground(color);

		Panel p3 = new Panel();
		// p3.setLayout(new BorderLayout());
		// Label la2=new Label(" ");
		Label la3 = new Label("Welcome to visit OKDOL BANK");
		la3.setFont(new Font("Serif",Font.BOLD,20));
		Button send = new Button("SEND");
		send.setBackground(new Color(255,160,122));


		
		Panel p4 = new Panel();
		clock=new Label(now);
		
		
		if(thread==null) {
			thread=new Thread(this);
			thread.start();
	
		
			
		}
		
		myWatch = new MyWatch();
		Thread th=new Thread(myWatch);
		th.start();
	
		
		
		
		//Thread th = new Thread(wh);
		//th.start();

	
		
		
		
		dialog = new MyLoginDialog(this);
		// dialog.add(new JButton("�α���"));

		dialog.setSize(100, 100);

		dialog2 = new EnterDialog(this);
		dialog2.setSize(200, 200);

		
		

		arr_vo_id = new ArrayList<>();
		
		file = new File("C:/IO/login.txt");
		try {
			br = new BufferedReader(new FileReader(file));

			while (br.ready()) {

				String userInfo = br.readLine();
				StringTokenizer st = new StringTokenizer(userInfo, "@");
				if (st.countTokens() > 1) {
					ValueObject vo = new ValueObject(id1.getText(), pw1.getText());
					arr_vo_id.add(vo);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				String testid = id1.getText();
				String testpw = pw1.getText();

				Properties properties = new Properties();
				String path = Login.class.getResource("management.menu").getPath();
				try {
					path = URLDecoder.decode(path, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					properties.load(new FileReader(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				BufferedWriter writer=null;
				try {
					writer=new BufferedWriter(new FileWriter("C:/IO/login.txt"));
					ValueObject vo = new ValueObject(testid, testpw);
					arr_vo_id.add(vo);
					
					for (ValueObject value : arr_vo_id) {
						writer.write(value.getVal1() + "@" + value.getVal2());
					}
					
					writer.close();
				} catch (IOException e1) {
				}
				
				
				String mname = properties.getProperty("name");
				String mid = properties.getProperty("id");
				String mpassword = properties.getProperty("pw");

				if (testid.equals(mid) && testpw.equals(mpassword)) {
					dialog.setSize(400,200);
					dialog.setVisible(true);
					
				} else {
					dialog2.setVisible(true);
				}

			}
		});

		p3.add(la3, BorderLayout.CENTER);
		p3.add(send, BorderLayout.SOUTH);
		p3.add(clock);
		p4.add(myWatch);
		
		
		this.add(p1);
		this.add(p0, BorderLayout.NORTH);
		this.add(p3, BorderLayout.SOUTH);
		this.add(p4, BorderLayout.EAST);

		setSize(600, 600);
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Login.this.setVisible(false);
			}
		});

	}

	class MyLoginDialog extends Dialog {

		public MyLoginDialog(Frame frame) {
			super(frame);
			Calendar cal = Calendar.getInstance();
			now2 = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "  "
					+ cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "  ";
			add(new Label("Welcome."));
			add(new Label("nice to meet you      "+now2));
			
			this.setSize(600,600);			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					main.setVisible(true);
					dispose(); // �ڿ������ϴ� ���
				}
			});

		}
	}

	class EnterDialog extends Dialog {

		public EnterDialog(Frame frame) {
			super(frame);

			add(new Label("NICE TO MEET YOU★"));

			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					enter.setVisible(true);
					dispose(); // �ڿ������ϴ� ���
				}
			});

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			Calendar cal = Calendar.getInstance();
			now = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "  "
					+ cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "";

			clock.setText(now);
			clock.setFont(new Font("Serif",Font.BOLD,12));

			try {
				Thread.sleep(1000); // 1�ʸ���
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}