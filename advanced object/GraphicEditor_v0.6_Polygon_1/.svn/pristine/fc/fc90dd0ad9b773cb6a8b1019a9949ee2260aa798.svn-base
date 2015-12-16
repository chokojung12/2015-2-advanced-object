import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;



public class CDrawingPanel extends JPanel {
	//assosiation	
	private MouseHandler mouseHandler;
	private ComponentHandler componentHandler;
	private String currentShape;
	private Graphics graphics_b;
	private Image buffer_image,buffer;
	private int w,h;
	
	public String getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(String currentShape) {
		this.currentShape = currentShape;
	}

	
	public CDrawingPanel() {
		mouseHandler = new MouseHandler();
		componentHandler = new ComponentHandler(); 
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addComponentListener(componentHandler);
	}
	
	
	

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if(buffer_image==null){
			buffer_image = createImage(getWidth(), getHeight());
			graphics_b = buffer_image.getGraphics();
		}
		//super.update(g);
		paint(g);
	}

	@Override
	public void paint(Graphics g){
		//super.paint(g);
		g.drawImage(buffer_image, 0, 0, this);
	}
	
	private void draw(int x, int y, int width, int height){
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		if(buffer_image==null){
			buffer_image = createImage(getWidth(), getHeight());
			//buffer_image = 
			graphics_b = buffer_image.getGraphics();
			graphics_b.setXORMode(getBackground());
		}
		graphics2d.setXORMode(getBackground());
		if(currentShape==CConstans.buttonNames[0]){
			graphics2d.drawRect(x, y, width, height);
			graphics_b.drawRect(x, y, width, height);
		} else if(currentShape==CConstans.buttonNames[1]){
			graphics2d.drawOval(x, y, width, height);
			graphics_b.drawOval(x, y, width, height);
		} else if(currentShape==CConstans.buttonNames[2]){
			graphics2d.drawLine(x, y, width+x, height+y);
			graphics_b.drawLine(x, y, width+x, height+y);
		}
		//graphics.add(graphics2d);
		//paint(graphics2d);
	}
	public class ComponentHandler implements ComponentListener{
		
		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			/*if(buffer_image!=null){
				buffer=buffer_image;
				buffer_image.flush();
				buffer_image = createImage(getWidth(), getHeight());
				buffer_image;
				graphics_b = null;
				graphics_b = buffer_image.getGraphics();
				graphics_b.setXORMode(getBackground());
			}*/
		}
		
		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	public class MouseHandler implements MouseInputListener {
		
		int x1, y1, x2, y2;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x1=e.getX();
			y1=e.getY();
			x2=e.getX();
			y2=e.getY();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			draw(x1,y1,x2-x1,y2-y1);
			x2=e.getX();
			y2=e.getY();
			draw(x1,y1,x2-x1,y2-y1);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
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
