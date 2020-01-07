package bank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class EventEx2 extends Frame {

	public MainEx main;
	public EventPerson person;
	public ArrayList<EventPerson> persons;

	public static TextField pId1;
	public static TextField gender1;
	public static TextField age1;
	public static TextField we1;
	public static TextField gift;

	EventEx2(MainEx main) {

		this.main = main;

		Panel p0 = new Panel();
		Label la1 = new Label("---------- �� ����------------");

		p0.add(la1);

		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(7, 2));

		Label pId = new Label("�ֹε�Ϲ�ȣ");
		pId1 = new TextField("", 10);

		Label gender = new Label("����");
		gender1 = new TextField("	", 10);

		Label age = new Label("����");

		// String temp_age=Integer.toString(person.getAge());

		age1 = new TextField("", 10);

		Label we = new Label("�¾�� ����");
		we1 = new TextField("", 10);

		Panel p2 = new Panel();

		gift= new TextField("",20);

	

	
		p1.add(pId);
		p1.add(pId1);
		p1.add(gender);
		p1.add(gender1);
		p1.add(age);
		p1.add(age1);
		p1.add(we);
		p1.add(we1);

		p2.add(gift);
		
		setLayout(new BorderLayout());

		Color color = new Color(242, 215, 213);

		this.setBackground(color);

		this.add(p1, BorderLayout.CENTER);
		this.add(p0, BorderLayout.NORTH);
		this.add(p2,BorderLayout.SOUTH);
		
		setSize(400, 400);
		setVisible(false);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// setVisible(false);
				dispose();
			}
		});

	}
}

class EventPerson {

	String personalId;
	String gender;
	int age;
	String weather;

	public EventPerson() {

	}

	public EventPerson(String personalId, String gender, int age, String weather) {

		this.personalId = personalId;
		this.gender = gender;
		this.age = age;
		this.weather = weather;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

}