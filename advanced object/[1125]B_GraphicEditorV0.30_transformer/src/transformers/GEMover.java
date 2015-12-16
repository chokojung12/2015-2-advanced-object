package transformers;

import java.awt.Graphics;

import shapes.GEShape;

public class GEMover extends GETransformer {
	
	private int px, py;
	public GEMover(GEShape shape) {
		super(shape);
	}
	@Override
	public void initTransforming(Graphics g, int x, int y) {
		px = x;
		py = y;
	}
	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		this.getShape().draw(g);
		this.getShape().moveShape(x-px, y-py);
		this.getShape().draw(g);
		px = x;
		py = y;
	}
	@Override
	public void finishTransforming(Graphics g, int x, int y) {
	}
	@Override
	public void continueTransforming(Graphics g, int x, int y) {
	}

}
