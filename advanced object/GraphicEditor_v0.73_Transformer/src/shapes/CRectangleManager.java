package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import constants.CConstans.EDrawingType;
import constants.CConstans.EAnchorPosition;

public class CRectangleManager extends CShapeManager {

	
	public CRectangleManager() {
		super(EDrawingType.Twopoint);
		this.shape = new Rectangle();
	}
	public CShapeManager clone(){
		
		return new CRectangleManager();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setBounds(x, y, 0, 0);
		this.setPP(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void moveShape(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		rectangle.translate(dx, dy);
		//rectangle.setLocation(rectangle.x+dx, rectangle.y+dy);
		this.setPP(x, y);
		this.setAnchorsBounds();
	}
	@Override
	public void resizeShape(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		EAnchorPosition position = super.geteAnchorPosition();
		int type = position.ordinal();
		int x0 = rectangle.x; int y0 = rectangle.y; int w = rectangle.width; int h = rectangle.height;
		if(type == EAnchorPosition.NN.ordinal()){
			int dy = y0 - y;	y0 -= dy;	h += dy;
		}else if(type == EAnchorPosition.NE.ordinal()){
			int dy = y0 - y;	y0 -= dy;	h += dy;
			int dx = x - (x0 + w);	w += dx;
		}else if(type == EAnchorPosition.EE.ordinal()){
			int dx = x - (x0 + w);	w += dx;
		}else if(type == EAnchorPosition.SE.ordinal()){
			int dy = y - (y0 + h);	h += dy;
			int dx = x - (x0 + w);	w += dx;
		}else if(type == EAnchorPosition.SS.ordinal()){
			int dy = y - (y0 + h);	h += dy;
		}else if(type == EAnchorPosition.SW.ordinal()){
			int dy = y - (y0 + h);	h += dy;
			int dx = x0 - x;	x0 -=dx;	w += dx;
		}else if(type == EAnchorPosition.WW.ordinal()){
			int dx = x0 - x;	x0 -=dx;	w += dx;
		}else if(type == EAnchorPosition.NW.ordinal()){
			int dy = y0 - y;	y0 -= dy; h += dy;
			int dx = x0 - x;	x0 -=dx;	w += dx;
		}
		rectangle.setBounds(x0, y0, w, h);
		this.setAnchorsBounds();
	}
	@Override
	public void rotateShape(int x, int y) {
		
		
	}
}
