package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

import constants.GEConstant;

abstract public class GEShape implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	public static enum EAnchors {NN, SS, EE, WW, NE, NW, SE, SW, RR, MM};
	private EAnchors eSelectedAnchor;	
	private GEAnchors anchors;
	public GEAnchors getAnchors() { return this.anchors; }	
	public EAnchors geteSelectedAnchor() {return eSelectedAnchor;}
	protected Point pp;
	private boolean selected;
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {this.selected = selected;}
	private BasicStroke dashedLineStroke;
	
	private Shape shape;
	private Color lineColor;
	private Color fillColor;
	public Shape getShape() { return this.shape; }
	public void setShape(Shape shape){this.shape = shape;}
	
	public void setLineColor(Color lineColor) {this.lineColor = lineColor;}
	public void setFillColor(Color fillColor) {this.fillColor = fillColor;}
	public Rectangle getBounds() {return shape.getBounds();}
	
	
	public GEShape(Shape shape) {
		this.shape = shape;
		this.anchors = new GEAnchors();
		pp = new Point();
	}

	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		/*
		float dashes[] = {GEConstant.DEFAULT_DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
				GEConstant.DEFAULT_DASHEDLINE_WIDTH,
				BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 10, dashes, 0);
				g2D.setStroke(dashedLineStroke);*/
		g2D.setXORMode(g2D.getBackground());
		
		
		if(fillColor != null){
			g2D.setColor(fillColor);
			g2D.fill(shape);
		}
		if(lineColor != null){
			g2D.setColor(lineColor);
			g2D.draw(shape);
		}
		if(lineColor == null){
			g2D.setColor(Color.black);
			g2D.draw(shape);
		}
		
		if (this.selected) {
			Rectangle boundingRect = shape.getBounds();
			this.anchors.setAchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
			this.anchors.draw(g2D);
		}
	}
	
	public boolean onShape(int x, int y) {
	
		if (this.selected) {
			this.eSelectedAnchor = this.anchors.onAnchor(x, y);
			if (this.eSelectedAnchor!=null)
				return true;
		}
		// for line move
		if(this.shape.intersects(x, y, 1, 1)){
			this.eSelectedAnchor = EAnchors.MM;
			return true;
		}	
		if (this.shape.contains(x, y)) {
			this.eSelectedAnchor = EAnchors.MM;
			return true;
		}		
		return false;
	}
	
	
	
	public abstract void setPoint(int x, int y); 
	public abstract void addPoint(int x, int y); 
	public abstract void movePoint(int x, int y);
	public abstract void moveShape(int x, int y);
	public abstract void resizeShape(int dw, int dh);
	public abstract void attribute();
	public abstract GEShape clone();
}
