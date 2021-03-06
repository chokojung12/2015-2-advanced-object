package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CRectangle extends CShape {

	private Rectangle rectangle;
	
	public CRectangle() {
		rectangle = new Rectangle();
	}
	public CShape clone(){
		
		return new CRectangle();
		
	}
	@Override
	public void setOrigin(int x, int y) {
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
	public void resize(int rate) {
		int x=rectangle.x;
		int y=rectangle.y;
		rectangle.setBounds(x+rate, y+rate, rectangle.width-rate*2, rectangle.height-rate*2);
		//rectangle.setSize(rectangle.width-rate, rectangle.height-rate);
		System.out.println(rectangle.getWidth());
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(rectangle);
	}

}
