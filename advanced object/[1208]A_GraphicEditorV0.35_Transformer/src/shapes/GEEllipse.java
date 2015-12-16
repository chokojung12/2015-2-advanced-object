package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape {
	private static final long serialVersionUID = 1L;
	
	private Ellipse2D.Double ellipse;
	public GEEllipse() {
		super(new Ellipse2D.Double(0, 0, 0, 0));
		this.ellipse = (Ellipse2D.Double) this.getShape();
	}
	
	public GEShape clone() {
		return new GEEllipse();
	}
	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		ellipse.x = x;
		ellipse.y = y;
		ellipse.width = 0;
		ellipse.height = 0;
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		ellipse.width = x-ellipse.getX();
		ellipse.height = y-ellipse.getY();
	}

	@Override
	public void moveShape(int x, int y) {
		// TODO Auto-generated method stub
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		ellipse.x+=dx;//setLocation(ellipse.x+dx, ellipse.y+dy);
		ellipse.y+=dy;
	}

	@Override
	public void resizeShape(int dw, int dh) {
		// TODO Auto-generated method stub
		this.ellipse.width = this.ellipse.width + dw;
		this.ellipse.height = this.ellipse.height + dh;
		
	}

	@Override
	public void attribute() {
		// TODO Auto-generated method stub
		
	}
	
}
