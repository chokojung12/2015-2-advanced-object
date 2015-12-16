package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

abstract public class GEShape {
	private Shape shape;
	
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	public GEShape(Shape shape) {
		this.shape = shape;
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(this.shape);			
	}
	abstract public void setPoint (int x, int y);
	abstract public void addPoint (int x, int y);
	abstract public void movePoint (int x, int y);
	
	abstract public void setX2(int x);
	abstract public void setY2(int y);
	abstract public void setDiagonal(int x, int y, int x2, int y2);
}
