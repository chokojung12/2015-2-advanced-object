package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import constants.CConstans;
import constants.CConstans.*;

public class CAnchors extends Vector<Rectangle> {
	
	
	public CAnchors(Rectangle bounds) {
		Rectangle rectangle;
		for(int i =0; i<EPosition.values().length-1; i++){
			rectangle = new Rectangle();
			rectangle.setSize(CConstans.ANCHOR_W,CConstans.ANCHOR_H);
			this.add(rectangle);
		}
		this.setBounds(bounds);
	}
	public void setBounds(Rectangle bounds){
		int x=bounds.x;
		int y=bounds.y;
		int h=bounds.height;
		int w=bounds.width;
		this.get(EPosition.NN.ordinal()).setLocation(x+w/2, y);
		this.get(EPosition.NE.ordinal()).setLocation(x+w, y);
		this.get(EPosition.EE.ordinal()).setLocation(x+w, y+h/2);
		this.get(EPosition.SE.ordinal()).setLocation(x+w, y+h);
		this.get(EPosition.SS.ordinal()).setLocation(x+w/2, y+h);
		this.get(EPosition.SW.ordinal()).setLocation(x, y+h);
		this.get(EPosition.WW.ordinal()).setLocation(x, y+h/2);
		this.get(EPosition.NW.ordinal()).setLocation(x, y);
		this.get(EPosition.RR.ordinal()).setLocation(x+w/2, y-(CConstans.ANCHOR_H*5));
		for(int i =0; i<this.size(); i++){
			this.get(i).translate(-(CConstans.ANCHOR_W/2),-(CConstans.ANCHOR_H/2));
		}		
	}
	public void draw(Graphics g){
		for(int i =0; i<this.size(); i++){
			Graphics2D g2D = (Graphics2D) g;
			g2D.draw(this.get(i));
		}
	}
	
	public EPosition contains(int x, int y){
		for(int i =0; i<this.size(); i++){
			if(this.get(i).contains(x, y)){
				return EPosition.values()[i];
			}
		}
		return null;
	}
}
