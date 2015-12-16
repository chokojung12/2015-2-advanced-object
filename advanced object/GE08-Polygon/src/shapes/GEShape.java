package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class GEShape {
	protected Shape myShape;
	protected Point startP;
	
	public GEShape(Shape shape){
		this.myShape = shape;
	}
	
	public void draw(Graphics2D g2D){
		g2D.draw(myShape);
	}
	
	abstract public void initDraw(Point startP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();
}
