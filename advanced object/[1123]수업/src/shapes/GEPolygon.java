package shapes;

import java.awt.Graphics;
import java.awt.Polygon;

public class GEPolygon extends GEShape {

	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	private int x0, y0;
	public GEPolygon() {
		super(new Polygon());
		this.polygon = (Polygon)this.getShape();
	}

	@Override
	public void setPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void movePoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}


	public void moveShape(int x, int y){
		for(int i = 0; i < this.polygon.npoints; i++){
			this.polygon.xpoints[i] = this.polygon.xpoints[i];
			this.polygon.ypoints[i] = this.polygon.ypoints[i];
		}
	}

}
