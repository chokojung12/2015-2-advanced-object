package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import modes.GEModels;
import shapes.GEShape;
import transformers.GEDrawer;
import constants.GEConstant.EDrawingState;

public class GEPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	
	// working objects
	private GEDrawer transformer;
	private GEShape currentShape;
	public void setCurrentShape(GEShape currentShape) { this.currentShape = currentShape; }
	
	public GEPanel() {
		// attributes initialization
		
		// components initialization
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void paint(Graphics g) {
		for (GEShape shape: GEModels.shapes) {
			shape.draw(g);
		}
	}
	
	public void startTransformation(int x, int y) {
		try {
			// create a shape
			GEShape shape = this.currentShape.getClass().newInstance();
			// create a transformer using the shape
			transformer = new GEDrawer(shape);
			// start drawing
			transformer.initDrawing(this.getGraphics(), x, y);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public void keepTransformation(int x, int y) {
		transformer.keepDrawing(this.getGraphics(), x, y);		
	}
	public void continueTransformation(int x, int y) {
		transformer.continueDrawing(this.getGraphics(), x, y);		
	}
	public void finishTransformation(int x, int y) {
		transformer.finishDrawing(this.getGraphics(), x, y);
		GEModels.shapes.add(transformer.getShape());
	}
	
	private class MouseHandler 
			implements MouseInputListener, MouseMotionListener {
		
		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==1)
				mouse1Clicked(e);
			else
				mouse2Clicked(e);
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle && currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				startTransformation(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawingNP;
			}
			else if (eDrawingState == EDrawingState.drawingNP) {
				continueTransformation(e.getX(), e.getY());
			}
		}
		public void mouse2Clicked(MouseEvent e) {			
			if (eDrawingState == EDrawingState.drawingNP) {
				finishTransformation(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingNP) {
				keepTransformation(e.getX(), e.getY());
			}
		}		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle && !currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				startTransformation(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawingTP;
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				keepTransformation(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				finishTransformation(e.getX(), e.getY());
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
