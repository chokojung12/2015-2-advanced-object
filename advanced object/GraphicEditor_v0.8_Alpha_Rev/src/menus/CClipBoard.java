package menus;

import java.awt.Rectangle;
import java.util.Vector;

import shapes.CGroupManager;
import shapes.CShapeManager;

public class CClipBoard {
	private Vector<Vector<CShapeManager>> clipBoard;
	private CShapeManager buffer;
	private int que;
	private int size;
	
	public int getQue(){	return que;	}
	public int getSize(){	return size;	}
	public void setQue(int que){	this.que=que;	}
	public CShapeManager getBuffer(){	return buffer;	}
	
	public CClipBoard(){
		clipBoard = new Vector<Vector<CShapeManager>>();
		clipBoard.add(new Vector<CShapeManager>());
		buffer=null;
		que=1;
		size=1;
	}
	public void setBuffer(CShapeManager object){
		CShapeManager buffer = object.clone();
		if(buffer.getClass().equals(CGroupManager.class)){
			Vector<CShapeManager> clones = new Vector<CShapeManager>();
			for(CShapeManager member: ((CGroupManager)object).getGroup()){
				CShapeManager clone = member.clone();
				Rectangle bound = member.getBounds();
				clone.setOrigin(bound.x, bound.y);
				clone.setPP(bound.x, bound.y);
				clone.movePoint((bound.x+bound.width), (bound.y+bound.height));
				clones.add(clone);
			}
			((CGroupManager)buffer).setGroup(clones);
			for(CShapeManager member: ((CGroupManager)buffer).getGroup()){
				Rectangle bound = member.getBounds();
				buffer.setOrigin(bound.x, bound.y);
				buffer.setPP(bound.x, bound.y);
				buffer.movePoint((bound.x+bound.width), (bound.y+bound.height));
			}
		}else {		
			Rectangle bound = object.getBounds();
			buffer.setOrigin(bound.x, bound.y);
			buffer.setPP(bound.x, bound.y);
			buffer.movePoint((bound.x+bound.width), (bound.y+bound.height));
		}
		this.buffer=buffer;
	}
	public void addClip(Vector<CShapeManager> origin){
		Vector<CShapeManager> clip = new Vector<CShapeManager>();
		for(CShapeManager member: origin){
			clip.add(member);
		}
		if(clipBoard.size()<11){	clipBoard.add(clip);	}
		else {
			clipBoard.remove(0);
			clipBoard.add(clip);
		}
		setQue(clipBoard.size()-1);
		size=clipBoard.size()-1;
	}
	public Vector<CShapeManager> getClip(int index){
		return clipBoard.get(index);
	}
	public void resetClipBoard(){
		Vector<Vector<CShapeManager>> buffer =new Vector<Vector<CShapeManager>> ();
		for(int i = 0; i <= que; i ++){
			buffer.add(getClip(i));
		}
		this.clipBoard = buffer;
		setQue(clipBoard.size()-1);
		size=clipBoard.size()-1;
	}
}
