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
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;



public class CDrawingPanel extends JPanel {
	//assosiation	
	private MouseHandler mouseHandler;

	
	//Components
	private String currentShape;
	private Vector<CShape> shapes;
	
	public String getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(String currentShape) {
		this.currentShape = currentShape;
	}

	
	public CDrawingPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		currentShape="regtangle";
		shapes = new Vector<CShape>();
	}
	
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setXORMode(getBackground());
		
		for (CShape shape: shapes) {
			currentShape = shape.getType();
			int x = shape.getX();
			int y = shape.getY();
			int width = shape.getW();
			int height = shape.getH();
			
			if (currentShape.equals(CConstans.buttonNames[0])) {
				graphics2D.drawRect(x, y, width, height);
			} else if (currentShape.equals(CConstans.buttonNames[1])) {
				graphics2D.drawOval(x, y, width, height);
			} else if (currentShape.equals(CConstans.buttonNames[2])) {
				graphics2D.drawLine(x, y, width + x, height + y);
			}
		}
	}
	
	public void save() {
		File file = new File("test");
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
		File file = new File("test");
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
	
	private void draw(int x, int y, int width, int height){
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(getBackground());
		if(currentShape.equals(CConstans.buttonNames[0])){
			graphics2d.drawRect(x, y, width, height);
		} else if(currentShape.equals(CConstans.buttonNames[1])){
			graphics2d.drawOval(x, y, width, height);			
		} else if(currentShape.equals(CConstans.buttonNames[2])){
			graphics2d.drawLine(x, y, width+x, height+y);
		}
		
	}

	public class MouseHandler implements MouseInputListener {
		int x1, y1;
		int x2, y2;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			x2 = e.getX();
			y2 = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			draw(x1, y1, x2-x1, y2-y1);
			x2 = e.getX();
			y2 = e.getY();
			draw(x1, y1, x2-x1, y2-y1);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			CShape shape = new CShape();
			shape.setX(x1);
			shape.setY(y1);
			shape.setW(x2-x1);
			shape.setH(y2-y1);
			shape.setType(currentShape);
			shapes.add(shape);
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
