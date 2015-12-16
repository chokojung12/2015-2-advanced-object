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
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentShape) { this.currentTool = currentShape; }
	private GEShape selectedShape;
	
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
			selectedShape = this.currentTool.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.currentTool.initDrawing(this.getGraphics(), x, y);	
	}
	private void keepDrawing(int x, int y) {
		this.currentTool.keepDrawing(this.getGraphics(), x, y);	
	}
	private void continueDrawing(int x, int y) {
		this.currentTool.continueDrawing(this.getGraphics(), x, y);	
	}
	private void finishDrawing(int x, int y) {
		this.currentTool.finishDrawing(this.getGraphics(), x, y);	
		this.shapes.add(currentTool);
	}
	
	private void initMoving(int x, int y) {
		this.currentTool.initMoving(this.getGraphics(), x, y);	
	}
	private void keepMoving(int x, int y) {
		this.currentTool.keepMoving(this.getGraphics(), x, y);	
	}

	private void finishMoving(int x, int y) {
		this.currentTool.finishMoving(this.getGraphics(), x, y);		
	}
	
	private GEShape onShape(int x, int y){
		for(GEShape shape: this.shapes){
			if(shape.onShape(x, y)){
				return shape;
			}
		}
		return null;
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		
		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				// 마우스 밑에 그림 체크
				selectedShape = onShape(e.getX(), e.getY());
				if(selectedShape == null){
					if(!currentTool.getClass().getSimpleName().equals("GEPolygon")){
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingNP;
					}
				}
			}else{
				initDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.drawingNP;
			}
		
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				keepDrawing(e.getX(), e.getY());
			}
			else if (eDrawingState == EDrawingState.moving) {
				keepMoving(e.getX(), e.getY());
			}	
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP && !currentTool.getClass().getSimpleName().equals("GEPolygon")) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
			else if (eDrawingState == EDrawingState.drawingNP) {	
				continueDrawing(e.getX(), e.getY());
				//keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			
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


