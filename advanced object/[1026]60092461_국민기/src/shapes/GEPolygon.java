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
		this.polygon.addPoint(x, y);
		g.drawLine(x, y, x, y);
		this.draw(g);
	}

	@Override
	public void finishDrawing(Graphics g, int x, int y) {
	}

}
