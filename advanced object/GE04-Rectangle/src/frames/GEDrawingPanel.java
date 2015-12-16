package frames;

import javax.swing.event.MouseInputAdapter;

import shapes.GERectangle;
import constants.GEConstants;

import javax.swing.JPanel;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GEDrawingPanel extends JPanel {
	
	private GERectangle rectangle;
	private MouseDrawingHandler drawingHandler;

	public GEDrawingPanel() {
		super();
		drawingHandler = new MouseDrawingHandler();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
	}
	private void initDraw(Point startP){
		rectangle = new GERectangle();
		rectangle.initDraw(startP);
	}
	private void animateDraw(Point currentP){
		Graphics2D g2D = (Graphics2D)getGraphics();
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(rectangle.getRectangle());
		rectangle.setCoordinate(currentP);
		g2D.draw(rectangle.getRectangle());
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
























