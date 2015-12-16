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

public class GEPanel extends JPanel implements Serializable {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes;
	
	public Vector<GEShape> getShapes() {return shapes;}

	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes;}
	// working variables
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
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
	
	private GEShape onShape(int x, int y) {
		for (GEShape shape: this.shapes) {
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
		this.shapes.add(selectedShape);
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
					initDrawing(e.getX(), e.getY()); //������ ����
					eDrawingState = EDrawingState.drawingNP; //������ ���º���
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
				System.out.println("keep ����� �־ȵǳ�");
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				// ���콺 �ؿ� �׸��� �ִ��� Ȯ��
				selectedShape = onShape(e.getX(), e.getY());
				// �׸��� ������
				if (selectedShape == null) {
					// ������ Polygon�� �ƴϸ�
					if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
						// �׸� �׸��� ����
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingTP;
					}
					else{
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingNP;
					}
				// �׸��� �ؿ� ������
				} 
				else {
					// �����̱� ����
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