package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class CPolygon extends CShape {
	private Polygon polygon;
	
	public CPolygon() {
		polygon = new Polygon();
	}
	@Override
	public CShape clone() {
		return new CPolygon();
	}
	@Override
	public void setOrgin(int x, int y) {
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		polygon.addPoint(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.draw(polygon);
	}
}
