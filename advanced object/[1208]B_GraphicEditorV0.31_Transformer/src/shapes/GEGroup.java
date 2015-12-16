package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

public class GEGroup extends GERectangle {
	private static final long serialVersionUID = 1L;
	private Vector<GEShape> shapes;
	
	public GEGroup() {
		super();
		this.shapes = new Vector<GEShape>();
	}
	public void draw(Graphics g) {
		boolean isFirst = true;
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(this.rectangle);
		for (GEShape shape: this.shapes) {
			g2D.draw(shape.getShape());
			if (this.isSelected()) {
				if (isFirst) {
					this.rectangle = shape.getShape().getBounds();
					isFirst = false;
				} else {
					this.rectangle = this.rectangle.union(shape.getShape().getBounds());
				}
			}
		}
		if (this.isSelected()) {
			this.getAnchors().setAchorGeo(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			this.getAnchors().draw(g2D);
		}
	}

	@Override
	public void moveShape(int x, int y) {
		for (GEShape shape: this.shapes) {
			shape.moveShape(x, y);
		}
	}
	public void resizeShape(int dw, int dh) {
		for (GEShape shape: this.shapes) {
			shape.moveShape(dw, dh);
		}
	}
	
	public void addShape(GEShape shape) {
		this.shapes.add(shape);
	}

}
