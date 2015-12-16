package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Shape shape;
	
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	public GEShape(Shape shape) {
		this.shape = shape;
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);		
	}
	public boolean onShape(int x, int y) {
		if (this.shape.contains(x, y))
			return true;
		return false;
	}
	// ���� ��¾�

	public abstract void setPoint(int x, int y);
	//�� �߰��ϴ¾�
	public abstract void addPoint(int x, int y);
	// ������ ���� �����̴� ��
	public abstract void movePoint(int x, int y);
	public abstract void moveShape(int x, int y);
	
}
