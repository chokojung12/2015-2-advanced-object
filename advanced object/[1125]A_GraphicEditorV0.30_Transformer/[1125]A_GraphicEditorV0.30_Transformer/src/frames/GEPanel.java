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
import transformer.GETransformer;
import constants.GEConstant;
import constants.GEConstant.EDrawingState;
import entity.GEModel;

public class GEPanel extends JPanel implements Cloneable{
	// attributes
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;		// fileMenu쪽 그림변화 체크
	public boolean isUpdated() {return bUpdated;}
	
	// components
	private MouseHandler mouseHandler;	//event
	private Vector<GEShape> shapes;
	public Vector<GEShape> getShapes() {return this.shapes;}
	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes;}
	
	// association variables
	private GEShape currentTool;		//GEShape association, 현재 그림
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	
	
	// working variables
	private GEShape selectedShape;	// panel에서 사용하기 위한 일회용
	private GETransformer currentTransformer; //GETransformer association, 그릴건지, 움직일건지, 로테이트 할건지 위해
	private GEUndoStack undoStack;
	
	public GEPanel() {
		// class attriutes
		bUpdated = false;	// 그림상태 초기에 false, true시 그림 변화
		//create components
		this.shapes = new Vector<GEShape>();	//그림 객체들
		mouseHandler = new MouseHandler();	
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.undoStack = new GEUndoStack();
	}
	
	public void init() {
	}
	
	public void undo(){
		if(this.shapes != null)
		this.shapes = this.undoStack.pop();
	}
	
	

	// 화면 clear 시키는애
	public void newShapes() {
		this.shapes.removeAllElements();
		this.repaint();		
	}
	
	//화면 읽는애
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
	
	//화면 저장
	public void saveShapes(String fileName) {
		try {
			GEModel.save(fileName, this.shapes);
			bUpdated = false;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 다시그리기
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
	
	// 처음 그림 시작, Graphics는 운영체제에서 갖고있는 그림도구, 그것을 전달받아서 그걸로 그림을 그림
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
		this.undoStack.push(shapes);
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
			if (eDrawingState == EDrawingState.idle) {
				// 마우스 밑에 그림이 있는지 확인
				selectedShape = onShape(e.getX(), e.getY());
				// 그림이 없으면
				
				if (selectedShape == null) {
					// 도구가 Polygon이 아니면
					try {
						selectedShape = currentTool.getClass().newInstance();
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					currentTransformer = new GEDrawer(selectedShape);
					if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
						// 그림 그리기 시작
						initTransforming(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingTP;
					}
				// 그림이 밑에 있으면
				} else {
					// 움직이기 시작
					if(selectedShape.geteSelectedAnchor() == EAnchors.MM){
						currentTransformer = new GEMover(selectedShape);
					}
					else if(selectedShape.geteSelectedAnchor() == EAnchors.RR){
						currentTransformer = new GERotator(selectedShape);
					}
					else{
						currentTransformer = new GEResizer(selectedShape);
					}
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.moving;
				}
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}




}
