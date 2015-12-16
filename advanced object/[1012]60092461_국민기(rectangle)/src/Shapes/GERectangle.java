package Shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class GERectangle extends GEShape {
	
	private Rectangle shape;
	
	public GERectangle(){
		super(new Rectangle());
		this.shape = (Rectangle)this.getShape();
	}
	
	public void setDiagonal(int x1, int y1, int x2, int y2){
		this.shape.setFrame(x1, y1, x2-x1, y2-y1);
	}

	
	public void setX2(int x){this.shape.setFrame(this.shape.getX(), this.shape.getY(), x-this.shape.getX(), this.shape.getHeight());}
	public void setY2(int y){this.shape.setFrame(this.shape.getX(), this.shape.getY(), this.shape.getHeight(), y-this.shape.getY());}
	
	public void draw(JPanel panel){
		Graphics2D g2D = (Graphics2D)panel.getGraphics();
		g2D.setXORMode(panel.getBackground());
		g2D.draw(shape);
	}
	//파란글씩 내부, 검은글씨 외부
}

