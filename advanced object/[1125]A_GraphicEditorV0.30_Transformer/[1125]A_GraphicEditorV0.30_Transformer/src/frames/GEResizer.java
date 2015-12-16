package frames;

import java.awt.Graphics;

import shapes.GEShape;
import transformer.GETransformer;

public class GEResizer extends GETransformer {
	private int px, py;
	public GEResizer(GEShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		px = x;
		py = y;
	}

	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		this.getShape().draw(g);
		this.getShape().resizeShape(x-px, y-py);
		this.getShape().draw(g);
		px = x;
		py = y;
	}

	@Override
	public void continueTransforming(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishTransforming(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

	}

}
