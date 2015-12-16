package frames;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import shapes.CRectangle;
import shapes.CShape;
import frames.CConstants.EButton;
import frames.CConstants.EDrawingState;

public class CDrawingPanel extends JPanel{
	// association variable
	private CShape currentTool;
	public void setCurrentTool(CShape tool) {this.currentTool = tool;}
	// working variable
	private CShape currentShape;
	private MouseHandler mouseHandler;
	private EDrawingState eDrawingState;
	// components
	private Vector<CShape> shapes;
	
	public CDrawingPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		shapes = new Vector<CShape>();
		eDrawingState = EDrawingState.idle;
	}
	
	public void init() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		// extends JPanel's paint
		super.paint(g);
		
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setXORMode(getBackground());
		
		for (CShape shape: shapes) {
			shape.draw(g);
		}
	}
	
	public void save() {
		File file = new File(CConstants.DEFAULTFILENAME);
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(shapes);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void open() {
		File file = new File(CConstants.DEFAULTFILENAME);
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			shapes = (Vector<CShape>) inputStream.readObject();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	public void draw(int x, int y, int width, int height) {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		graphics2D.setXORMode(getBackground());
		
		currentShape.draw(graphics2D);
	}
	
	public void setOrgin(int x, int y) {
		currentShape = currentTool.clone();
		currentShape.setOrgin(x, y);
	}
	
	public void keepDrawing(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		currentShape.movePoint(x, y);
		currentShape.draw(g);
	}
	
	public void addPoint(int x, int y) {
		currentShape.addPoint(x, y);
	}

	public void addShape(int x, int y) {
		this.shapes.add(currentShape);
	}
	
	public class MouseHandler implements MouseInputListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				if (eDrawingState == EDrawingState.idle) {
					setOrgin(e.getX(), e.getY());
					eDrawingState = EDrawingState.NPDrawing;
				} else if (eDrawingState == EDrawingState.NPDrawing) {
					addPoint(e.getX(), e.getY());
				}
			} else if (e.getClickCount() == 2) {
				if (eDrawingState == EDrawingState.NPDrawing) {
					addShape(e.getX(), e.getY());
					eDrawingState = EDrawingState.idle;
				}
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.NPDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				setOrgin(e.getX(), e.getY());
				eDrawingState = EDrawingState.TPDrawing;
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {
				addShape(e.getX(), e.getY());
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
