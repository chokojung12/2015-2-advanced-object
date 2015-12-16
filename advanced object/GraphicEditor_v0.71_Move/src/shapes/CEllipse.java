package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import constants.CConstans.EDrawingState;

public class CEllipse extends CShape {

	public CEllipse() {
		super(EDrawingState.TPDrawing);
		this.shapeUtility = new Ellipse2D.Double(0, 0, 0, 0);
	}
	public CShape clone(){
		
		return new CEllipse();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shapeUtility;
		ellipse.x = x;
		ellipse.y = y;
		ellipse.width = 0;
		ellipse.height = 0;
	}
	@Override
	public void movePoint(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shapeUtility;
		ellipse.width = x-ellipse.getX();
		ellipse.height = y-ellipse.getY();
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void draw(Graphics g) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shapeUtility;
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(ellipse);
		if(anchors != null){
			anchors.draw(g2d);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shapeUtility;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		ellipse.x+=dx;//setLocation(ellipse.x+dx, ellipse.y+dy);
		ellipse.y+=dy;
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
