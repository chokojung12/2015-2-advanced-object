package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import shapes.GEShape.EAnchors;

public class GEAnchors {
	
	private Vector<Rectangle> anchors;
	private final int AW = 8;
	private final int AH = 8;
	
	public GEAnchors(){
		this.anchors = new Vector<Rectangle>();
		for(int i = 0; i < EAnchors.values().length-1; ++i){
			this.anchors.add(new Rectangle());
		}
	}
	
	public void setAnchorGeo(int x, int y, int w, int h){
		int d = AW/2;
		this.anchors.get(EAnchors.EE.ordinal()).setFrame(x+w-d, y+h/2-d, AW, AH);
		this.anchors.get(EAnchors.WW.ordinal()).setFrame(x-d, y+h/2-d, AW, AH);
		this.anchors.get(EAnchors.NN.ordinal()).setFrame(x+w/2-d, y-d, AW, AH);
		this.anchors.get(EAnchors.SS.ordinal()).setFrame(x+w/2-d, y+h-d, AW, AH);
		this.anchors.get(EAnchors.NE.ordinal()).setFrame(x+w-d, y-d, AW, AH);
		this.anchors.get(EAnchors.NW.ordinal()).setFrame(x-d, y-d, AW, AH);
		this.anchors.get(EAnchors.SE.ordinal()).setFrame(x+w-d, y+h/2-d, AW, AH);
		this.anchors.get(EAnchors.SW.ordinal()).setFrame(x-d, y+h/2-d, AW, AH);
		this.anchors.get(EAnchors.RR.ordinal()).setFrame(x+w/2-d, y-h/2-d, AW, AH);
	}
	
	public void draw(Graphics2D g2D){
		for(int i = 0; i < EAnchors.values().length-1; ++i){
			g2D.draw(this.anchors.get(i));	
		}
	}
	
	public EAnchors onAnchor(int x, int y){
		for(int i = 0; i < EAnchors.values().length-1; ++i){
			if(this.anchors.get(i).contains(x, y)){
				return EAnchors.values()[i];	//마우스포인터가 9개 anchor중에 위에 올라가있는것 리턴
			}
		}
		return null;
	}
}
