package shapes;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GERectangle extends GEShape {
	
	private Rectangle rectangle;
	
	public GERectangle() {
		super(new Rectangle());
	}
	@Override
	public void initDrawing(Graphics g, int x, int y) {
		rectangle = (Rectangle) shape;
		rectangle.setFrame(x, y, 0, 0);
		this.draw(g);
	}
	@Override
	public void keepDrawing(Graphics g, int x, int y) {
		this.draw(g);
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
		this.draw(g);		
	}
	@Override
	public void finishDrawing(Graphics g, int x, int y) {
		
	}
	@Override
	public void continueDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}


