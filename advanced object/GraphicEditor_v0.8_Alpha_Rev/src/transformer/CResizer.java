package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


public class CResizer extends CTransformer{
	
	@Override
	public void initTransforming(int x, int y) {
		getShapeManager().setPP(x, y);
		oldP.setLocation(x,y);
	}
	private Point2D computeScale(Point previous, Point current){
		int deltaW=0;
		int deltaH=0;
		switch(getShapeManager().geteAnchorType()){
		case EE: deltaW =   current.x-previous.x; 	deltaH =   0;	 					break;
		case WW: deltaW = -(current.x-previous.x);	deltaH =   0; 						break;
		case SS: deltaW =   0;						deltaH =   current.y-previous.y; 	break;
		case NN: deltaW =   0;						deltaH = -(current.y-previous.y);	break;
		case SE: deltaW =   current.x-previous.x; 	deltaH =   current.y-previous.y;	break;
		case NE: deltaW =   current.x-previous.x; 	deltaH = -(current.y-previous.y);	break;
		case SW: deltaW = -(current.x-previous.x);	deltaH =   current.y-previous.y;	break;			
		case NW: deltaW = -(current.x-previous.x);	deltaH = -(current.y-previous.y);	break;
		default: break;
		}
		double w = getShapeManager().getBounds().getWidth();
		double h = getShapeManager().getBounds().getHeight();
		double x = 1.0;
		double y = 1.0;
		if (w > 0.0)
			x += deltaW / w;// + x;
		if (h > 0.0)			
			y += deltaH / h;// + y;
		
		return new Point2D.Double(x, y);
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform savedAT = g2D.getTransform();
		g2D.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
		getShapeManager().draw(g2D);
		Point2D scale = computeScale(oldP, new Point(x, y));
		affineTransform.setToScale(scale.getX(), scale.getY());
		getShapeManager().setShape(affineTransform.createTransformedShape(getShapeManager().getShape()));
		if (getShapeManager().isSelected()) {
			getShapeManager().getAnchors().setTransformedShape(affineTransform);
		}
		oldP.setLocation(x,y);
		getShapeManager().draw(g2D);
		g2D.setTransform(savedAT);
	}

	@Override
	public void finishTransforming(Graphics2D g2D, int x, int y) {
		panel.getClipBoard().addClip(panel.getShapes());		
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
