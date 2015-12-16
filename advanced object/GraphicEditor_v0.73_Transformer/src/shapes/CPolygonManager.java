package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import constants.CConstans.EAnchorPosition;
import constants.CConstans.EDrawingType;

public class CPolygonManager extends CShapeManager {

	public CPolygonManager() {
		super(EDrawingType.NPoint);
		this.shape = new Polygon();
	}
	@Override
	public CShapeManager clone() {
		return new CPolygonManager();
	}
	@Override
	public void setOrigin(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
		
	}
	@Override
	public void draw(Graphics g) {
		Polygon polygon = (Polygon) this.shape;
		Graphics2D g2D = (Graphics2D) g;
		if(polygon.npoints<3){
			g.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		} else {			
			g2D.draw(polygon);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.translate(x-this.pp.x, y-this.pp.y);
		this.setPP(x, y);
		this.setAnchorsBounds();
	}
	@Override
	public void resizeShape(int x, int y) {
		/*Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		Polygon polygon = (Polygon) this.shape;
	    int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		Rectangle bounds = polygon.getBounds();
	    transform.scale((bounds.getWidth()+dx)/bounds.getWidth(), (bounds.getHeight()+dy)/bounds.getHeight());
	    g2d.setTransform(transform);
		EAnchorPosition position = super.geteAnchorPosition();*/
	}
	@Override
	public void rotateShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
