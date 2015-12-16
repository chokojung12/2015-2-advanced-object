package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import constants.CConstans.EDrawingState;

public class CPolygon extends CShape {

	public CPolygon() {
		super(EDrawingState.NPDrawing);
		this.shapeUtility = new Polygon();
	}
	@Override
	public CShape clone() {
		return new CPolygon();
	}
	@Override
	public void setOrigin(int x, int y) {
		Polygon polygon = (Polygon) this.shapeUtility;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		Polygon polygon = (Polygon) this.shapeUtility;
		polygon.addPoint(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		Polygon polygon = (Polygon) this.shapeUtility;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	@Override
	public void draw(Graphics g) {
		Polygon polygon = (Polygon) this.shapeUtility;
		Graphics2D g2D = (Graphics2D) g;
		g2D.draw(polygon);
	}
	@Override
	public void moveShape(int x, int y) {
		Polygon polygon = (Polygon) this.shapeUtility;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		polygon.translate(dx, dy);
		this.setPP(x, y);
	}
}
