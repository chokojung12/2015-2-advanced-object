package shapes;

import java.io.Serializable;
import java.util.Vector;

import constants.CConstans.EDrawingType;
import frames.CDrawingPanel;

public class CGroupManager extends CShapeManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private Vector<CShapeManager> shapes;
	public Vector<CShapeManager> getGroup(){	return groupMember;	}
	public void setGroup(Vector<CShapeManager> groupMember){	this.groupMember=groupMember;	}
	
	public CGroupManager(){
		super(EDrawingType.Twopoint);
		//initialize components
		shapes = new Vector<CShapeManager>();
		groupMember = new Vector<CShapeManager>();
	}
	
	public void setGroup(CDrawingPanel panel, Vector<CShapeManager> selected){
		this.groupMember=selected;
		Vector<CShapeManager> shapes = panel.getShapes();
		for(CShapeManager shape: shapes){
			if(this.groupMember.indexOf(shape)==0){
				this.shapes.add(this);
			} else if(selected.indexOf(shape)==-1){
				this.shapes.add(shape);
			}
		}
		panel.setShapes(this.shapes);
		panel.repaint();
	}
	public void setUnGroup(CDrawingPanel panel, CGroupManager group){
		this.groupMember=group.getGroup();
		shapes = panel.getShapes();
		shapes.remove(group);
		for(CShapeManager shape: groupMember){
			shapes.add(shape);
		}
		panel.setShapes(this.shapes);
		panel.repaint();
	}

	@Override
	public Vector<CShapeManager> contain(Vector<CShapeManager> shapes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resizeShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateShape(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CShapeManager clone() {
		return new CGroupManager();
	}
}
