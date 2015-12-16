package shapes;

import java.awt.Graphics;
import java.awt.Shape;

abstract public class GEShape {
	private Shape shape;
	public GEShape(Shape shape) {
		this.setShape(shape);
	}
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	abstract public void draw(Graphics g);
}
