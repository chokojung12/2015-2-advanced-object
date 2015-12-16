package transformers;

import java.awt.Graphics;

import shapes.GEShape;

public class GEDrawer {
	
	private GEShape shape;
	public GEShape getShape() {return shape;}
	
	public GEDrawer(GEShape shape) {
		this.shape = shape;
	}
	public void initDrawing(Graphics g, int x, int y) {
		shape.setPoint(x, y);
		shape.addPoint(x, y);
		this.shape.draw(g);
	}
	public void keepDrawing(Graphics g, int x, int y) {
		this.shape.draw(g);
		this.shape.movePoint(x, y);
		this.shape.draw(g);
	}
	public void continueDrawing(Graphics g, int x, int y) {
		shape.addPoint(x, y);
	}
	public void finishDrawing(Graphics g, int x, int y) {
	}

}
