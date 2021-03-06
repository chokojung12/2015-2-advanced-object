package frames;

import javax.swing.event.MouseInputAdapter;
import constants.GEConstants;
import shapes.GEShape;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GEDrawingPanel extends JPanel {

	private GEShape currentShape;
	private MouseDrawingHandler drawingHandler;

	public GEDrawingPanel() {
		super();
		drawingHandler = new MouseDrawingHandler();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
	}
	
	public void setCurrentShape(GEShape currentShape){
		this.currentShape = currentShape;
	}
	
	private void initDraw(Point startP){
		currentShape.initDraw(startP);
	}
	
	private void animateDraw(Point currentP){
		Graphics2D g2D = (Graphics2D)getGraphics();      
		g2D.setXORMode(g2D.getBackground());         
		currentShape.draw(g2D);
		currentShape.setCoordinate(currentP);
		currentShape.draw(g2D);
	}
	
	private class MouseDrawingHandler extends MouseInputAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			animateDraw(e.getPoint());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			initDraw(e.getPoint());
		}

	}

}
























