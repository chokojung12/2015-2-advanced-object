package Draw;

import java.awt.Rectangle;
import java.awt.Point;
public class GERectangle {
	private Point startP;
	private Rectangle rectangle;
	public GERectangle(){
		rectangle = new Rectangle();
	}
	public void initDraw(Point startP){
		this.startP = startP;
	}
	public void setCoordinate(Point currentP){
		rectangle.setFrame(startP.x, startP.y, 
				currentP.x - startP.x, currentP.y - startP.y);
	}
	public Rectangle getRectangle(){
		return rectangle;
	}

}
