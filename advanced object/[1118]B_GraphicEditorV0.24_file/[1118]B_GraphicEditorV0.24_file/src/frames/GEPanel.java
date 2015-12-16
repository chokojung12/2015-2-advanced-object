package frames;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import models.GEModel;
import shapes.GEShape;
import transformers.GEDrawer;
import transformers.GEMover;
import transformers.GETransformer;
import constants.GEConstant.EDrawingState;

public class GEPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;
	public boolean isUpdated() {return bUpdated;}
	
	// components
	private Vector<GEShape> shapes = new Vector<GEShape>();	
	private MouseHandler mouseHandler;
	private GETransformer transformer;

	// association objects
	private GEShape currentShape;
	public void setCurrentShape(GEShape currentShape) { this.currentShape = currentShape; }

	public GEPanel() {
		// attributes initialization
		this.bUpdated = false;		
		// components initialization
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void init() {
	}
	
	public void newFile() {
		this.shapes.removeAllElements();
		bUpdated = false;
		this.repaint();
	}
	
	public void readShapes(String fileName) {
		this.shapes = (Vector<GEShape>) GEModel.read(fileName);
		bUpdated = false;
		this.repaint();
	}

	public void saveShapes(String fileName) {
		GEModel.write(fileName, this.shapes);
		bUpdated = false;
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (GEShape shape: this.shapes) {
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
	
	public void startTransformation(int x, int y) {
		transformer.initTransforming(this.getGraphics(), x, y);
	}
	public void keepTransformation(int x, int y) {
		transformer.keepTransforming(this.getGraphics(), x, y);		
	}
	public void continueTransformation(int x, int y) {
		transformer.continueTransforming(this.getGraphics(), x, y);		
	}
	public void finishTransformation(int x, int y) {
		transformer.finishTransforming(this.getGraphics(), x, y);
		this.shapes.add(transformer.getShape());
		this.bUpdated = true;
	}
	
	private class MouseHandler 
			implements MouseInputListener, MouseMotionListener {
		
		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==1)
				mouse1Clicked(e);
			else
				mouse2Clicked(e);
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				if (currentShape.getClass().getSimpleName().equals("GEPolygon")) {
					try {
						GEShape shape = currentShape.getClass().newInstance();
						// create a transformer using the shape
						transformer = new GEDrawer(shape);
						startTransformation(e.getX(), e.getY());
						eDrawingState = EDrawingState.drawingNP;
					} catch (InstantiationException	| IllegalAccessException exception) {
						exception.printStackTrace();
					}
				}
			}
			else if (eDrawingState == EDrawingState.drawingNP) {
				continueTransformation(e.getX(), e.getY());
			}
		}
		public void mouse2Clicked(MouseEvent e) {			
			if (eDrawingState == EDrawingState.drawingNP) {
				finishTransformation(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingNP) {
				keepTransformation(e.getX(), e.getY());
			}
		}		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				GEShape shape = onShape(e.getX(), e.getY());
				if (shape == null) {
					if (!currentShape.getClass().getSimpleName().equals("GEPolygon")) {
						// create a shape
						try {
							shape = currentShape.getClass().newInstance();
							// create a transformer using the shape
							transformer = new GEDrawer(shape);
							// start drawing
							startTransformation(e.getX(), e.getY());
							eDrawingState = EDrawingState.drawingTP;
						} catch (InstantiationException	| IllegalAccessException exception) {
							exception.printStackTrace();
						}
					}
				} else {
					transformer = new GEMover(shape);
					// start drawing
					startTransformation(e.getX(), e.getY());
					eDrawingState = EDrawingState.moving;					
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				keepTransformation(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.moving) {
				keepTransformation(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.drawingTP) {
				finishTransformation(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			} else if (eDrawingState == EDrawingState.moving) {
				finishTransformation(e.getX(), e.getY());
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
