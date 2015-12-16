package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics는 그림 도구( 물감, 붓, 도형, 등등, OS에서 기본적인것들은 갖고옴)
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel; // JPanel을 호출하면서 이미 default setting이 됨, 도화지 
// paint는 os->frame->나 한테 계속 그림도구를 전달해준다.
public class GEPanel extends JPanel {
	
	private MouseListener mouseListener;
	
	public void paint(Graphics g) {
			
	}
	
	public GEPanel(){
		this.mouseListener = new MouseHandler(); // 마우스 핸들러를 생성하고
		this.addMouseListener(mouseListener); // 마우스 핸들러를 panel에 등록한다.
	}
	
	private void draw(int x1, int x2, int y1, int y2){
		Graphics g = this.getGraphics(); // 이미 paint를 통해서 Graphics를 전달 받았으므로 내려서 받기만 하면 된다.
		//g.setColor(Color.BLACK);
		g.fillRect(x1, y1, x2 - x1, y2 - y1);
		
	}
	
	//클래스안의 클래스 이너클래스라고 한다. MouseListener가 인터페이스이기 때문에 MouseHandler안에 MouseListener의 모든 함수가 정의되있어야한다.
	private class MouseHandler implements MouseListener{
		int x1, y1, x2, y2;
		@Override // MouseEvent arg0 여기에 마우스 이벤트 발생위치등 정보들이 있다.
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
