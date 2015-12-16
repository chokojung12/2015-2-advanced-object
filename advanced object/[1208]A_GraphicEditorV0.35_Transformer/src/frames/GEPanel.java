package frames;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEGroup;
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

public class GEPanel extends JPanel implements Printable {
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
	private boolean cutState = false;


	public boolean isCutState() {return cutState;}
	public void setCutState(boolean cutState) {this.cutState = cutState;}

	
	public void setSelectedShape(GEShape selectedShape) {
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(false);
		}
		this.selectedShape = selectedShape;
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(true);
		}
	}
	public GEShape getSelectedShape() {return this.selectedShape; }
	
	
	private GEUndoStack undoStack;

	public GEUndoStack getUndoStack() {return undoStack;}
	public void setUndoStack(GEUndoStack undoStack) {this.undoStack = undoStack;}
	
	public GEPanel() {
		super();
		// class attributes
		bUpdated = false;
		// create components
		this.shapes = new Vector<GEShape>();
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.undoStack = new GEUndoStack();
		GEConstant.VARIATION = 0;

	}
	public void init() {
	}
	public void redo() {
	}
	public void undo() {
		if (this.shapes!=null){
			Vector<GEShape> temp = new Vector<GEShape>();
			temp = this.undoStack.pop();
			if(temp == null){
				return ;
			}
			this.shapes = temp;
		}
	}
	public void resetSelections(GEShape selectedShape){
		for(GEShape shape: shapes){
			shape.setSelected(false);
		}
		if(selectedShape!=null){
			selectedShape.setSelected(true);
		}
		repaint();
	}
	public void newShapes() {
		this.shapes.removeAllElements();
		this.undoStack = new GEUndoStack();
		GEConstant.VARIATION = 0;
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
		super.paint(g);
		for (GEShape shape: this.getShapes()) {
			shape.draw(g);
		}
	}
	
	public Graphics2D getGraphic(){
		Graphics2D g = (Graphics2D) getGraphics();
		return g;
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
	
	
	
	//??
	private void setSelected() {
		for (GEShape shape: this.shapes) {
			shape.setSelected(false);
		}
		this.getSelectedShape().setSelected(true);
	}

	
	private void initTransforming(int x, int y) {
		this.currentTransformer.initTransforming(this.getGraphics(), x, y);
		++GEConstant.VARIATION;
	}
	private void keepTransforming(int x, int y) {
		this.currentTransformer.keepTransforming(this.getGraphics(), x, y);	
	}
	private void continueTransforming(int x, int y) {
		this.currentTransformer.continueTransforming(this.getGraphics(), x, y);	
	}
	private void finishTransforming(int x, int y) {
		this.currentTransformer.finishTransforming(this.getGraphics(), x, y);
		bUpdated = true;
		this.setSelected();
		this.undoStack.push(this.shapes);
		this.repaint();
		
		if (this.currentTransformer.getClass().getSimpleName().equals("GEDrawer")) {
			if (this.getSelectedShape().getClass().getSimpleName().equals("GEGroup")) {
				GEGroup group = (GEGroup) this.getSelectedShape();
				for (GEShape shape: this.shapes) {
					if (group.addShape(shape)) {
						this.shapes.remove(shape);
					}
				}
			}
			this.getShapes().add(getSelectedShape());
		}
	}
	// 커서결정
	private void setCursor(int x, int y) {
		GEShape shape = onShape(x, y);
		if (shape == null) {
			this.setCursor(GEConstant.DEFAULT_CURSOR);
		} else {
			switch (shape.geteSelectedAnchor()) {
			case EE: setCursor(GEConstant.EE_RESIZE_CURSOR); break;
			case WW: setCursor(GEConstant.WW_RESIZE_CURSOR); break;
			case SS: setCursor(GEConstant.SS_RESIZE_CURSOR); break;
			case NN: setCursor(GEConstant.NN_RESIZE_CURSOR); break;
			case NE: setCursor(GEConstant.NE_RESIZE_CURSOR); break;
			case NW: setCursor(GEConstant.NW_RESIZE_CURSOR); break;
			case SE: setCursor(GEConstant.SE_RESIZE_CURSOR); break;
			case SW: setCursor(GEConstant.SW_RESIZE_CURSOR); break;
			case RR: setCursor(GEConstant.RR_CURSOR); 		 break;
			case MM: setCursor(GEConstant.MM_CURSOR);		 break;
			}			
		}
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
						// currentTool도형을 setSelectedShape에 넣어주고
						setSelectedShape(currentTool.getClass().newInstance());

					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					currentTransformer = new GEDrawer(getSelectedShape());
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
				//getShapes().add(getSelectedShape());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				setCursor(e.getX(), e.getY());
			} else if(eDrawingState == EDrawingState.drawingNP) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			try {
				if (eDrawingState == EDrawingState.idle) {
					// 마우스 밑에 그림이 있는지 확인
					setSelectedShape(onShape(e.getX(), e.getY()));
					// 그림이 없으면
					if (getSelectedShape() == null) {
						// 도구가 Polygon이 아니면
						setSelectedShape(currentTool.getClass().newInstance());

						currentTransformer = new GEDrawer(getSelectedShape());
						
						if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
							// 그림 그리기 시작
							initTransforming(e.getX(), e.getY());
							eDrawingState = EDrawingState.drawingTP;
						}
						
					}
					// 그림이 밑에 있으면
					else {							
						// 움직이기 시작	
						if (getSelectedShape().geteSelectedAnchor()==EAnchors.MM) {
							currentTransformer = new GEMover(getSelectedShape());
						} else if (getSelectedShape().geteSelectedAnchor()==EAnchors.RR) {
							currentTransformer = new GERotator(getSelectedShape());							
						} else {
							currentTransformer = new GEResizer(getSelectedShape());							
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
	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
