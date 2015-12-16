package Shapes;

import java.awt.Point;
import java.awt.Rectangle;

import Shapes.GERectangle;
import Shapes.GEShape;
public class GERectangle extends GEShape{

	public GERectangle(){
		super(new Rectangle());
	}
	public void initDraw(Point startP){
		this.startP = startP;
	}
	public void setCoordinate(Point currentP){
		Rectangle tempRectangle = (Rectangle)myShape;
		tempRectangle.setFrame(startP.x, startP.y, 
				currentP.x - startP.x, currentP.y - startP.y);
	}
	public GEShape clone() {
		return new GERectangle();
	}
}
