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
import shapes.CPolygonManager;
import shapes.CRectangleManager;
import shapes.CShapeManager;
import shapes.CShapeManager.*;
import transformer.*;

public class CDrawingPanel extends JPanel{
	// association variable
	private CShapeManager currentTool;
	// components
	private Vector<CShapeManager> shapes;
	//Utilities
	private MouseHandler mouseHandler;
	// working variable
	private EDrawingState eDrawingState;
	private CTransformer transformer;
	private CShapeManager currentShape;
	//Setters & Getters
	public void setCurrentTool(CShapeManager tool) {this.currentTool = tool;}
	
	public CDrawingPanel() {
		//Create Instances
		shapes = new Vector<CShapeManager>();
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		//Variables Initializing
		eDrawingState = eDrawingState.idle;
		transformer=null;
		currentShape=null;
		currentTool=null;
	}
	
	public void init() {		
	}
	
	@Override
	public void paint(Graphics g) {
		// Extends JPanel's paint
		super.paint(g);
		Graphics2D graphics2D = (Graphics2D) g;
		for (CShapeManager shape: shapes) {
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
	
	@SuppressWarnings("unchecked")
	public void open() {
		File file = new File(CConstans.DEFAULTFILENAME);
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			shapes = (Vector<CShapeManager>) inputStream.readObject();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void resetSelections(CShapeManager selectedShape){
		for(CShapeManager shape: shapes){
			shape.setSelected(false);
		}
		if(selectedShape!=null){
			selectedShape.setSelected(true);
		}
		repaint();
	}
	public void initDrawing(int x, int y) {
		currentShape = currentTool.clone();
		currentShape.setOrigin(x, y);
	}
	public void keepDrawing(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		currentShape.movePoint(x, y);
		currentShape.draw(g);
	}
	public void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
	}
	public void finishDrawing(int x, int y) {
	//	if(currentShape.getWidth()>1){
			this.shapes.add(currentShape);
//		} else { 
//			currentShape = null;
//		}
		resetSelections(currentShape);
	}
	public CShapeManager onShape(int x, int y){
		for(CShapeManager shape : shapes){			
			if(shape.contains(x, y)){
				currentShape = shape;
				return shape;
			}
		}
		return null;
	}
	public void initTransforming(int x, int y){
		switch(currentShape.geteTransformationType()){
		case draw:
			transformer = new CDrawer();
			break;
		case resize:
			transformer = new CResizer();
			break;
		case move:
			transformer = new CMover();
			break;
		case rotate:
			transformer = new CMover();
			break;
		}
		transformer.setShapeManager(currentShape);
		transformer.initTransforming(x, y);		
	}
	public void keepTransforming(int x, int y){
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		transformer.keepTransforming(x, y);
		currentShape.draw(g);
		repaint();
	}
	public void finishTransforming(int x, int y){
		transformer.finishTransforming(x, y);
	}
	/*public void changeCursor(){
		switch(currentShape.geteAnchorPosition()){
		
		}
		this.setCursor(currentShape.getCursor());
	}*/
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
				currentShape = onShape(e.getX(), e.getY());
				if(currentShape == null){
					resetSelections(null);
					bClicked = true;
					if(currentTool.getEDrawingType().equals(EDrawingType.NPoint)){
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.NPDrawing;
					}
				}else {
					resetSelections(currentShape);
					repaint();
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.transforming;
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
					setCursor(currentShape.getCursor());
				}else {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}else if(eDrawingState == EDrawingState.NPDrawing){		
				keepDrawing(e.getX(), e.getY());
			}
		}
		private boolean bClicked;
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {         //Origin Code for TPDrawing
				currentShape = onShape(e.getX(), e.getY());
				if(currentShape == null){
					resetSelections(null);
					bClicked = true;
					if(currentTool.getEDrawingType().equals(EDrawingType.Twopoint)){
						initDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.TPDrawing;
					}
				}else {
					resetSelections(currentShape);
					repaint();
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.transforming;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			bClicked = false;
			if (eDrawingState == EDrawingState.TPDrawing) {		//Origin Code for TPDrawing
				keepDrawing(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.transforming){
				keepTransforming(e.getX(),e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {			//Origin Code for TPDrawing
				if(!bClicked){
					finishDrawing(e.getX(), e.getY());
				}
				eDrawingState = EDrawingState.idle;
			}else if(eDrawingState == EDrawingState.transforming){ 
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
