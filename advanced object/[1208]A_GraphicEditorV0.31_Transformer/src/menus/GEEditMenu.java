package menus;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JMenuItem;

import shapes.GEShape;
import constants.GEConstant;
import constants.GEConstant.EEditMenuItem;

public class GEEditMenu extends GEMenu {

	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	private GEShape copyShape, deleteShape;

	public GEEditMenu() {		
		super();
		actionListener = new ActionHandler();		
		this.menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EEditMenuItem eMenuItem: GEConstant.EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(eMenuItem.getName());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());			
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	private void moveClip(GEShape clip){
		Graphics2D g2D = drawingPanel.getGraphic();
		AffineTransform affineTransform = new AffineTransform();
		AffineTransform saveAT = g2D.getTransform();
		//off set
		affineTransform.setToTranslation(100, 100);	
		clip.setShape(affineTransform.createTransformedShape(clip.getShape()));
		//clip.draw(g2D);	
		g2D.setTransform(saveAT);
	}
	

	public void delete(){
		//selected check;
		for(GEShape shape : this.drawingPanel.getShapes()){
			if(shape.isSelected()){
				this.deleteShape = shape;	
			}
		}
		// 선택한것 제거
		for(GEShape shape : this.drawingPanel.getShapes()){
			if(shape == this.deleteShape){
				this.drawingPanel.getShapes().removeElement(shape);
				drawingPanel.repaint();
				break;
			}
		}
	}
	//com
	private void copy(){
		//선택한것 카피
		for(GEShape shape : this.drawingPanel.getShapes()){
			if(shape.isSelected()){
				System.out.println("카피됨");
				this.copyShape = shape;	
			}
		}
	}
	private void paste(){
		if(this.copyShape == null){
			return ;
		}
		int offset = 50;
		
		GEShape temp = this.copyShape.clone();
		Rectangle bound = this.copyShape.getBounds();
		
		temp.setPoint(bound.x+offset, bound.y+offset);
		temp.setPP(bound.x, bound.y);
		temp.movePoint((bound.x+bound.width+offset), (bound.y+bound.height+offset));
		
		this.drawingPanel.getShapes().add(temp);
	
		drawingPanel.repaint();
	}
	public void cut(){
		drawingPanel.repaint();
	}
	
	
	private void redo(){
		
	}
	private void undo() {
		this.drawingPanel.undo();
	}
	
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EEditMenuItem.copy.getName())) {
				copy();
			} else if(e.getActionCommand().equals(EEditMenuItem.cut.getName())) {
				cut();
			} else if(e.getActionCommand().equals(EEditMenuItem.paste.getName())) {
				paste();
			} else if(e.getActionCommand().equals(EEditMenuItem.delete.getName())) {
				delete();
			} else if(e.getActionCommand().equals(EEditMenuItem.redo.getName())) {
				redo();
			} else if(e.getActionCommand().equals(EEditMenuItem.undo.getName())) {
				undo();
			}
		}
	}	
}
