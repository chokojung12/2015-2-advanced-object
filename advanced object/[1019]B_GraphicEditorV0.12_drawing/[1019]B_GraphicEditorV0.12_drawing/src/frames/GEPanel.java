package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import modes.GEModels;
import shapes.GEEllipse;
import shapes.GEShape;
import transformers.GEDrawer;
import constants.GEConstant.EDrawingState;

public class GEPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	
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
	
	public GEDrawer startTransformation(int x, int y) {
		GEShape shape = new GEEllipse();
		shape.setDiagonal(x, y, x, y);
		GEDrawer transformer = new GEDrawer(this.getGraphics(), shape);
		return transformer;
	}
	public void finishTransformation(GEDrawer transformer) {
		GEModels.shapes.add(transformer.getShape());
	}
	
	private class MouseHandler 
			implements MouseInputListener, MouseMotionListener {
		
		private GEDrawer transformer;
		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				transformer = startTransformation(e.getX(), e.getY());
				eDrawingState = transformer.initDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawing) {
				eDrawingState = transformer.keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawing) {
				eDrawingState = transformer.finishDrawing(e.getX(), e.getY());
				finishTransformation(transformer);
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
