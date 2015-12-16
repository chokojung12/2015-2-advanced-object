package shapes;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class GEEllipse extends GEShape{
	private static final long serialVersionUID = 1L;
	
	private Ellipse2D ellipse;
	private int x0, y0;
	private Ellipse2D preEllipse;
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		x0 = x;
		y0 = y;
		this.draw(g);
	}

	@Override
	public void keepDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		this.draw(g);
		this.ellipse = (Ellipse2D)shape;
		this.ellipse.setFrame(x0, y0, x-x0, y-y0);
		this.draw(g);
	}

	@Override
	public void continueDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishDrawing(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initMoving(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		x0 = x;
		y0 = y;
		preEllipse = (Ellipse2D)shape;
	}

	@Override
	public void keepMoving(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		this.draw(g);
		this.ellipse.setFrame(x-preEllipse.getWidth()/2, y-preEllipse.getHeight()/2, preEllipse.getWidth(), preEllipse.getHeight()); // 이전 ellipse 사이즈 기억하고 그대로 이동만
		//원의 좌상점 이기때문에 마우스를 원의 중심으로 계산해서 입력
		this.draw(g);
		
	}

	@Override
	public void finishMoving(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
