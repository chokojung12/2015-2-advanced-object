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
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Vector;

import javax.imageio.stream.ImageOutputStream;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Libs.CConstans;
import Libs.CConstans.EDrawingState;
import shapes.CShape;




public class CDrawingPanel extends JPanel {
	//association variable
	private CShape currentTool;
	public void setCurrentTool(CShape tool) {	this.currentTool = tool;	}
	//working variable  : 계산할때 잠깐씩 필요한 변수
	private CShape currentShape;
	public CShape setcurrentShape() {	return currentShape;	}
	private EDrawingState eDrawingstate;
	//components
	private MouseHandler mouseHandler;
	private Vector<CShape> shapes;

	//1st phase Initializing
	public CDrawingPanel() {
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		shapes = new Vector<CShape>();
		eDrawingstate = EDrawingState.idle;
	}
	
	//2nd phase Initializing
	public void init(){
		
	}
	
	public void prints(String s){
		System.out.println(s);
	}

	//override method
	@Override
	public void paint(Graphics g) {
		//Extends JPanel's paint
		super.paint(g);
		
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setXORMode(getBackground());
		
		for (CShape shape: shapes) {
			shape.draw(g);
		}
	}
	
	//USER Define method
	public void save() {
		File file = new File(CConstans.DEFAULTFILENAME);
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(shapes);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	public void open() {
		File file = new File(CConstans.DEFAULTFILENAME);
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			shapes = (Vector<CShape>) inputStream.readObject();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		repaint();
	}
	
	private void draw(int x, int y, int width, int height){
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(getBackground());
		
		currentShape.draw(graphics2d);
		
	}
	
	public void setOrigin(int x, int y) {
		currentShape = currentTool.clone();
		currentShape.setOrigin(x,y);
		}

	public void keepDrawing(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
		currentShape.movePoint(x,y);
		currentShape.draw(g);
	}
	public void addPoint(int x, int y){
		currentShape.addPoint(x, y);
	}
	
	public void addShape(int x, int y) {
		// TODO Auto-generated method stub
		this.shapes.add(currentShape);
	}


	public class MouseHandler implements MouseInputListener {
		 // Enum으로 바꾸기		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1){
				if(eDrawingstate == EDrawingState.idle){
					setOrigin(e.getX(), e.getY());
					eDrawingstate = EDrawingState.NPdrawing;
				}else if( eDrawingstate == EDrawingState.NPdrawing ){
					addPoint(e.getX(), e.getY());										
				}
				
			}else if (e.getClickCount() == 2){
				if (eDrawingstate == EDrawingState.NPdrawing){
					addShape(e.getX(),e.getY());
					eDrawingstate = EDrawingState.idle;
				}
			}
		}		
		@Override
		public void mouseMoved(MouseEvent e) {
			if( eDrawingstate == EDrawingState.NPdrawing ){
				addPoint(e.getX(), e.getY());
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if(eDrawingstate == EDrawingState.idle){
				if(eDrawingstate == EDrawingState.idle){
					setOrigin(e.getX(), e.getY());
					eDrawingstate = EDrawingState.TPdrawing;
				}					
			}
		
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			//keepDrawing(e.getX(), e.getY());	
			if( eDrawingstate == EDrawingState.TPdrawing ){
				keepDrawing(e.getX(), e.getY());
			}
		
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			//finishDrawing(e.getX(),e.getY());	
			 if (eDrawingstate == EDrawingState.TPdrawing){
				 addShape(e.getX(),e.getY());
					eDrawingstate = EDrawingState.idle;
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
