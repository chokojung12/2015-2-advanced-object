package transformer;

import java.awt.Graphics;

import shapes.GEShape;

public abstract class GETransformer {
	// drawing, moving, resizing, rotating
	private GEShape shape;
	public GETransformer(GEShape shape) {
		this.shape = shape;
	}
	public GEShape getShape() {return shape;}
	
	abstract public void initTransforming(Graphics g, int x, int y);
	abstract public void keepTransforming(Graphics g, int x, int y);
	abstract public void continueTransforming(Graphics g, int x, int y);
	abstract public void finishTransforming(Graphics g, int x, int y);
}
