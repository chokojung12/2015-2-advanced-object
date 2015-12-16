package shapes;

import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape {
	
	private Ellipse2D shape;
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
	}
	@Override
	public void setPoint(int x, int y) {
		shape = (Ellipse2D)this.getShape();
		shape.setFrame(x, y, 0, 0);		
	}
	@Override
	public void addPoint(int x, int y) {
		shape.setFrame(shape.getX(), shape.getY(), x-shape.getX(), y-shape.getY());		
	}
	@Override
	public void movePoint(int x, int y) {
		shape.setFrame(shape.getX(), shape.getY(), x-shape.getX(), y-shape.getY());		
	}
}
