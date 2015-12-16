package shapes;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Vector;

import constants.CConstans.EDrawingState;

public abstract class CShape implements Serializable{
	
	private EDrawingState eDrawingType;
	protected Shape shapeUtility;
	protected Point pp;
	protected Vector<Rectangle> edges;
	
	public CShape(EDrawingState eDrawingType) {
		this.eDrawingType = eDrawingType;
		pp = new Point();
	}
	public abstract void setOrigin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void moveShape(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void draw(Graphics g);
	public abstract CShape clone();
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
