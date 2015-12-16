package shapes;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class GEPolygon extends GEShape {

	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	public GEPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}
	
	public GEShape clone(){
		return new GEPolygon();
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
	@Override
	public void moveShape(int x, int y) {
		/*for (int i=0; i<this.polygon.npoints; i++) {
			this.polygon.xpoints[i] = this.polygon.xpoints[i]+x;
			this.polygon.ypoints[i] = this.polygon.ypoints[i]+y;
		}*/
		this.polygon.translate(x, y);
	}
	@Override
	public void resizeShape(int dw, int dh) {
		// TODO Auto-generated method stub
		this.polygon.xpoints[this.polygon.npoints-1] += dw;
		this.polygon.ypoints[this.polygon.npoints-1] += dh;
	}

	@Override
	public void attribute() {
		// TODO Auto-generated method stub
		;
	}
	public Polygon polygonAttribute() {
		// TODO Auto-generated method stub
		return this.polygon;
	}

}
