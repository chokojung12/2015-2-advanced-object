package shapes;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class CShape implements Serializable{
	
	public abstract void setOrigin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void resize(int rate);
	public abstract void draw(Graphics g);
	public abstract CShape clone();

	
	
	
}
