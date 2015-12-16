package shapes;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Vector;

import constants.CConstans.ECURSOR;
import constants.CConstans.EDrawingState;
import constants.CConstans.EPosition;

public abstract class CShape implements Serializable{
	
	private EDrawingState eDrawingType;
	protected Shape shapeUtility;
	protected Point pp;
	protected CAnchors anchors;
	protected EPosition currentAnchor;
	
	
	public CShape(EDrawingState eDrawingType) {
		this.eDrawingType = eDrawingType;
		pp = new Point();
	}
	public abstract void setOrigin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void moveShape(int x, int y);
	public abstract void resizeShape(int x, int y);
	public abstract void rotateShape(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void draw(Graphics g);
	public abstract CShape clone();
	public void setSelected(boolean bSelected){
		if(bSelected){
			anchors = new CAnchors(shapeUtility.getBounds());
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
	public boolean contains(int x, int y){
		if(anchors == null){
			if(shapeUtility.intersects(x,y,1,1)){
				currentAnchor = EPosition.MM;
				return true;
			}
		} else {
			currentAnchor = anchors.contains(x, y);
			if(currentAnchor == null){
				if(shapeUtility.intersects(x,y,1,1)){
					currentAnchor = EPosition.MM;
					return true;
				}
			} else {
				for(Rectangle anchor: anchors){
					if(anchor.intersects(x,y,1,1)){
						currentAnchor = EPosition.getValue(anchors.indexOf(anchor));
						return true;
					}
				}
				return true;
			}
		}
		return false;
	}
	public int onAnchor(){
		int no = EPosition.valueOf(currentAnchor.name()).ordinal();
		if(currentAnchor == null){ return EPosition.MM.ordinal();	}
		else{	return no; }
	}
	public Shape getShapeUtility(){
		return shapeUtility;
	}
	public EDrawingState getEDrawingType(){
		return eDrawingType;
	}
	public void setPP(int x, int y){
		this.pp.x=x;
		this.pp.y=y;
	}	
}
