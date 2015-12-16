package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Vector;

import constants.CConstans;
import constants.CConstans.*;

public class CAnchors extends Vector<Shape> {
	
	
	public CAnchors(Rectangle bounds) {
		Rectangle rectangle;
		for(int i =0; i<EAnchorPosition.values().length; i++){
			rectangle = new Rectangle();
			rectangle.setSize(CConstans.ANCHOR_W,CConstans.ANCHOR_H);
			this.add(rectangle);
		}
		this.setBounds(bounds);
	}
	public void setBounds(Rectangle bounds){
		int x=bounds.x - (CConstans.ANCHOR_W/2);
		int y=bounds.y - (CConstans.ANCHOR_H/2);
		int h=bounds.height;
		int w=bounds.width;
		
		((Rectangle)this.get(EAnchorPosition.NN.ordinal())).setLocation(x+w/2, y);
		((Rectangle)this.get(EAnchorPosition.NE.ordinal())).setLocation(x+w, y);
		((Rectangle)this.get(EAnchorPosition.EE.ordinal())).setLocation(x+w, y+h/2);
		((Rectangle)this.get(EAnchorPosition.SE.ordinal())).setLocation(x+w, y+h);
		((Rectangle)this.get(EAnchorPosition.SS.ordinal())).setLocation(x+w/2, y+h);
		((Rectangle)this.get(EAnchorPosition.SW.ordinal())).setLocation(x, y+h);
		((Rectangle)this.get(EAnchorPosition.WW.ordinal())).setLocation(x, y+h/2);
		((Rectangle)this.get(EAnchorPosition.NW.ordinal())).setLocation(x, y);
		((Rectangle)this.get(EAnchorPosition.RR.ordinal())).setLocation(x+w/2, y-(CConstans.ANCHOR_H*5));	
	}
	public void draw(Graphics g){
		for(int i =0; i<this.size(); i++){
			Graphics2D g2D = (Graphics2D) g;
			g2D.draw(this.get(i));
		}
	}
	
	public EAnchorPosition contains(int x, int y){
		for(int i =0; i<this.size(); i++){
			if(this.get(i).contains(x, y)){
				return EAnchorPosition.values()[i];
			}
		}
		return null;
	}
}
