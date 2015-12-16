package shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class GELine extends GEShape{

private static final long serialVersionUID = 1L;
	
	private Line2D.Double line;
	public GELine() {
		super(new Line2D.Double(0,0,0,0));
		this.line = (Line2D.Double) this.getShape();
	}
	
	public GEShape clone() {
		return new GELine();
	}
	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		Line2D line = (Line2D) this.getShape();
		line.setLine(x, y, x, y);
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		line.setLine(line.getX1(), line.getY1(), x, y);
	}

	@Override
	public void moveShape(int x, int y) {
		// TODO Auto-generated method stub
		int dx = x-this.pp.x;
		int dy = y-this.pp.y;
		line.setLine(line.getX1()+dx,line.getY1()+dy,line.getX2()+dx,line.getY2()+dy);
	}

	@Override
	public void resizeShape(int dw, int dh) {
		// TODO Auto-generated method stub
		//아래 무빙 가능
		//line.setLine(line.getX1()+dw,line.getY1()+dh,line.getX2()+dw,line.getY2()+dh);
		this.line.x2 += dw;
		this.line.y2 += dh;
	}
	public void rotateShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attribute() {
		// TODO Auto-generated method stub
		
	}


}
