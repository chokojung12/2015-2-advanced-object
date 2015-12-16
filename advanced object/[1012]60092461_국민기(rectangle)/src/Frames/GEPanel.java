package Frames;

import java.awt.Graphics; // Graphics는 그림 도구( 물감, 붓, 도형, 등등, OS에서 기본적인것들은 갖고옴)
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel; // JPanel을 호출하면서 이미 default setting이 됨, 도화지 

import Shapes.GEShape;
import Constant.GEConstant.EDrawingState;

public class GEPanel extends JPanel {
	
	//attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private MouseHandler mouseHandler;
	private Vector<Rectangle> rectangles;
	
	//working variable
	private Rectangle currentShape;
	
	public GEPanel(){
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.rectangles = new Vector<Rectangle>();
	}
	
	public void paint(Graphics g){
		for(GEShape shape : shapes){
			shape.draw(g);
			
		}
	}
	
	
	private void initDrawing(int x, int y){
		this.currentShape = new Rectangle(x, y, x, y);
		this.currentShape.setDiagonal(x, y, x, y);
		this.currentShape.draw(this.getGraphics());
	}
	private void keepDrawing(int x, int y){
		this.currentShape.draw(this.getGraphics());
		this.currentShape.setX2(x);
		this.currentShape.setY2(y);
		this.currentShape.draw(this.getGraphics());
	}
	private void finishDrawing(int x, int y){
		rectangles.add(currentShape);
	}
	

	private class MouseHandler implements MouseListener, MouseMotionListener{

		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1){
				mouse1Clicked(e);
			}
			else if(e.getClickCount() == 2){
				mouse2Clicked(e);
			}
			if(eDrawingState == EDrawingState.drawing){
				finishDrawing(e.getX(),e.getY());
				eDrawingState = EDrawingState.idle;
			}
			else{
				initDrawing(e.getX(),e.getY());
				eDrawingState = EDrawingState.drawing;
			}
			
		}
		private void mouse1Clicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(eDrawingState == EDrawingState.idle){
				initDrawing(e.getX(),e.getY());
				eDrawingState = EDrawingState.drawing;
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(eDrawingState == EDrawingState.idle){
				initDrawing(e.getX(),e.getY());
				eDrawingState = EDrawingState.drawing;
			}
		}
		public void mouseDragged(MouseEvent e){
			if(eDrawingState == EDrawingState.drawing){
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(eDrawingState == EDrawingState.drawing){
				finishDrawing(e.getX(),e.getY());
				eDrawingState = EDrawingState.idle;
			}
			
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
