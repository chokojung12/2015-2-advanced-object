package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEShape;
import constants.GEConstant.EDrawingState;
import entity.GEModelShape;

public class GEPanel extends JPanel implements Serializable {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	
	
	public Vector<GEShape> getShapes() {return GEModelShape.shapes;}

	public void setShapes(Vector<GEShape> shapes) {GEModelShape.shapes = shapes;}
	// working variables
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	private GEShape selectedShape;
	
	public GEPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void paint(Graphics g) {
		for (GEShape shape: GEModelShape.getShapes()) {
			shape.draw(g);
		}
	}
	
	private GEShape onShape(int x, int y) {
		for (GEShape shape: GEModelShape.getShapes()) {
			if (shape.onShape(x, y)) {
				return shape;
			}
		}
		return null;		
	}
	
	private void initDrawing(int x, int y) {
		try {
			selectedShape = currentTool.getClass().newInstance();
			this.selectedShape.initDrawing(this.getGraphics(), x, y);	
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	private void keepDrawing(int x, int y) {
		this.selectedShape.keepDrawing(this.getGraphics(), x, y);	
	}
	private void continueDrawing(int x, int y) {
		this.selectedShape.continueDrawing(this.getGraphics(), x, y);	
	}
	private void finishDrawing(int x, int y) {
		this.selectedShape.finishDrawing(this.getGraphics(), x, y);	
		GEModelShape.getShapes().add(selectedShape);
	}
	
	private void initMoving(int x, int y) {
		this.selectedShape.initMoving(this.getGraphics(), x, y);	
	}
	private void keepMoving(int x, int y) {
		this.selectedShape.keepMoving(this.getGraphics(), x, y);	
	}
	private void finishMoving(int x, int y) {
		this.selectedShape.finishMoving(this.getGraphics(), x, y);	
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
			if (eDrawingState == EDrawingState.idle) {
				if (currentTool.getClass().getSimpleName().equals("GEPolygon")) {
					initDrawing(e.getX(), e.getY()); //폴리곤 생성
					eDrawingState = EDrawingState.drawingNP; //폴리곤 상태변경
				}
			} 
			else if (eDrawingState == EDrawingState.drawingNP && currentTool.getClass().getSimpleName().equals("GEPolygon")) {
				continueDrawing(e.getX(), e.getY());
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawingNP && currentTool.getClass().getSimpleName().equals("GEPolygon")) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			/*if (eDrawingState == EDrawingState.drawingNP && !currentTool.getClass().getSimpleName().equals("GEPolygon")) {
				keepDrawing(e.getX(), e.getY());
			}*/
			if (eDrawingState == EDrawingState.drawingNP && currentTool.getClass().getSimpleName().equals("GEPolygon")) {
				System.out.println("keep 드로잉 왜안되냐");
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				// 마우스 밑에 그림이 있는지 확인
				selectedShape = onShape(e.getX(), e.getY());
				// 그림이 없으면
				if (selectedShape == null) {
					// 도구가 Polygon이 아니면
					if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
						// 그림 그리기 시작
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingTP;
					}
					else{
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingNP;
					}
				// 그림이 밑에 있으면
				} 
				else {
					// 움직이기 시작
					initMoving(e.getX(), e.getY());
					eDrawingState = EDrawingState.moving;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				keepDrawing(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.moving) {
				keepMoving(e.getX(), e.getY());				
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawingTP) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			} else if (eDrawingState == EDrawingState.moving)
				finishMoving(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
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
