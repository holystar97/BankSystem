package bank;

import java.awt.Graphics;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

public class Watch extends JFrame implements Runnable{

	 public GregorianCalendar time;
	 public int width=300;
	 public int hour=0;
	 public int min=0;
	 public int sec=0;
	 public Graphics gp;
	public MainEx main;
	
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
	      gp.fillRect(x, y, width, width);
		
	}
	
	public void draw_time(int x, int y, int angle, String i) {
	    x = 147 + (int) (105 * Math.sin(angle * Math.PI / 180));
        y = 155 - (int) (100 * Math.cos(angle * Math.PI / 180));
        gp.drawString(i, x, y);
		
	}
	
	
	public void draw(int ox, int oy, int x, int y, int I, int angle) {
	    x = ox + (int) (I * Math.sin(angle * Math.PI / 180));
        y = oy - (int) (I * Math.cos(angle * Math.PI / 180));
        gp.drawLine(ox, oy, x, y);
		
	}
	
	public Watch() {
	//	this.main = main;
        init();
    }
 
    // frame�� �׸��� ���� �ʱ�ȭ �޼���
    public void init() {
        this.setTitle("Test Clock");
        this.setSize(width, width);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        gp = getGraphics();
    }
 
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stu
		
		
	}*/

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
