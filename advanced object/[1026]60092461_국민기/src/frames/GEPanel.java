package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEShape;
import constants.GEConstant.EDrawingState;

public class GEPanel extends JPanel implements Printable {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes;
	
	// working variables
	private GEShape currentShape;
	public void setCurrentShape(GEShape currentShape) { this.currentShape = currentShape; }

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
		try {
			this.currentShape = this.currentShape.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.currentShape.initDrawing(this.getGraphics(), x, y);	
	}
	private void keepDrawing(int x, int y) {
		this.currentShape.keepDrawing(this.getGraphics(), x, y);	
	}
	private void continueDrawing(int x, int y) {
		this.currentShape.continueDrawing(this.getGraphics(), x, y);	
	}
	private void finishDrawing(int x, int y) {
		this.currentShape.finishDrawing(this.getGraphics(), x, y);	
		this.shapes.add(currentShape);
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		
		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle && !currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				initDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawingTP;
			}
			else if (eDrawingState == EDrawingState.idle && currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				initDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawingNP;
			}
			else if(eDrawingState == EDrawingState.drawingNP){
				continueDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP && !currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				keepDrawing(e.getX(), e.getY());
			}
			else if (eDrawingState == EDrawingState.drawingNP) {
			
			}	
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP && !currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
			else if (eDrawingState == EDrawingState.drawingNP) {	
				continueDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingNP) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==1) {				
				mouse1Clicked(e);
			} else if (e.getClickCount()==2) {
				mouse2Clicked(e);				
			}
		}
		
		private void mouse1Clicked(MouseEvent e) {
			/*if (eDrawingState == EDrawingState.idle && !currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				initDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawingNP;
			} 
			else if (eDrawingState == EDrawingState.drawingNP) {
				continueDrawing(e.getX(), e.getY());
			}*/

		}
		private void mouse2Clicked(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawingNP) {
				 
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

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}


