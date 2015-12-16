package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics�� �׸� ����( ����, ��, ����, ���, OS���� �⺻���ΰ͵��� �����)
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel; // JPanel�� ȣ���ϸ鼭 �̹� default setting�� ��, ��ȭ�� 
import javax.swing.event.MouseInputListener;
// paint�� os->frame->�� ���� ��� �׸������� �������ش�.
public class GEPanel extends JPanel {
	
	private MouseHandler mouseHandler;
	
	public void paint(Graphics g) {
			
	}
	
	public GEPanel(){
		this.mouseHandler = new MouseHandler(); // ���콺 �ڵ鷯�� �����ϰ�
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	private void draw(int x1, int x2, int y1, int y2){
		Graphics2D g = (Graphics2D)this.getGraphics(); // �̹� paint�� ���ؼ� Graphics�� ���� �޾����Ƿ� ������ �ޱ⸸ �ϸ� �ȴ�.
		//setXORMode ������� �ٽ� �׸���. ������ ����� ������ �ٽ� �׸���.
		g.setXORMode(this.getBackground());
		g.drawRect(x1, y1, x2-x1, y2-y1);
		
	}
	
	//Ŭ�������� Ŭ���� �̳�Ŭ������� �Ѵ�. ���콺 �ڵ鷯�� ���콺 ��ǲ�����ʿ� ���콺 ��Ǹ����ʸ� ���� ���� �Ŵ�. �ٱ��� �ְ� ������ �߰��ϸ� �ȴ�.
	// MVC���� �׸��� �ϼ��Ǹ� ���� �ϼ��Ǵ� ���̴�.
	private class MouseHandler implements MouseMotionListener, MouseInputListener{
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
			x2 = e.getX();
			y2 = e.getY();
			draw(x1, x2, y1, y2);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			draw(x1, x2, y1, y2);
			x2 = e.getX();
			y2 = e.getY();
			draw(x1, x2, y1, y2);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
}
