package frames;

import javax.swing.event.MouseInputAdapter;
import constants.GEConstants;
import shapes.GEShape;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.ArrayList;

public class GEDrawingPanel extends JPanel {

	private GEShape currentShape;
	private ArrayList<GEShape> shapeList;
	private MouseDrawingHandler drawingHandler;

	public GEDrawingPanel() {
		super();
		shapeList = new ArrayList<GEShape>();
		drawingHandler = new MouseDrawingHandler();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
	}
	
	public void setCurrentShape(GEShape currentShape){
		this.currentShape = currentShape;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		for(GEShape shape : shapeList){
			shape.draw(g2D);
		}
	}
	
	private void initDraw(Point startP){
		currentShape = currentShape.clone();
		currentShape.initDraw(startP);
	}
	
	private void animateDraw(Point currentP){
		Graphics2D g2D = (Graphics2D)getGraphics();      
		g2D.setXORMode(g2D.getBackground());         
		currentShape.draw(g2D);
		currentShape.setCoordinate(currentP);
		currentShape.draw(g2D);
	}
	
	private void finishDraw(){
		shapeList.add(currentShape);
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

		@Override
		public void mouseReleased(MouseEvent arg0) {
			finishDraw();
		}
		
		
	}

}
























