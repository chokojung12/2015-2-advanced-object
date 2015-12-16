package shapes;

import java.awt.Rectangle;

public class GERectangle extends GEShape {
	
	private Rectangle shape;
	
	public GERectangle() {
		super(new Rectangle());
	}
	@Override
	public void setPoint(int x, int y) {
		shape = (Rectangle)this.getShape();
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
