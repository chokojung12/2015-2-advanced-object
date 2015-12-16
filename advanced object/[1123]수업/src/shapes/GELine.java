package shapes;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class GELine extends GEShape {
	private static final long serialVersionUID = 1L;
	
	private Line2D line;
	private int x0, y0;
	private Line2D preLine;
	
	public GELine() {
		super(new Line2D.Double());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		x0 = x;
		y0 = y;
		this.draw(g);
	}

	@Override
	public void keepDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		this.draw(g);
		this.line = (Line2D)shape;
		this.line.setLine(x0, y0, x, y);
		this.draw(g);
	}

	@Override
	public void continueDrawing(Graphics g, int x, int y) {

	}

	@Override
	public void finishDrawing(Graphics g, int x, int y) {

	}

	@Override
	public void initMoving(Graphics g, int x, int y) {
		
	}

	@Override
	public void keepMoving(Graphics g, int x, int y) {
		
	
	}

	@Override
	public void finishMoving(Graphics g, int x, int y) {

	}

}
