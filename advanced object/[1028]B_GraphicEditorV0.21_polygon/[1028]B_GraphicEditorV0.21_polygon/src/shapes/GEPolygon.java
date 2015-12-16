package shapes;

import java.awt.Polygon;

public class GEPolygon extends GEShape {

	private Polygon shape;
	
	public GEPolygon() {
		super(new Polygon());
	}
	@Override
	public void setPoint(int x, int y) {
		shape = (Polygon)this.getShape();
		shape.addPoint(x, y);
	}

	@Override
	public void addPoint(int x, int y) {
		shape.addPoint(x, y);
	}

	@Override
	public void movePoint(int x, int y) {
		shape.xpoints[shape.npoints-1] = x;
		shape.ypoints[shape.npoints-1] = y;		
	}
}
