package frames;

import javax.swing.event.MouseInputAdapter;
import constants.GEConstants;
import constants.GEConstants.EState;
import shapes.GEShape;
import shapes.GEPolygon;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.ArrayList;
import transformer.GEDrawer;
import transformer.GETransformer;
import java.awt.Color;

public class GEDrawingPanel extends JPanel {

	private GEShape currentShape, selectedShape;
	private EState currentState;
	private ArrayList<GEShape> shapeList;
	private GETransformer transformer;
	private MouseDrawingHandler drawingHandler;
	private Color lineColor, fillColor;

	public GEDrawingPanel() {
		super();
		shapeList = new ArrayList<GEShape>();
		currentState = EState.Idle;
		drawingHandler = new MouseDrawingHandler();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		lineColor = GEConstants.COLOR_LINE_DEFAULT;
		fillColor = GEConstants.COLOR_FILL_DEFAULT;
	}
	
	public void setLineColor(Color lineColor) {
		if(selectedSetColor(true, lineColor) == true){
			return;
		}
		this.lineColor = lineColor;
	}



	public void setFillColor(Color fillColor) {
		if(selectedSetColor(false, fillColor) == true){
			return;
		}
		this.fillColor = fillColor;
	}

	private boolean selectedSetColor(boolean flag, Color color){
		if(selectedShape != null){
			if(flag == true){
				selectedShape.setLineColor(color);
			}else{
				selectedShape.setFillColor(color);
			}
			repaint();
			return true;
		}
		return false;
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
		currentShape.setLineColor(lineColor);
		currentShape.setFillColor(fillColor);
		transformer = new GEDrawer(currentShape);
		transformer.init(startP);
	}
	
	
	private void continueDrawing(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	private void finishDraw(){
		shapeList.add(currentShape);
	}
	
	private GEShape onShape(Point p){
		for(int i = shapeList.size(); i > 0; i--){
			GEShape shape = shapeList.get(i-1);
			if(shape.onShape(p)){
				return shape;
			}
		}
		return null;
	}
	
	private void clearSelectedShapes(){
		for(GEShape shape : shapeList){
			shape.setSelected(false);
		}
	}
	
	private class MouseDrawingHandler extends MouseInputAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(currentState == EState.TwoPointsDrawing){
				transformer.transformer(
						(Graphics2D)getGraphics(), e.getPoint());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(currentState == EState.Idle){
				if(currentShape != null){
					clearSelectedShapes();
					selectedShape = null;
					initDraw(e.getPoint());
					if(currentShape instanceof GEPolygon){
						currentState = EState.NPointsDrawing;
					}else{
						currentState = EState.TwoPointsDrawing;
					}
				}else{
					selectedShape = onShape(e.getPoint());
					clearSelectedShapes();
					if(selectedShape != null){
						selectedShape.setSelected(true);
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(currentState == EState.TwoPointsDrawing){
				//((GEDrawer)transformer).finalize(shapeList);
				finishDraw();
				currentState = EState.Idle;
			}else if(currentState == EState.NPointsDrawing){
				return;
			}
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(currentState == EState.NPointsDrawing){
					if(e.getClickCount() == 1){
						continueDrawing(e.getPoint());
					}else if(e.getClickCount() == 2){
						//((GEDrawer)transformer).finalize(shapeList);
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
				transformer.transformer(
						(Graphics2D)getGraphics(), e.getPoint());
			}
		}		
		
	}

}
























