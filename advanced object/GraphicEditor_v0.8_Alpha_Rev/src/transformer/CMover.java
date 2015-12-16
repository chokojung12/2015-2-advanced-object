package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import shapes.CGroupManager;
import shapes.CShapeManager;

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
		if(this.getShapeManager().getClass().equals(CGroupManager.class)){
			for(CShapeManager member: ((CGroupManager)this.getShapeManager()).getGroup()){
				member.draw(g2D);
			}
		}else this.getShapeManager().draw(g2D);
		affineTransform.setToTranslation(x-this.oldP.x, y-this.oldP.y);
		if(this.getShapeManager().getClass().equals(CGroupManager.class)){
			for(CShapeManager member: ((CGroupManager)this.getShapeManager()).getGroup()){
				member.setShape(affineTransform.createTransformedShape(member.getShape()));
				if (member.isSelected()) {
					member.getAnchors().setTransformedShape(affineTransform);
				}
			}
		}else {
			getShapeManager().setShape(affineTransform.createTransformedShape(getShapeManager().getShape()));
			if (getShapeManager().isSelected()) {
				getShapeManager().getAnchors().setTransformedShape(affineTransform);
			}
		}
		this.setOldP(x, y);
		if(this.getShapeManager().getClass().equals(CGroupManager.class)){
			for(CShapeManager member: ((CGroupManager)this.getShapeManager()).getGroup()){
				member.draw(g2D);
			}
		}else this.getShapeManager().draw(g2D);
		g2D.setTransform(saveAT);
	}
	@Override
	public void finishTransforming(Graphics2D g2D, int x, int y) {
		panel.getClipBoard().addClip(panel.getShapes());
	}
	@Override
	public void addPoint(int x, int y) {
	}
}
