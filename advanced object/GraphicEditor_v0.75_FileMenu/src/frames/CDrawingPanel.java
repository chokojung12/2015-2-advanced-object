package frames;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import shapes.CShapeManager;
import transformers.CTransformer;
import constants.CConstants;
import constants.CConstants.EDrawingState;
import constants.CConstants.EDrawingType;
import constants.CConstants.ETransformationState;

public class CDrawingPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// association variable
	private CShapeManager currentTool;	
	// components
	private Vector<CShapeManager> shapeManagers;
	private MouseHandler mouseHandler;
	private CTransformer transformer;

	private CCursorManager cursorManager;
	private BasicStroke dashedLineStroke;
	// working variable
	private boolean bDirty;
	
	// setters & getters
	public void setCurrentTool(CShapeManager tool) {this.currentTool = tool;}
	public Vector<CShapeManager> getShapeManagers() {return shapeManagers;}
	public void setShapeManagers(Vector<CShapeManager> shapeManagers) {
		this.shapeManagers = shapeManagers;
		repaint();
	}
	public boolean isDirty() {return bDirty;}
	public void setDirty(boolean bDirty) {this.bDirty = bDirty;}
	
	public CDrawingPanel() {
		currentTool = null;
		
		shapeManagers = new Vector<CShapeManager>();
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		transformer = null;
		
		cursorManager = new CCursorManager();
		float dashes[] = {CConstants.DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
				CConstants.DASHEDLINE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0);
		this.bDirty = false;
	}
	
	public void init() {		
	}
	
	@Override
	public void paint(Graphics g) {
		// extends JPanel's paint
		super.paint(g);		
		for (CShapeManager shapeManager: shapeManagers) {
			shapeManager.draw(g);
		}
	}
	public void clearPanel() {
		shapeManagers.clear();
		repaint();
		this.bDirty = false;
	}
	
	public CShapeManager onShape(int x, int y) {
		for (CShapeManager shapeManager: shapeManagers) {
			if (shapeManager.contains(x, y)) {
				return shapeManager;
			}
		}
		return null;
	}
	
	public void selectTransfomer(int x, int y) {
		CShapeManager currentShape = onShape(x, y);
		this.setSelected(currentShape);
		if (currentShape==null) {
			currentShape = currentTool.clone();
		}
		transformer = ETransformationState.values()[currentShape.getETransformationState().ordinal()].newTransformer();
		transformer.setShapeManager(currentShape);
		transformer.initTransforming(x, y);
	}
	
	public void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) getGraphics();
		g2D.setXORMode(getBackground());
		g2D.setStroke(dashedLineStroke);
		transformer.keepTransforming(g2D, x, y);
	}
	
	public void finishTransforming(int x, int y) {
		transformer.finishTransforming(x, y);
		if (transformer.getShapeManager().getETransformationState()==ETransformationState.draw) {
			shapeManagers.add(transformer.getShapeManager());
			this.setSelected(transformer.getShapeManager());
		} else {
			repaint();
		}
		this.bDirty = true;
	}
	private void continueTransforming(int x, int y) {
		transformer.addPoint(x, y);
	}
	
	private void setSelected(CShapeManager selectedShape) {
		if (selectedShape==null) {
			for(CShapeManager shapeManager: shapeManagers) {
				shapeManager.setSelected(false);
			}
			repaint();
		} else {
			if (!selectedShape.isSelected()) {
				for(CShapeManager shapeManager: shapeManagers) {
					shapeManager.setSelected(false);
				}
				selectedShape.setSelected(true);
				repaint();
			}
		}
	}		

	// mousehadler
	public class MouseHandler implements MouseInputListener {
		private EDrawingState eDrawingState = EDrawingState.idle;
		private boolean bClicked = false;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				selectTransfomer(e.getX(), e.getY());
				if (transformer.getShapeManager().getETransformationState()==ETransformationState.draw) {
					if (currentTool.getEDrawingType() == EDrawingType.NPoint) {
						eDrawingState = EDrawingState.NPDrawing;
					}
				}
			} else if (eDrawingState == EDrawingState.NPDrawing) {
				continueTransforming(e.getX(), e.getY());
			}
		}
		public void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.NPDrawing) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				setCursor(cursorManager.getCursor(onShape(e.getX(), e.getY())));
			} else if (eDrawingState == EDrawingState.NPDrawing) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			bClicked = true;
			if (eDrawingState == EDrawingState.idle) {
				selectTransfomer(e.getX(), e.getY());
				if (transformer.getShapeManager().getETransformationState()==ETransformationState.draw) {
					if (currentTool.getEDrawingType() == EDrawingType.TwoPoint) {
						eDrawingState = EDrawingState.TPDrawing;
					}
				} else {
					eDrawingState = EDrawingState.transforming;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			bClicked = false;
			if (eDrawingState == EDrawingState.TPDrawing || eDrawingState == EDrawingState.transforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {
				if (!bClicked) {
					finishTransforming(e.getX(), e.getY());
				}
				eDrawingState = EDrawingState.idle;
			} else if (eDrawingState == EDrawingState.transforming) {
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
