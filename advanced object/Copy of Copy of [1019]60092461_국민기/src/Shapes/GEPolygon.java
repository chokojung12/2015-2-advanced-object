package Shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape {
	public GEPolygon(){
		super(new Polygon());
	}
	
	public void initDraw(Point startP){
		((Polygon)myShape).addPoint(startP.x, startP.y);
	}
	
	public void setCoordinate(Point currentP){
		Polygon tempPolygon = (Polygon)myShape;
		tempPolygon.xpoints[((Polygon)myShape).npoints - 1] = currentP.x;
		tempPolygon.ypoints[((Polygon)myShape).npoints - 1] = currentP.y;
	}
	
	public void continueDrawing(Point currentP){
		((Polygon)myShape).addPoint(currentP.x, currentP.y);
	}
	public GEShape clone() {
		return new GEPolygon();
	}
}
