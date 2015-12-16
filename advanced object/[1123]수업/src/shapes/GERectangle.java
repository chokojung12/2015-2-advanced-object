package shapes;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GERectangle extends GEShape {

	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	private int x0, y0;
	
	public GERectangle() {
		super(new Rectangle());
		this.rectangle = (Rectangle)this.getShape();
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.rectangle.setLocation(x, y);
	}
	
	@Override
	public void addPoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}
	@Override
	public void movePoint(int x, int y) {

	}

	@Override
	public void moveShape(int x, int y) {
		this.rectangle.setLocation(this.rectangle.x + x, this.rectangle.y + y);	
	}

}


