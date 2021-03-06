package Shapes;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import Shapes.GERectangle;
import Shapes.GEShape;

public class GEEllipse extends GEShape{

	public GEEllipse(){
		super(new Ellipse2D.Double());
	}

	public void initDraw(Point startP){
		this.startP = startP;
	}

	public void setCoordinate(Point currentP){
		Ellipse2D tempEllipse = (Ellipse2D)myShape;
		tempEllipse.setFrame(startP.x, startP.y, 
				currentP.x - startP.x,  currentP.y - startP.y);
	}
	public GEShape clone() {
		return new GEEllipse();
	}
}