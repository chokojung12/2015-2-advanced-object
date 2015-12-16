package transformers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class CDrawer extends CTransformer {

	public CDrawer() {
		super();
	}
	@Override
	public void initTransforming(int x, int y) {
		this.setOldP(x, y);
		shapeManager.setOrgin(x, y);
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform saveAT = g2D.getTransform();
		g2D.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
		this.getShapeManager().draw(g2D);
		shapeManager.movePoint(x, y);
		this.getShapeManager().draw(g2D);
		g2D.setTransform(saveAT);
	}
	@Override
	public void finishTransforming(int x, int y) {
	}
	@Override
	public void addPoint(int x, int y) {
		this.shapeManager.addPoint(x, y);
	}
}
