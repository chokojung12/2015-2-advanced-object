package shapes;

import java.awt.Rectangle;

public class GERectangle extends GEShape {

	private static final long serialVersionUID = 1L;
	private Rectangle shape;
	
	public GERectangle() {
		super(new Rectangle());
		shape = (Rectangle)this.getShape();
	}
	
	@Override
	public void setPoint(int x, int y) {
		shape.setLocation(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		this.shape.setSize(x-this.shape.x, y-this.shape.y);
	}
	@Override
	public void movePoint(int x, int y) {
		this.shape.setSize(x-this.shape.x, y-this.shape.y);
	}
	@Override
	public void moveShape(int dx, int dy) {
		shape.setLocation(shape.x + dx, shape.y + dy);
	}
}
