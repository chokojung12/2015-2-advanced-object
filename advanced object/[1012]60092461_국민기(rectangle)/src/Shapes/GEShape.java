package Shapes;

import java.awt.Graphics;
import java.awt.Shape;

abstract class GEShape {

	private Shape shape;
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {this.shape = shape;}
	public GEShape(Shape shape){this.shape = shape;}
	abstract public void draw(Graphics g);
}
