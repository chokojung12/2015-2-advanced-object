package transformer;

import java.awt.Graphics;

import shapes.GEShape;

public abstract class GETransformer {
	// drawing, moving, resizing, rotating
	private GEShape shape;
	// shape�� �޾ƿ���
	public GETransformer(GEShape shape){this.shape = shape;}
	
	// ���������� ����ϴ°� �ڽĵ��̴ϱ� �ڽĵ��� ����ϵ��� get, set
	public GEShape getShape() {return shape;}
	
	abstract public void initTransforming(Graphics g, int x, int y);
	abstract public void keepTransforming(Graphics g, int x, int y);
	abstract public void continueTransforming(Graphics g, int x, int y);// 2���� ���� �ƴ� ��� ���⼱ polygon
	abstract public void finishTransforming(Graphics g, int x, int y);
}
