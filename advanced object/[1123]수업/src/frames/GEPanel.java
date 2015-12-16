package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEShape;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GETransformer;
import constants.GEConstant.EDrawingState;
import entity.GEModel;

public class GEPanel extends JPanel implements Printable {
	// attributes
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;
	public boolean isUpdated() {return bUpdated;}
	
	// components
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes = new Vector<GEShape>();
	public Vector<GEShape> getShapes() {return this.shapes;}
	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes;}
	
	// association variables
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	private GETransformer currentTransformer;
	
	// working variables
	private GEShape selectedShape;	

	public GEPanel() {
		bUpdated = false;		
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void init() {
	}
	// 그림 초기화
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
		super.paint(g);
		for (GEShape shape: this.getShapes()) {
			shape.draw(g);
		}
	}
	
	private GEShape onShape(int x, int y) {
		for (GEShape shape: this.getShapes()) {
			if (shape.onShape(x, y)) {
				return shape;
			}
		}
		return null;		
	}
	
	private void initTransforming(int x, int y) {
		try {
			selectedShape = currentTool.getClass().newInstance();
			this.currentTransformer = new GEDrawer(selectedShape);
			this.currentTransformer.initTransforming(this.getGraphics(), x, y);	
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	private void keepTransforming(int x, int y) {
		this.currentTransformer.keepTransforming(this.getGraphics(), x, y);	
	}
	private void continueTransforming(int x, int y) {
		this.currentTransformer.continueTransforming(this.getGraphics(), x, y);	
	}
	private void finishTransforming(int x, int y) {
		this.currentTransformer.finishTransforming(this.getGraphics(), x, y);	
		this.getShapes().add(selectedShape);
		bUpdated = true;
	}
	
	private void initMoving(int x, int y) {
		this.currentTransformer = new GEMover(selectedShape);
		this.currentTransformer.initTransfoming(this.getGraphics(), x, y);	
	}
	private void keepMoving(int x, int y) {
		this.selectedShape.keepMoving(this.getGraphics(), x, y);	
	}
	private void finishMoving(int x, int y) {
		this.selectedShape.finishMoving(this.getGraphics(), x, y);
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
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingNP) {
				//System.out.println("move");
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
					if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
						// 그림 그리기 시작
						initTransforming(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingTP;
					}
				// 그림이 밑에 있으면
				} else {
					// 움직이기 시작
					initMoving(e.getX(), e.getY());
					eDrawingState = EDrawingState.moving;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.moving) {
				keepMoving(e.getX(), e.getY());				
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			 if (eDrawingState == EDrawingState.drawingTP) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			} else if (eDrawingState == EDrawingState.moving) {
				finishMoving(e.getX(), e.getY());
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
