package transformer;

import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.CShapeManager;

public abstract class CTransformer {
	protected AffineTransform affineTransform;
	private CShapeManager shapeManager;
	protected Point pp;
	
	public CShapeManager getShapeManager() {	return shapeManager;	}
	public void setShapeManager(CShapeManager shapeManager) {	this.shapeManager = shapeManager;	}
	
	public abstract void initTransforming(int x, int y);
	public abstract void keepTransforming(int x, int y);
	public abstract void finishTransforming(int x, int y);
	public CTransformer() {
		this.affineTransform = new AffineTransform();
		this.shapeManager = null;
		this.pp = new Point();
	}
	
	
}
