package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GERectangle extends GEShape {
	private Rectangle shape;
	
	public GERectangle() {
		super(new Rectangle());
		this.shape = (Rectangle)this.getShape();
	}
	public void setDiagonal(int x1, int y1, int x2, int y2) {
		this.shape.setFrame(x1, y1, x2-x1, y2-y1);
	}
	public void setX2(int x) {
		this.shape.setFrame(this.shape.getX(), this.shape.getY(), x-this.shape.getX(), this.shape.getHeight());
	}
	public void setY2(int y) {
		this.shape.setFrame(this.shape.getX(), this.shape.getY(), this.shape.getWidth(), y-this.shape.getY());
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);		
	}}


