package transformer;

import java.awt.Graphics;

import shapes.GEShape;

public abstract class GETransformer {
	// drawing, moving, resizing, rotating
	private GEShape shape;
	// shape를 받아오고
	public GETransformer(GEShape shape){this.shape = shape;}
	
	// 실질적으로 사용하는건 자식들이니까 자식들이 사용하도록 get, set
	public GEShape getShape() {return shape;}
	
	abstract public void initTransforming(Graphics g, int x, int y);
	abstract public void keepTransforming(Graphics g, int x, int y);
	abstract public void continueTransforming(Graphics g, int x, int y);// 2개의 점이 아닌 경우 여기선 polygon
	abstract public void finishTransforming(Graphics g, int x, int y);
}
