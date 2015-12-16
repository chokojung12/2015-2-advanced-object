package Frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import Constant.GEConstant.EState;
import Shapes.GEPolygon;
import Shapes.GEShape;

public class GEPanel extends JPanel {

	private GEShape currentShape;
	private EState currentState;
	private ArrayList<GEShape> shapeList;
	private MouseDrawingHandler drawingHandler;

	public GEPanel() {
		super();
		shapeList = new ArrayList<GEShape>();
		currentState = EState.Idle;
		drawingHandler = new MouseDrawingHandler();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);

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
	
	private void continueDrawing(Point currentP){
		((GEPolygon)currentShape).continueDrawing(currentP);
	}
	
	private void finishDraw(){
		shapeList.add(currentShape);
	}
	
	private class MouseDrawingHandler extends MouseInputAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(currentState == EState.TwoPointsDrawing){
				animateDraw(e.getPoint());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(currentState == EState.Idle){
				initDraw(e.getPoint());
				if(currentShape instanceof GEPolygon){
					currentState = EState.NPointsDrawing;
				}else{
					currentState = EState.TwoPointsDrawing;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(currentState == EState.TwoPointsDrawing){
				finishDraw();
				currentState = EState.Idle;
				repaint();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(currentState == EState.NPointsDrawing){
					if(e.getClickCount() == 1){
						continueDrawing(e.getPoint());
					}else if(e.getClickCount() == 2){
						finishDraw();
						currentState = EState.Idle;
						repaint();
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(currentState == EState.NPointsDrawing){
				animateDraw(e.getPoint());
			}
		}		
		
	}

}
























