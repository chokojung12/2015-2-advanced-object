package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

abstract public class GEShape implements Cloneable {
	
	protected Shape shape;
	
	public GEShape(Shape shape) {
		this.shape = shape;
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);		
	}
	abstract public void initDrawing(Graphics g, int x, int y);
	abstract public void keepDrawing(Graphics g, int x, int y);
	abstract public void continueDrawing(Graphics g, int x, int y);
	abstract public void finishDrawing(Graphics g, int x, int y);
	
	abstract public void initMoving(Graphics graphics, int x, int y);
	abstract public void keepMoving(Graphics graphics, int x, int y);
	abstract public void finishMoving(Graphics graphics, int x, int y);
	public boolean onShape(int x, int y) {
		return this.shape.contains(x, y);
	}
}
