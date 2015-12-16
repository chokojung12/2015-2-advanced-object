package shapes;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape {
	
	private Ellipse2D ellipse;
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
	}
	@Override
	public void initDrawing(Graphics g, int x, int y) {
		ellipse = (Ellipse2D) shape;
		ellipse.setFrame(x, y, 0, 0);
		this.draw(g);
	}
	@Override
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g);
		ellipse.setFrame(ellipse.getX(), ellipse.getY(), x-ellipse.getX(), y-ellipse.getY());
		this.draw(g);		
	}
	@Override
	public void finishDrawing(Graphics g, int x, int y) {
		
	}
	@Override
	public void continueDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initMoving(Graphics graphics, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keepMoving(Graphics graphics, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finishMoving(Graphics graphics, int x, int y) {
		// TODO Auto-generated method stub
		
	}}
