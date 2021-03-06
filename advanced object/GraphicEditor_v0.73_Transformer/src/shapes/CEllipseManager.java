package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import constants.CConstans.EDrawingType;

public class CEllipseManager extends CShapeManager {

	public CEllipseManager() {
		super(EDrawingType.Twopoint);
		this.shape = new Ellipse2D.Double(0, 0, 0, 0);
	}
	public CShapeManager clone(){
		
		return new CEllipseManager();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shape;
		ellipse.x = x;
		ellipse.y = y;
		ellipse.width = 0;
		ellipse.height = 0;
	}
	@Override
	public void movePoint(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shape;
		ellipse.width = x-ellipse.getX();
		ellipse.height = y-ellipse.getY();
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void moveShape(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shape;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		ellipse.x+=dx;//setLocation(ellipse.x+dx, ellipse.y+dy);
		ellipse.y+=dy;
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
