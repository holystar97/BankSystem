package bank;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BackgroundImage extends JFrame {

	JScrollPane scrollPane;
	ImageIcon icon;
	
	
	public BackgroundImage() {
		
		icon=new ImageIcon("D:/java1209/workspace/bank/bank.jpg");
		JPanel panel=new JPanel() {
		
		public void paintComponent(Graphics g) {
			
			g.drawImage(icon.getImage(),150,50,null);
			setOpaque(false);
			super.paintComponent(g);
			
		}
		
		};
		
		
		scrollPane=new JScrollPane(panel);
		setContentPane(scrollPane);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BackgroundImage frame= new BackgroundImage();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960,720);
		frame.setVisible(true);
		
	}

}
