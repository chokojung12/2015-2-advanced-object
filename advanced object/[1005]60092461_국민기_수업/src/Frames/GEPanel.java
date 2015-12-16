package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics�� �׸� ����( ����, ��, ����, ���, OS���� �⺻���ΰ͵��� �����)
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel; // JPanel�� ȣ���ϸ鼭 �̹� default setting�� ��, ��ȭ�� 
// paint�� os->frame->�� ���� ��� �׸������� �������ش�.
public class GEPanel extends JPanel {
	
	private MouseListener mouseListener;
	
	public void paint(Graphics g) {
			
	}
	
	public GEPanel(){
		this.mouseListener = new MouseHandler(); // ���콺 �ڵ鷯�� �����ϰ�
		this.addMouseListener(mouseListener); // ���콺 �ڵ鷯�� panel�� ����Ѵ�.
	}
	
	private void draw(int x1, int x2, int y1, int y2){
		Graphics g = this.getGraphics(); // �̹� paint�� ���ؼ� Graphics�� ���� �޾����Ƿ� ������ �ޱ⸸ �ϸ� �ȴ�.
		//g.setColor(Color.BLACK);
		g.fillRect(x1, y1, x2 - x1, y2 - y1);
		
	}
	
	//Ŭ�������� Ŭ���� �̳�Ŭ������� �Ѵ�. MouseListener�� �������̽��̱� ������ MouseHandler�ȿ� MouseListener�� ��� �Լ��� ���ǵ��־���Ѵ�.
	private class MouseHandler implements MouseListener{
		int x1, y1, x2, y2;
		@Override // MouseEvent arg0 ���⿡ ���콺 �̺�Ʈ �߻���ġ�� �������� �ִ�.
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x1 = e.getX();
			y1 = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			x2 = e.getX();
			y2 = e.getY();
			draw(x1, x2, y1, y2);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
}
