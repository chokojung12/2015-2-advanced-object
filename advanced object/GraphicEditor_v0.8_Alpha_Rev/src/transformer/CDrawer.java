package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.CSelectionManager;
import shapes.CShapeManager;


public class CDrawer extends CTransformer{
	
	@Override
	public void initTransforming(int x, int y) {		
		getShapeManager().setOrigin(x, y);
		getShapeManager().setPP(x, y);
		oldP.setLocation(x,y);
	}

	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		getShapeManager().draw(g2D);
		getShapeManager().movePoint(x, y);
		getShapeManager().draw(g2D);
	}

	@Override
	public void finishTransforming(Graphics2D g2D, int x, int y) {
		if(!getShapeManager().getClass().equals(CSelectionManager.class)){
			panel.addShape(getShapeManager());
			panel.resetSelections(getShapeManager());
			panel.getClipBoard().addClip(panel.getShapes());
			panel.setbDiirty(true);
		}else {
			Vector<CShapeManager> selected = getShapeManager().contain(panel.getShapes());
			getShapeManager().draw(g2D);
			resetselection(selected);
		}
		System.out.println(getShapeManager().getShape());
	}
	
	private void resetselection(Vector<CShapeManager> selected){
		for(CShapeManager shape: panel.getShapes()){
			shape.setSelected(false);
		}
		for(CShapeManager shape: selected){
			shape.setSelected(true);
		}
		panel.repaintPanel();
	}
	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
