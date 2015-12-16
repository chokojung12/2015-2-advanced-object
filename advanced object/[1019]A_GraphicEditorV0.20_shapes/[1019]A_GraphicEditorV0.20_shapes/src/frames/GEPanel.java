package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;


//import shapes.GEEllipse;
import shapes.GERectangle;
import shapes.GEShape;
import constants.GEConstant.EDrawingState;


public class GEPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes;
	
	// working variables
	private GERectangle currentShape;

	public GEPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.shapes = new Vector<GEShape>();
	}
	
	public void paint(Graphics g) {
		for (GEShape shape: shapes) {
			shape.draw(g);
		}
	}
	
	private void initDrawing(int x, int y) {
		this.currentShape = new GERectangle();
		this.currentShape.setDiagonal(x, y, x, y);
		this.currentShape.draw(this.getGraphics());	
	}
	private void keepDrawing(int x, int y) {
		this.currentShape.draw(this.getGraphics());	
		this.currentShape.setX2(x);
		this.currentShape.setY2(y);
		this.currentShape.draw(this.getGraphics());	
	}
	private void finishDrawing(int x, int y) {
		this.shapes.add(currentShape);
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {

		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==1) {
				
				
				mouse1Clicked(e);
			} else if (e.getClickCount()==2) {
				mouse2Clicked(e);				
			}
		}
		private void mouse1Clicked(MouseEvent e) {
		}
		private void mouse2Clicked(MouseEvent e) {
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				initDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawing;
			}			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawing) {
					finishDrawing(e.getX(), e.getY());
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
