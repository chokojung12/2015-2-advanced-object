package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CRectangle extends CShape {
	private Rectangle rectangle;
	
	public CRectangle() {
		rectangle = new Rectangle();
	}
	@Override
	public CShape clone() {
		return new CRectangle();
	}
	@Override
	public void setOrgin(int x, int y) {
		rectangle.setBounds(x, y, 0, 0);
	}
	@Override
	public void movePoint(int x, int y) {
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.draw(rectangle);
	}
}
