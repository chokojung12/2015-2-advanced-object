package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GEGroup extends GERectangle {
	private static final long serialVersionUID = 1L;
	private Vector<GEShape> shapes;
	
	public GEGroup() {
		super();
		this.shapes = new Vector<GEShape>();
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		Rectangle boundingRect = new Rectangle();
		for (GEShape shape: this.shapes) {
			g2D.draw(shape.getShape());
			if (this.isSelected()) {
				boundingRect = boundingRect.union(shape.getShape().getBounds());
			}
		}
		if (this.isSelected()) {
			g2D.draw(boundingRect);
			this.getAnchors().setAchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
			this.getAnchors().draw(g2D);
		}
	}
	@Override
	public void setPoint(int x, int y) {
		this.rectangle.setLocation(x, y);
	}
	public void addPoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}
	@Override
	public void movePoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
		for (GEShape shape: this.shapes) {
			shape.movePoint(x, y);
		}
	}
	@Override
	public void moveShape(int x, int y) {
		this.rectangle.setLocation(this.rectangle.x+x, this.rectangle.y+y);		
		for (GEShape shape: this.shapes) {
			shape.moveShape(x, y);
		}
	}
	public void resizeShape(int dw, int dh) {
		this.rectangle.width =	this.rectangle.width + dw;
		this.rectangle.height =	this.rectangle.height + dh;
		for (GEShape shape: this.shapes) {
			shape.resizeShape(dw, dh);
		}
	}
	public boolean addShape(GEShape shape) {
		if (this.rectangle.contains(shape.getShape().getBounds())) {
			this.shapes.add(shape);
			return  true;
		}
		return false;
	}

}
