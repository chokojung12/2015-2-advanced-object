package shapes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

import constants.CConstants.EAnchorType;
import constants.CConstants.EDrawingType;
import constants.CConstants.ETransformationState;

public abstract class CShapeManager implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// attributes
	protected Shape shape;
	private EDrawingType eDrawingType;
	private ETransformationState eTransformationState;
	// anchors
	private EAnchorType eAnchorType;
	private CAnchors anchors;
	
	// getters & setters
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape=shape;}
	public EDrawingType getEDrawingType() {return eDrawingType;}
	public ETransformationState getETransformationState() {return eTransformationState;}
	public void setETransformationState(ETransformationState eTransformationState) {this.eTransformationState = eTransformationState;}
	public EAnchorType getEAnchorType() {return eAnchorType;}
	public CAnchors getAnchors() { return anchors; }
	
	// constructor
	public CShapeManager(EDrawingType eDrawingType) {
		// subclass will create a shape & set types
		this.shape = null;
		this.eDrawingType = eDrawingType;
		// initial state will be draw
		this.eTransformationState = ETransformationState.draw;
		// anchors will be created when needed
		this.anchors = null;
		this.eAnchorType = null;
	}
	public Rectangle getBounds() {return shape.getBounds();}	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.draw(shape);
		if (anchors != null) {
			anchors.draw(g2D);
		}
	}
	public boolean contains(int x, int y) {
		if (anchors!=null) {
			eAnchorType = anchors.contanins(x, y);
			if (eAnchorType != null) {
				if (eAnchorType==EAnchorType.RR)
					this.eTransformationState = ETransformationState.rotate;
				else
					this.eTransformationState = ETransformationState.resize;
				return true;
			}
		}
		if (shape.contains(x, y)) {
			this.eTransformationState = ETransformationState.move;
			return true;
		}
		return false;
	}
	public void setSelected(boolean bSelected) {
		if (bSelected) {
			anchors = new CAnchors(shape.getBounds());
			this.eTransformationState = ETransformationState.move;
		} else {
			anchors = null;
		}
	}
	public boolean isSelected() {
		if (anchors == null) {
			return false;
		}	
		return true;
	}
	
	public abstract CShapeManager clone();
	public abstract void setOrgin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void addPoint(int x, int y);
}
