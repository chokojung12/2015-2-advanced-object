package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import frames.CDrawingPanel;
import shapes.CShapeManager;

public abstract class CTransformer {
	protected AffineTransform affineTransform;
	private CShapeManager shapeManager;
	protected Point oldP, anchorP;
	protected CDrawingPanel panel;
	
	public CShapeManager getShapeManager() {	return shapeManager;	}
	public void setShapeManager(CShapeManager shapeManager) {	this.shapeManager = shapeManager;	}
	public void setPanel(CDrawingPanel panel){	this.panel = panel;	}
	public void setOldP(int x, int y) {this.oldP.x = x;	this.oldP.y = y;}
	public Point getAnchorP() { return anchorP; }
	
	public abstract void initTransforming(int x, int y);
	public abstract void keepTransforming(Graphics2D g2D, int x, int y);
	public abstract void finishTransforming(Graphics2D g2D, int x, int y);
	
	public abstract void addPoint(int x, int y);
	
	public CTransformer() {
		this.affineTransform = new AffineTransform();
		this.shapeManager = null;
		this.oldP = new Point(0,0);
		this.anchorP = new Point(0,0);
	}
	
	
}
