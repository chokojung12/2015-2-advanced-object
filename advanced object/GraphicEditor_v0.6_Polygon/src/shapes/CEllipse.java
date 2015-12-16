package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CEllipse extends CShape {

	private Ellipse2D.Double ellipse;
	
	public CEllipse() {
		ellipse = new Ellipse2D.Double(0, 0, 0, 0);
	}
	public CShape clone(){
		
		return new CEllipse();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		ellipse.x = x;
		ellipse.y = y;
		ellipse.width = 0;
		ellipse.height = 0;
	}
	@Override
	public void movePoint(int x, int y) {
		ellipse.width = x-ellipse.getX();
		ellipse.height = y-ellipse.getY();
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(ellipse);
	}
	@Override
	public void resize(int rate) {
		ellipse.x = ellipse.x+rate;
		ellipse.y = ellipse.y+rate;
		ellipse.width = ellipse.x-rate;
		ellipse.height = ellipse.y-rate;		
	}

}
