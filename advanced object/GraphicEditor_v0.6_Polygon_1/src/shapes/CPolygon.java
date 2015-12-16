package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Vector;

public class CPolygon extends CShape {
	
	private Polygon polygon;

	public CPolygon() {	polygon = new Polygon();	}
	public CShape clone(){	return new CPolygon();		}
	@Override
	public void setOrigin(int x, int y) {
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y){
		polygon.addPoint(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	//	polygon.
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(polygon);	
	}
}
