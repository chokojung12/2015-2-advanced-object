package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class CEllipse extends CShape implements Serializable {

	private Ellipse2D ellipse;
	private Graphics2D graphics2d;
	
	public CEllipse() {
		ellipse = new Ellipse2D() {
			
			@Override
			public Rectangle2D getBounds2D() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void setFrame(double x, double y, double w, double h) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public double getY() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public double getX() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public double getWidth() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public double getHeight() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
	public CShape clone(){
		
		return new CEllipse();
		
	}
	
	@Override
	public void setOrigin(int x, int y) {
		ellipse.setFrame(x, y, 0, 0);
	}
	@Override
	public void movePoint(int x, int y) {
		ellipse.setFrame(ellipse.getX(), ellipse.getY(), x-ellipse.getX(), y-ellipse.getY());
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(ellipse);
	}

}
