package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics�� �׸� ����( ����, ��, ����, ���, OS���� �⺻���ΰ͵��� �����)
import java.awt.Graphics2D;

import javax.swing.JPanel; // JPanel�� ȣ���ϸ鼭 �̹� default setting�� ��, ��ȭ�� 

public class GEPanel extends JPanel {
	
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.RED);
		g2D.drawRect(20, 20, 20, 20);
		g2D.drawString("son goal", 40, 40);
		
	}
	
}
