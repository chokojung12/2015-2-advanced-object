package frames;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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

import constants.CConstans;
import constants.CConstans.*;
import shapes.CPolygon;
import shapes.CRectangle;
import shapes.CShape;
import shapes.CShape.*;

public class CDrawingPanel extends JPanel{
	// association variable
	private CShape currentTool;
	public void setCurrentTool(CShape tool) {this.currentTool = tool;}
	// working variable
	private CShape currentShape;
	private CShape selectedShape;
	private int selectedIndex;
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
		File file = new File(CConstans.DEFAULTFILENAME);
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
		File file = new File(CConstans.DEFAULTFILENAME);
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
	public void initDrawing(int x, int y) {
		currentShape = currentTool.clone();
		currentShape.setOrigin(x, y);
	}
	public void initMoving(int x, int y) {
		currentShape.setPP(x,y);		
	}
	public void keepDrawing(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		currentShape.movePoint(x, y);
		currentShape.draw(g);
	}
	public void keepMoving(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		currentShape.moveShape(x, y);
		currentShape.draw(g);
	}
	public void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
	}
	public void finishDrawing(int x, int y) {
		this.shapes.add(currentShape);
	}
	public void finishTransform(int x, int y){
		this.shapes.set(selectedIndex, selectedShape);
	}
	public CShape onShape(int x, int y){
		Graphics2D g = (Graphics2D) getGraphics();
		for(CShape shape : shapes){			
			if(shape.getShapeUtility().intersects(x,y,1,1)){
				selectedIndex = shapes.indexOf(shape);
				return shape;
			}
		}
		return null;
	}
	public class MouseHandler implements MouseInputListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==1){
				mouse1Clicked(e);
			}else if(e.getClickCount()==2){
				mouse2Clicked(e);
			}//state Diagram
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				if(currentTool.getEDrawingType().equals(EDrawingState.NPDrawing)){
					initDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.NPDrawing;
				}
			} else if (eDrawingState == EDrawingState.NPDrawing) {
				continueDrawing(e.getX(), e.getY());
			}
		}
		public void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.NPDrawing) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EDrawingState.idle){
				if(onShape(e.getX(), e.getY()) != null){
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
				}else {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}else if(eDrawingState == EDrawingState.NPDrawing){		
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {         //Origin Code for TPDrawing
				currentShape = onShape(e.getX(), e.getY());
				if(currentShape == null){
					if(currentTool.getEDrawingType().equals(EDrawingState.TPDrawing)){
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.TPDrawing;
					}
				}else {
					initMoving(e.getX(), e.getY());
					eDrawingState = EDrawingState.moving;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {		//Origin Code for TPDrawing
				keepDrawing(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.moving){
				keepMoving(e.getX(),e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {			//Origin Code for TPDrawing
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}else if(eDrawingState == EDrawingState.moving){ 
				finishTransform(e.getX(), e.getY());
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
