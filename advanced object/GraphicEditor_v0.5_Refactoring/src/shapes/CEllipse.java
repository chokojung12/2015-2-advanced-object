package shapes;

import java.awt.Graphics;

public class CEllipse extends CShape {

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(getX(), getY(), getW(), getH());
	}

}
