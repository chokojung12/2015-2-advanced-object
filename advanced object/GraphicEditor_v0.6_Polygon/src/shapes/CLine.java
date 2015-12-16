package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class CLine extends CShape {

	private Line2D line;
	
	public CLine() {
		line = new Line2D.Double(0,0,0,0);
	}
	public CShape clone(){
		
		return new CLine();
		
	}
	@Override
	public void setOrigin(int x, int y) {
		line.setLine(x, y, x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void movePoint(int x, int y) {
		line.setLine(line.getX1(), line.getY1(), x, y);
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(line);
	}
	@Override
	public void resize(int rate) {
		line.setLine(line.getX1()+rate, line.getY1()+rate, line.getX1()-rate, line.getY1()-rate);		
	}

}
