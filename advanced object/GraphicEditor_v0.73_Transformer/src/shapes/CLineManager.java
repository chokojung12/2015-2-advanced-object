package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import constants.CConstans.EDrawingType;

public class CLineManager extends CShapeManager {

	public CLineManager() {
		super(EDrawingType.Twopoint);
		this.shape = new Line2D.Double(0,0,0,0);
	}
	public CShapeManager clone(){
		
		return new CLineManager();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(x, y, x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void movePoint(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(line.getX1(), line.getY1(), x, y);
	}
	@Override
	public void moveShape(int x, int y) {
		Line2D line = (Line2D) this.shape;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		line.setLine(line.getX1()+dx,line.getY1()+dy,line.getX2()+dx,line.getY2()+dy);
		this.setPP(x, y);
		this.setAnchorsBounds();
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
