package transformer;

import java.awt.Graphics;
import java.awt.Rectangle;

import shapes.GEShape;

public class GEDrawer extends GETransformer {

	public GEDrawer(GEShape shape) {
		super(shape);
	}
	
	public void initTransforming(Graphics g, int x, int y) {
		this.getShape().setPoint(x, y);
		this.getShape().addPoint(x, y);
		this.getShape().draw(g);
	}

	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		this.getShape().draw(g);
		this.getShape().movePoint(x, y);
		this.getShape().draw(g);
	}

	@Override
	public void continueTransforming(Graphics g, int x, int y) {

	}

	@Override
	public void finishTransforming(Graphics g, int x, int y) {

	}

}
