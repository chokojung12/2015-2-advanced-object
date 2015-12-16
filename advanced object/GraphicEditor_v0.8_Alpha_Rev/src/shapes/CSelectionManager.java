package shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import constants.CConstans.EDrawingType;

public class CSelectionManager extends CShapeManager {	//구현하기
	private static final long serialVersionUID = 1L;
	private Vector<CShapeManager> shapes;
	//private Vector<Integer> index;

	public Vector<CShapeManager> getShapes() {	return shapes;	}
	public void setShapes(Vector<CShapeManager> shapes) {
		this.shapes = shapes;
	}
	
	public CSelectionManager(){
		super(EDrawingType.Twopoint);
		this.shape = new Rectangle();
		shapes = new Vector<CShapeManager>();
		//index = new Vector<Integer>();
	}
	public CShapeManager clone(){
		return new CSelectionManager();
	}
	public Vector<CShapeManager> getIndex(){
		return shapes;
	}
	private int intersects(CShapeManager shape){
		Vector<Point> points = new Vector<Point>();
		points.clear();
		int check = 0;
		Rectangle bound = shape.getShape().getBounds();			
		points.add(new Point(bound.x+(bound.width/2),bound.y));
		points.add(new Point(bound.x+(bound.width/2),(bound.y+bound.height)));
		points.add(new Point((bound.x+bound.width),bound.y+(bound.height/2)));
		points.add(new Point((bound.x),bound.y+(bound.height/2)));
		for(Point point: points){
			if(this.contains(point.x, point.y)){	check++;	}
		}
		return check;
	}
	public Vector<CShapeManager> contain(Vector<CShapeManager> shapes){
		for(CShapeManager shape: shapes){
			int check=0;
			if(shape.getClass().equals(CGroupManager.class)){
				for(CShapeManager member: ((CGroupManager)shape).getGroup()){
					check=intersects(member);
					if(check==4){	this.shapes.add(member);	}
				}
			}else {
				check=intersects(shape);
				if(check==4){	this.shapes.add(shape);	}
			}
		}
		return this.shapes;
	}
	@Override
	public void setOrigin(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setBounds(x, y, 0, 0);
		this.setPP(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}

	@Override
	public void moveShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resizeShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
