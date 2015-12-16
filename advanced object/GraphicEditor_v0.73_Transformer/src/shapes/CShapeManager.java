package shapes;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.Vector;

import constants.CConstans.ECURSOR;
import constants.CConstans.EDrawingType;
import constants.CConstans.EAnchorPosition;
import constants.CConstans.ETransformationType;

public abstract class CShapeManager implements Serializable{
	//about shapes
	protected Shape shape;
	private EDrawingType eDrawingType;
	//about transformation state
	private ETransformationType eTransformationType;
	private CAnchors anchors;
	private EAnchorPosition eAnchorPosition;
	
	protected Point pp;
	
	public Shape getShape(){	return shape;	}	
	public void setShape(Shape shape){	this.shape = shape;	}	
	public EDrawingType getEDrawingType(){	return eDrawingType; }	
	public ETransformationType geteTransformationType() { 	return eTransformationType;	}
	public void setPP(int x, int y){	this.pp.x=x;	this.pp.y=y;	}
	public CShapeManager(EDrawingType eDrawingType) {
		shape = null;
		this.eDrawingType = eDrawingType;
		eTransformationType=ETransformationType.draw;
		anchors = null;
		eAnchorPosition = null;
		pp = new Point();
	}
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setXORMode(g2d.getBackground());
		g2d.draw(shape);
		if(this.isSelected()){
			anchors.draw(g2d);
		}
	}
	public boolean contains(int x, int y){
		if(anchors != null){
			eAnchorPosition = anchors.contains(x, y);
			if(eAnchorPosition != null){
				if(eAnchorPosition==EAnchorPosition.RR){
					eTransformationType = ETransformationType.rotate;
				} else {
					eTransformationType = ETransformationType.resize;
				}
				return true;
			}
		}
		if(shape.intersects(x,y,1,1)){
			eTransformationType = ETransformationType.move;
			return true;
		}
		return false;
	}
	public void setSelected(boolean bSelected){
		if(bSelected){
			anchors = new CAnchors(shape.getBounds());
		} else {
			anchors = null;
		}
	}
	public boolean isSelected(){
		if(anchors == null){
			return false;
		}
		return true;
	}
	public Cursor getCursor(){
		if(this.isSelected()){
			if(eTransformationType == ETransformationType.resize){
				return ECURSOR.valueOf(eAnchorPosition.ordinal()).getCursor();
			} else if(eTransformationType == ETransformationType.rotate){
				return ECURSOR.valueOf(eAnchorPosition.ordinal()).getCursor();
			} else {
				return ECURSOR.MOVE.getCursor();
			}
		}else return ECURSOR.DEFAULT.getCursor();
	}
	public void setAnchorsBounds(){
		if(anchors != null){
			anchors.setBounds(shape.getBounds());
		}
	}
	public int getWidth(){
		return shape.getBounds().width;
	}
	public EAnchorPosition geteAnchorPosition(){
		return eAnchorPosition;
	}

	public Vector<Shape> getAnchors() {
		return anchors;
	}
	public void setAnchors(int i, Shape shape){
		anchors.set(i, shape);
	}
	/*public int onAnchor(){
		for(EAnchorPosition position : EAnchorPosition.values())
		if(eAnchorPosition.equals(position)){
			if(position.ordinal()<EAnchorPosition.RR.ordinal()){	return EAnchorPosition.NN.ordinal();	}
			if(position.ordinal()==EAnchorPosition.RR.ordinal()){	return EAnchorPosition.RR.ordinal();	}
		}
		return EAnchorPosition.MM.ordinal();
	}*/
	public abstract void setOrigin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void moveShape(int x, int y);
	public abstract void resizeShape(int x, int y);
	public abstract void rotateShape(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract CShapeManager clone();
}
