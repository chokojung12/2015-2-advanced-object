package transformers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class CMover extends CTransformer {

	public CMover() {
		super();
	}
	@Override
	public void initTransforming(int x, int y) {
		this.setOldP(x, y);
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform saveAT = g2D.getTransform();
		g2D.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
		this.getShapeManager().draw(g2D);
		affineTransform.setToTranslation(x-this.oldP.x, y-this.oldP.y);
		getShapeManager().setShape(affineTransform.createTransformedShape(getShapeManager().getShape()));
		if (getShapeManager().isSelected()) {
			getShapeManager().getAnchors().setTransformedShape(affineTransform);
		}
		this.setOldP(x, y);
		this.getShapeManager().draw(g2D);
		g2D.setTransform(saveAT);
	}
	@Override
	public void finishTransforming(int x, int y) {
	}
	@Override
	public void addPoint(int x, int y) {
	}
}
