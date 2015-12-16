package shapes;

import java.awt.Graphics;

public class CRectangle extends CShape {

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(getX(), getY(), getW(), getH());
	}

}
