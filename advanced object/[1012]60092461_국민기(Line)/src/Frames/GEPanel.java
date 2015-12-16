package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics는 그림 도구( 물감, 붓, 도형, 등등, OS에서 기본적인것들은 갖고옴)
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel; // JPanel을 호출하면서 이미 default setting이 됨, 도화지 
import javax.swing.event.MouseInputListener;

import Constant.GEConstant.EDrawingState;
import java.awt.Point;
import java.awt.geom.Line2D;
public class GEPanel extends JPanel {
	
	private MouseHandler mouseHandler;
	private Vector<Line> Lines;
	private Line currentShape;
	private Line2D line;
	
	public GEPanel(){
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.Lines = new Vector<Line>();
	}
	
	public void paint(Graphics g){
		for(Line shape : Lines){
			shape.draw(this);
		}
	}
	/*

	public class GELine {
		private Point startP;
		private Line2D line;

		public GELine(){
			line = new Line2D.Double();
		}

		public void initDraw(Point startP){
			this.startP = startP;
		}

		public void setCoordinate(Point currentP){
			line.setLine(startP.x, startP.y, currentP.x,  currentP.y);
		}

		public Line2D getLine() {
			return line;
		}
	}*/
	
	public void draw(int x1, int y1, int x2, int y2) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		g2D.drawRect(x1, y1, x2-x1, y2-y1);
	}
	
	private void initDrawing(int x, int y){
		this.currentShape = new Line(x, y, x, y);
		this.currentShape.draw(this);
	}
	private void keepDrawing(int x, int y){
		this.currentShape.draw(this);
		this.currentShape.setX2(x);
		this.currentShape.setY2(y);
		this.currentShape.draw(this);
	}
	private void finishDrawing(int x, int y){
		Lines.add(currentShape);
	}
	
	private class Rectangle{
		int x1, y1, x2, y2;
		public Rectangle(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
		public int getX1() {return x1;}
		public void setX1(int x1) {this.x1 = x1;}
		public int getY1() {return y1;}
		public void setY1(int y1) {this.y1 = y1;}
		public int getX2() {return x2;}
		public void setX2(int x2) {this.x2 = x2;}
		public int getY2() {return y2;}
		public void setY2(int y2) {this.y2 = y2;}
		
		public void draw(JPanel panel){
			Graphics2D g2D = (Graphics2D)panel.getGraphics();
			g2D.setXORMode(panel.getBackground());
			g2D.drawRect(x1, y1, x2-x1, y2-y1);
		}
	}
	
	private class Line{
		int x1, y1, x2, y2;
		public Line(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
		public int getX1() {return x1;}
		public void setX1(int x1) {this.x1 = x1;}
		public int getY1() {return y1;}
		public void setY1(int y1) {this.y1 = y1;}
		public int getX2() {return x2;}
		public void setX2(int x2) {this.x2 = x2;}
		public int getY2() {return y2;}
		public void setY2(int y2) {this.y2 = y2;}
		
		public void draw(JPanel panel){
			Graphics2D g2D = (Graphics2D)panel.getGraphics();
			g2D.setXORMode(panel.getBackground());
			g2D.drawLine(x1, y1, x2, y2);
		}
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
		
		private void mouse2Clicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		public void mouseMoved(MouseEvent e) {
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


		
	}
}
