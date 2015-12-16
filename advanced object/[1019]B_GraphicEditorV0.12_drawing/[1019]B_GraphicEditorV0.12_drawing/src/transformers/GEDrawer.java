package transformers;

import java.awt.Graphics;

import shapes.GEShape;
import constants.GEConstant.EDrawingState;

public class GEDrawer {
	
	private Graphics g;
	private GEShape shape;
	public GEShape getShape() {return shape;}
	
	public GEDrawer(Graphics g, GEShape shape) {
		this.g = g;
		this.shape = shape;
	}
	public EDrawingState initDrawing(int x, int y) {
		shape.setDiagonal(x, y, x, y);
		this.shape.draw(g);
		return EDrawingState.drawing;
		
	}
	public EDrawingState keepDrawing(int x, int y) {
		this.shape.draw(g);
		this.shape.setX2(x);
		this.shape.setY2(y);
		this.shape.draw(g);
		return EDrawingState.drawing;
	}
	public EDrawingState finishDrawing(int x, int y) {
		return EDrawingState.idle;
	}

}
