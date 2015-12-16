package shapes;

import java.awt.Graphics;
import java.awt.Polygon;

public class GEPolygon extends GEShape {

	private Polygon polygon;
	public GEPolygon() {
		super(new Polygon());
	}

	@Override
	public void initDrawing(Graphics g, int x, int y) {
		this.polygon = (Polygon)shape;
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
		this.draw(g);
	}

	@Override
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g);
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
		this.draw(g);		
	}
	@Override
	public void continueDrawing(Graphics g, int x, int y) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void finishDrawing(Graphics g, int x, int y) {
	}

	@Override
	public void initMoving(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keepMoving(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishMoving(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
