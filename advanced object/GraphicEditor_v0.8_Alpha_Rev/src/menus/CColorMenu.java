package menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JColorChooser;

import shapes.CGroupManager;
import shapes.CSelectionManager;
import shapes.CShapeManager;
import constants.CConstans.EMenuItem;

public class CColorMenu extends CMenu {
	private static final long serialVersionUID = 1L;
	private AffineTransform affineTransform;
	
	public CColorMenu(EMenuItem[] eMenuItems){
		super(eMenuItems);
	}
	private Color selectColor(){
		Color c = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
		return c;
	}
	public void fill(){
		Graphics2D g = drawingPanel.getGraphic();
		Color color = selectColor();
		CShapeManager currentshape = drawingPanel.getCurrentShape();
		g.setColor(color);
		if(currentshape.getClass().equals(CSelectionManager.class)){
			Vector<CShapeManager> selected = ((CSelectionManager) currentshape).getShapes();
			for(CShapeManager shape: selected){
				shape.setFill(color);
				drawingPanel.repaint();
			}
		} else if(currentshape.getClass().equals(CGroupManager.class)){
			Vector<CShapeManager> selected = ((CGroupManager) currentshape).getGroup();
			for(CShapeManager shape: selected){
				shape.setFill(color);
				drawingPanel.repaint();
			}
		}
		if(currentshape.isSelected()){
			currentshape.setFill(color);
			drawingPanel.repaint();
		}
	}
	public void line(){
		CShapeManager currentshape = drawingPanel.getCurrentShape();
		Color color = selectColor();
		if(currentshape.getClass().equals(CSelectionManager.class)){
			Vector<CShapeManager> selected = ((CSelectionManager) currentshape).getShapes();
			for(CShapeManager shape: selected){
				shape.setLine(color);
				drawingPanel.repaint();
			}
		} else if(currentshape.getClass().equals(CGroupManager.class)){
			Vector<CShapeManager> selected = ((CGroupManager) currentshape).getGroup();
			for(CShapeManager shape: selected){
				shape.setLine(color);
				drawingPanel.repaint();
			}
		}
		if(currentshape.isSelected()){
			currentshape.setLine(color);
			drawingPanel.repaint();
		}
	}
	public void font(){
		
	}
	public AffineTransform getAffineTransform() {
		return affineTransform;
	}
	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}	
}