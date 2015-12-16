package transformers;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.CShapeManager;

public abstract class CTransformer {
	
	// transformer has problems in displaying transformed shapes
	// 1. when a shape is rotated, resize direction is odd
	// 2. when a shape is resized, anchor lost it position a little
	
	protected CShapeManager shapeManager;	
	protected Point oldP, anchorP;
	protected AffineTransform affineTransform;
	
	public CShapeManager getShapeManager() {return shapeManager;}
	public void setShapeManager(CShapeManager shapeManager) {this.shapeManager = shapeManager;}
	public void setOldP(int x, int y) {this.oldP.x = x;	this.oldP.y = y;}
	public Point getAnchorP() { return anchorP; }
	
	public CTransformer() {
		this.shapeManager = null;
		oldP = new Point(0, 0);
		anchorP = new Point(0, 0);
		affineTransform =  new AffineTransform();
	}
	
	public abstract void initTransforming(int x, int y);
	public abstract void keepTransforming(Graphics2D g2D, int x, int y);
	public abstract void finishTransforming(int x, int y);
	
	public abstract void addPoint(int x, int y);
}
