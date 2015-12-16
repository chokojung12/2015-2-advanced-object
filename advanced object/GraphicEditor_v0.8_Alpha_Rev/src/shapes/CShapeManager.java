package shapes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Vector;

import constants.CConstans.EAnchorType;
import constants.CConstans.ECURSOR;
import constants.CConstans.EDrawingType;
import constants.CConstans.ETransformationState;

public abstract class CShapeManager implements Serializable{
	private static final long serialVersionUID = 1L;
	//about shapes
	protected Shape shape;
	private EDrawingType eDrawingType;
	protected Vector<CShapeManager> groupMember;
	//about transformation state
	private ETransformationState eTransformationType;
	private CAnchors anchors;
	private EAnchorType eAnchorType;
	private Color fill;
	private Color line;
	
	protected Point pp;
	
	public Shape getShape(){	return shape;	}	
	public void setShape(Shape shape){	this.shape = shape;	}	
	public EDrawingType getEDrawingType(){	return eDrawingType; }	
	public ETransformationState geteTransformationType() { 	return eTransformationType;	}
	public EAnchorType geteAnchorType() { return eAnchorType;	}
	public void setETransformationState(ETransformationState eTransformationState) {this.eTransformationType = eTransformationState;}
	public void setPP(int x, int y){	this.pp.x=x;	this.pp.y=y;	}
	public void setFill(Color color){ this.fill=color;}
	public void setLine(Color color){ this.line=color;}
	public CShapeManager(EDrawingType eDrawingType) {
		shape = null;
		this.eDrawingType = eDrawingType;
		eTransformationType=ETransformationState.draw;
		anchors = null;
		eAnchorType = null;
		pp = new Point();
		fill=null;
		line=null;
	}
	public Rectangle getBounds() {return shape.getBounds();}
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setXORMode(g2d.getBackground());
		if(line!=null) 
			g2d.setColor(line);
		if(shape==null){
			for(CShapeManager member: groupMember){
				member.draw(g2d);
			}
		}
		else	
			g2d.draw(shape);
		
		if(fill!=null){
			g2d.setColor(fill);
			if(shape==null){
				for(CShapeManager member: groupMember){
					member.draw(g2d);
				}
			}
			else	
				g2d.fill(shape);
		}
		if(this.isSelected()){
			anchors.draw(g2d);
		}
		g.setColor(Color.BLACK);
	}
	public boolean contains(int x, int y){
		if(anchors != null){
			eAnchorType = anchors.contanins(x, y);
			if(eAnchorType != null){
				if(eAnchorType==EAnchorType.RR){
					eTransformationType = ETransformationState.rotate;
				} else {
					eTransformationType = ETransformationState.resize;
				}
				return true;
			}
		}if(shape==null){
			for(CShapeManager member: groupMember){
				if(member.getShape().intersects(x,y,1,1)){
					eTransformationType = ETransformationState.move;
					return true;
				}
			}
		}else if(shape.intersects(x,y,1,1)){
			eTransformationType = ETransformationState.move;
			return true;
		}
		eTransformationType = ETransformationState.draw;
		return false;
	}
	public void setSelected(boolean bSelected){
		if(shape==null){
			for(CShapeManager member: groupMember){
				member.setSelected(bSelected);
			}
		}else {
			if(bSelected){
				anchors = new CAnchors(shape.getBounds());
			} else {
				anchors = null;
			}
		}
	}
	public boolean isSelected(){
		if(shape==null){
			for(CShapeManager member: groupMember){
				return member.isSelected();
			}
		}else {
			if(anchors == null){
				return false;
			}
			return true;
		}
		return false;
	}
	public Cursor getCursor(){
		if(this.isSelected()){
			if(eTransformationType == ETransformationState.resize){
				return ECURSOR.valueOf(eAnchorType.ordinal()).getCursor();
			} else if(eTransformationType == ETransformationState.rotate){
				return ECURSOR.valueOf(eAnchorType.ordinal()).getCursor();
			} else {
				return ECURSOR.MOVE.getCursor();
			}
		}else return ECURSOR.DEFAULT.getCursor();
	}
	public int getWidth(){
		return shape.getBounds().width;
	}
	public EAnchorType geteAnchorPosition(){
		return eAnchorType;
	}

	public CAnchors getAnchors() {	return anchors;	}
	public void setAnchors(int i, Shape shape){
		anchors.set(i, (Rectangle) shape);
	}
	public void addShape(){
		
	}
	public abstract Vector<CShapeManager> contain(Vector<CShapeManager> shapes);
	public abstract void setOrigin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void moveShape(int x, int y);
	public abstract void resizeShape(int x, int y);
	public abstract void rotateShape(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract CShapeManager clone();
}
