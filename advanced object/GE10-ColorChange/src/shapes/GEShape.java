package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Rectangle;
import utils.GEAnchorList;
import constants.GEConstants.EAnchorTypes;
import java.awt.Color;

public abstract class GEShape {
	protected Shape myShape;
	protected Point startP;
	protected boolean selected;
	protected GEAnchorList anchorList;
	protected EAnchorTypes selectedAnchor;
	protected Color lineColor, fillColor;
	
	public GEShape(Shape shape){
		this.myShape = shape;
		anchorList = null;
		selected = false;
	}
	
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}


	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


	public void draw(Graphics2D g2D){
		if(fillColor != null){
			g2D.setColor(fillColor);
			g2D.fill(myShape);
		}
		if(lineColor != null){
			g2D.setColor(lineColor);
			g2D.draw(myShape);
		}
		if(selected == true){
			anchorList.draw(g2D);
		}
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
		if(selected == true){
			anchorList = new GEAnchorList();
			anchorList.setPosition(myShape.getBounds());
		}else{
			anchorList = null;
		}
	}
	
	public boolean onShape(Point p){
		if(anchorList != null){
			selectedAnchor = anchorList.onAnchors(p);
			if(selectedAnchor != EAnchorTypes.NONE){
				return true;
			}
		}
		return myShape.intersects(new Rectangle(p.x, p.y, 2, 2));
	}
	
	abstract public void initDraw(Point startP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();
}
