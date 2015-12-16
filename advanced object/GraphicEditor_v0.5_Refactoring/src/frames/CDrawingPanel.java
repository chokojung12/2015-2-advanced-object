package frames;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import shapes.CShape;
import frames.CConstans.EBUTTON;



public class CDrawingPanel extends JPanel {
	//assosiation	
	private MouseHandler mouseHandler;

	
	//Components
	private CShape currentShape;
	private Vector<CShape> shapes;
	
	public CShape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(CShape currentShape) {	this.currentShape = currentShape;	}

	
	public CDrawingPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		shapes = new Vector<CShape>();
	}
	
	public void init(){
		
	}

	@Override
	public void paint(Graphics g) {
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
			ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void open() {
		File file = new File(CConstans.DEFAULTFILENAME);
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			try {
				shapes = (Vector<CShape>) inputStream.readObject();
				inputStream.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			/*ObjectInputStream inputStream;
			try {
				inputStream = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(file)));
				try {
					shapes = (Vector<CShape>) inputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		repaint();
	}
	
	private void draw(int x, int y, int width, int height){
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(getBackground());
		
		currentShape.draw(graphics2d);
		
	}
	
	public void initDrawing(int x, int y) {
		currentShape.setX(x);
		currentShape.setY(y);
		currentShape.setW(0);
		currentShape.setH(0);
	}

	public void keepDrawing(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		
		currentShape.setW(x-currentShape.getX());
		currentShape.setH(y-currentShape.getY());
		currentShape.draw(g);
	}
	
	public void finishDrawing(int x, int y) {
		// TODO Auto-generated method stub
		this.shapes.add(currentShape);
	}


	public class MouseHandler implements MouseInputListener {
		int x1, y1;
		int x2, y2;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			initDrawing(e.getX(), e.getY());			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			keepDrawing(e.getX(), e.getY());			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			finishDrawing(e.getX(),e.getY());			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}

	
	

}
