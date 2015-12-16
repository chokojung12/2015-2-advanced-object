package shapes;

import java.awt.Rectangle;

public class GERectangle extends GEShape {

	private static final long serialVersionUID = 1L;
	protected Rectangle rectangle;
	
	public GERectangle() {
		super(new Rectangle());
		this.rectangle = (Rectangle) this.getShape();
		
	}
	
	public GEShape clone(){
		return new GERectangle();
	}
	
	@Override
	public void setPoint(int x, int y) {
		rectangle.setBounds(x, y, 0, 0);
		//this.setPP(x, y);
	}
	public void addPoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}
	@Override
	public void movePoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}
	@Override
	public void moveShape(int x, int y) {
		this.rectangle.setLocation(this.rectangle.x+x, this.rectangle.y+y);		
	}
	public void resizeShape(int dw, int dh) {
		this.rectangle.width =	this.rectangle.width + dw;
		this.rectangle.height =	this.rectangle.height + dh;
	}
	
}


