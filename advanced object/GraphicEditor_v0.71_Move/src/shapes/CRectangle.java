package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import constants.CConstans.EDrawingState;

public class CRectangle extends CShape {

	
	public CRectangle() {
		super(EDrawingState.TPDrawing);
		this.shapeUtility = new Rectangle();
	}
	public CShape clone(){
		
		return new CRectangle();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shapeUtility;
		rectangle.setBounds(x, y, 0, 0);
		this.setPP(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shapeUtility;
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void draw(Graphics g) {
		Rectangle rectangle = (Rectangle) this.shapeUtility;
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(rectangle);
		if(anchors != null){
			anchors.draw(g2d);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shapeUtility;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		rectangle.setLocation(rectangle.x+dx, rectangle.y+dy);
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
