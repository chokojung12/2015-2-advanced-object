package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics는 그림 도구( 물감, 붓, 도형, 등등, OS에서 기본적인것들은 갖고옴)
import java.awt.Graphics2D;

import javax.swing.JPanel; // JPanel을 호출하면서 이미 default setting이 됨, 도화지 

public class GEPanel extends JPanel {
	
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.RED);
		g2D.drawRect(20, 20, 20, 20);
		g2D.drawString("son goal", 40, 40);
		
	}
	
}
