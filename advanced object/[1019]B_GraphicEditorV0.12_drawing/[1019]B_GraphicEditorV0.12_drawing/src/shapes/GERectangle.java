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
	public void setDiagonal(int x1, int y1, int x2,int y2) {
		this.shape.x = x1;
		this.shape.y = y1;
		this.shape.width = x2-x1;
		this.shape.height = y2-y1;
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(this.shape);			
	}
	public void setX2(int x) {this.shape.width = x-this.shape.x;}
	public void setY2(int y) {this.shape.height = y-this.shape.y;}
}
