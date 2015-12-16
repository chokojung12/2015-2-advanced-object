package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEShape;
import shapes.GEShape.EAnchors;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotator;
import transformer.GETransformer;
import constants.GEConstant;
import constants.GEConstant.EDrawingState;
import entity.GEModel;

public class GEPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;
	public boolean isUpdated() {return bUpdated;}
	
	// components
	private MouseHandler mouseHandler;
	
	private Vector<GEShape> shapes;
	public Vector<GEShape> getShapes() {return this.shapes;}
	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes;}
	
	// association variables
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	
	// working variables
	private GETransformer currentTransformer;
	private GEShape selectedShape;	
	
	private GEUndoStack undoStack;

	public GEPanel() {
		// class attributes
		bUpdated = false;
		// create components
		this.shapes = new Vector<GEShape>();
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.undoStack = new GEUndoStack();
	}
	public void init() {
	}
	public void redo() {
	}
	public void undo() {
		if (this.shapes!=null)
			this.shapes = this.undoStack.pop();		
	}
	public void newShapes() {
		this.shapes.removeAllElements();
		this.repaint();		
	}
	
	@SuppressWarnings("unchecked")
	public void readShapes(String fileName) {
		try {
			this.shapes = (Vector<GEShape>) GEModel.read(fileName);
			bUpdated = false;		
			this.repaint();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}	
	public void saveShapes(String fileName) {
		try {
			GEModel.save(fileName, this.shapes);
			bUpdated = false;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		for (GEShape shape: this.getShapes()) {
			shape.draw(g);
		}
	}
	// check whether the mouse pointer is on a shape or not
	private GEShape onShape(int x, int y) {
		for (GEShape shape: this.getShapes()) {
			if (shape.onShape(x, y)) {
				return shape;
			}
		}
		return null;		
	}
	
	private void initTransforming(int x, int y) {
		this.currentTransformer.initTransforming(this.getGraphics(), x, y);	
	}
	private void keepTransforming(int x, int y) {
		this.currentTransformer.keepTransforming(this.getGraphics(), x, y);	
	}
	private void continueTransforming(int x, int y) {
		this.currentTransformer.continueTransforming(this.getGraphics(), x, y);	
	}
	private void finishTransforming(int x, int y) {
		this.currentTransformer.finishTransforming(this.getGraphics(), x, y);
		this.undoStack.push(this.shapes);
		bUpdated = true;
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
					try {
						selectedShape = currentTool.getClass().newInstance();
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					currentTransformer = new GEDrawer(selectedShape);
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.drawingNP;
				}
			} else if (eDrawingState == EDrawingState.drawingNP) {
				continueTransforming(e.getX(), e.getY());
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawingNP) {
				finishTransforming(e.getX(), e.getY());
				getShapes().add(selectedShape);
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				if(onShape(e.getX(), e.getY())==null) {
					setCursor(GEConstant.DEFAULT_CURSOR);
				} else {
					setCursor(GEConstant.MOVE_CURSOR);
				}	
			} else if(eDrawingState == EDrawingState.drawingNP) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			try {
				if (eDrawingState == EDrawingState.idle) {
					// ���콺 �ؿ� �׸��� �ִ��� Ȯ��
					selectedShape = onShape(e.getX(), e.getY());
					// �׸��� ������
					if (selectedShape == null) {
						// ������ Polygon�� �ƴϸ�
						selectedShape = currentTool.getClass().newInstance();
						currentTransformer = new GEDrawer(selectedShape);
						if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
							// �׸� �׸��� ����
							initTransforming(e.getX(), e.getY());
							eDrawingState = EDrawingState.drawingTP;
						}
						// �׸��� �ؿ� ������
					} else {
						// �����̱� ����
						if (selectedShape.geteSelectedAnchor()==EAnchors.MM) {
							currentTransformer = new GEMover(selectedShape);
						} else if (selectedShape.geteSelectedAnchor()==EAnchors.RR) {
							currentTransformer = new GERotator(selectedShape);							
						} else {
							currentTransformer = new GEResizer(selectedShape);							
						}
						initTransforming(e.getX(), e.getY());
						eDrawingState = EDrawingState.moving;
					}
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.moving) {
				keepTransforming(e.getX(), e.getY());				
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawingTP) {
				finishTransforming(e.getX(), e.getY());
				getShapes().add(selectedShape);
				selectedShape.setSelected(true);
				eDrawingState = EDrawingState.idle;
			} else if (eDrawingState == EDrawingState.moving) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
