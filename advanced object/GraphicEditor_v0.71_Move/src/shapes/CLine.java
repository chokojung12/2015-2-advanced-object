package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import constants.CConstans.EDrawingState;

public class CLine extends CShape {

	public CLine() {
		super(EDrawingState.TPDrawing);
		this.shapeUtility = new Line2D.Double(0,0,0,0);
	}
	public CShape clone(){
		
		return new CLine();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		Line2D line = (Line2D) this.shapeUtility;
		line.setLine(x, y, x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void movePoint(int x, int y) {
		Line2D line = (Line2D) this.shapeUtility;
		line.setLine(line.getX1(), line.getY1(), x, y);
	}
	@Override
	public void draw(Graphics g) {
		Line2D line = (Line2D) this.shapeUtility;
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(line);
		if(anchors != null){
			anchors.draw(g2d);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		Line2D line = (Line2D) this.shapeUtility;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		line.setLine(line.getX1()+dx,line.getY1()+dy,line.getX2()+dx,line.getY2()+dy);
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
