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
		if(polygon.npoints<3){
			g.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		} else {
			g2D.draw(polygon);
		}
		if(anchors != null){
			anchors.draw(g2D);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		Polygon polygon = (Polygon) this.shapeUtility;
		polygon.translate(x-this.pp.x, y-this.pp.y);
		this.setPP(x, y);
		if(anchors != null){
			anchors.setBounds(shapeUtility.getBounds());
		}
	}
	@Override
	public void resizeShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rotateShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
