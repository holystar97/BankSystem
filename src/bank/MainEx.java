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
		img = tool.getImage("D:/java1209/workspace/bank/bank.jpg");
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
		mb2 = new JButton("일반모드");
		mb3 = new JButton("상담사모드");

		p0 = new Panel(); // ó��
		p0.setBackground(Color.WHITE);

		p1 = new Panel(); // ������ ���
		p1.setBackground(Color.WHITE);
		p2 = new Panel(); // �� ���
		p2.setBackground(Color.WHITE);
		p3 = new Panel(); // ������
		p3.setBackground(Color.WHITE);

		p_main = new Panel(); // ���� �г�
		p_main.setLayout(cards);

		////////////////////// ùȭ��

		// p0.setLayout(new BorderLayout());
		// la=new Label("mk bank�� ���Ű��� ȯ���մϴ�.");
		p0.add(mb1);
		p0.add(mb2);
		p0.add(mb3);

		// p0.add(la,BorderLayout.CENTER);

		/////////////////////// ������ ���

		b = new Button[11];

		// la1=new Label("<<<������ ���>>>");

		b[0] = new Button("Login");

		b[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login.setVisible(true);

			}
		});

		b[1] = new Button("Chat(Server)");
		b[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cs.setVisible(true);
			}
		});
		
		b[2] = new Button("Chat(Client)");
		b[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cc.setVisible(true);
				
			}
		});
		
		
		b[3] = new Button("View_all");

		b[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list.setVisible(true);
				if (AL != null && AL.size() > 0) {
					custsList(list.ta);
				} else {
					list.ta.setText("No customer");
				}

			}
		});

		b[4] = new Button("Deposit");

		b[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deposit.setVisible(true);

			}
		});

		b[5] = new Button("Withdraw");

		b[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				withdraw.setVisible(true);

			}
		});

		b[6] = new Button("Transfer");

		b[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				transfer.setVisible(true);
				if (AL != null && AL.size() > 0) {
					custsAccount(transfer.ta);
				} else {
					transfer.ta.setText("Complete");
				}

			}
		});

		b[7] = new Button("Balance");

		b[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				balance.setVisible(true);

			}
		});

		b[8] = new Button("Event");

		b[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				event.setVisible(true);

			}
		});

		b[9] = new Button("Note");

		b[10] = new Button("Counselor");

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
		img = tool.getImage("D:/java1209/workspace/bank/bank.jpg");

		p1.add(myCanvas);
		// p1.add(myCanvas,"Center");

		/////////////////////// �Ϲݰ����

		b1 = new Button[7];

		// p2.setLayout(new GridLayout(4,3));

		// la2=new Label("<<<�Ϲ� �� ���>>>");

		b1[0] = new Button("������ �Է�");
		b1[1] = new Button("������ ���");
		b1[2] = new Button("����");
		b1[3] = new Button("���");
		b1[4] = new Button("��ü");
		b1[5] = new Button("�ܰ�");
		b1[6] = new Button("����");

		// p2.add(la2);
		for (int i = 0; i < b1.length; i++) {
			p2.add(b1[i]);
		}

		myCanvas = new MyCanvas();
		tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("D:/java1209/workspace/bank/bank.jpg");

		p2.add(myCanvas);

		/////////////////////// ������

		b2 = new Button[4];

		// la3=new Label("<<<������>>>");

		b2[0] = new Button("");
		b2[1] = new Button("������");
		b2[2] = new Button("ä��");
		b2[3] = new Button("��ġ");

		for (int i = 0; i < b2.length; i++) {
			p3.add(b2[i]);
		}

		myCanvas = new MyCanvas();
		tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("D:/java1209/workspace/bank/bank.jpg");

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

		/////////////////////// �޴� ��

		mb = new MenuBar();
		m1 = new Menu("Manager Mode");
		mi1 = new MenuItem[7];
		mi1[0] = new MenuItem("Login");
		mi1[1] = new MenuItem("Chatting");
		mi1[2] = new MenuItem("View_All");
		mi1[3] = new MenuItem("Event");
		mi1[4] = new MenuItem("Note");
		mi1[5] = new MenuItem("Withdraw");
		mi1[6] = new MenuItem("Balance");

		for (int i = 0; i < mi1.length; i++) {
			m1.add(mi1[i]);
			mi1[i].addActionListener(this);
			// mi[i].addActionListener(this);
			if (i == 1 || i == 4 || i == 5)
				m1.addSeparator();
		}
		// mi[1].setEnabled(false); // ������Ʈ�� ��Ȱ��ȭ ��Ű�� �༮

		m2 = new Menu("User Mode");
		mi2 = new MenuItem[6];
		mi2[0] = new MenuItem("������ �Է�");
		mi2[1] = new MenuItem("����");
		mi2[2] = new MenuItem("���");
		mi2[3] = new MenuItem("��ü");
		mi2[4] = new MenuItem("�ܰ�");
		mi2[5] = new MenuItem("����");

		for (int i = 0; i < mi2.length; i++) {
			m2.add(mi2[i]);
			mi2[i].addActionListener(this);
			// mi[i].addActionListener(this);
			if (i == 0 || i == 4)
				m2.addSeparator();
		}

		m3 = new Menu("Counselor Mode");
		mi3 = new MenuItem[3];
		mi3[0] = new MenuItem("�̺�Ʈ");
		mi3[1] = new MenuItem("������");
		mi3[2] = new MenuItem("ä��");

		m4 = new Menu("Planner");
		mi4 = new MenuItem[3];
		mi4[0] = new MenuItem("�޷�");
		mi4[1] = new MenuItem("��ø");
		mi4[2] = new MenuItem("�ð�");

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

		
		Iterator<Customers> data = AL.iterator();
		while (data.hasNext()) {
			Customers cust = data.next();
			ta.append("Name: ");
			ta.append(cust.getName());
			ta.append("\n\n");
			ta.append("Id: ");
			ta.append(cust.getId());
			ta.append("\n\n");
			ta.append("Password: ");
			ta.append(cust.getPw());
			ta.append("\n\n");
			ta.append("Phone: ");
			ta.append(cust.getPhone());
			ta.append("\n\n");
			ta.append("Email: ");
			ta.append(cust.getEmail());
			ta.append("\n\n");
			ta.append("Account: ");
			ta.append(cust.getAccount());
			ta.append("\n\n");
			ta.append("Money: ");
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
