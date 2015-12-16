package menus;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

import shapes.GEShape;
import constants.GEConstant;
import constants.GEConstant.EColorMenuItem;


public class GEColorMenu extends GEMenu {

	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	
	public GEColorMenu() {		
		super();
		actionListener = new ActionHandler();
		this.menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EColorMenuItem eMenuItem: GEConstant.EColorMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(eMenuItem.getName());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	
	private Color selectColor(){
		Color c = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
		return c;
	}

	private void lineColor() {
		Color lineColor = selectColor();
		GEShape currentShape = this.drawingPanel.getSelectedShape();
		if(this.drawingPanel.getShapes().size() == 0){
			return ;
		}
		if (currentShape.isSelected()){
			currentShape.setLineColor(lineColor);
			drawingPanel.repaint();
		}
		
	}
	
	private void fillColor() {
		Color fillColor = selectColor();
		GEShape currentshape = this.drawingPanel.getSelectedShape();
		
		if(this.drawingPanel.getShapes().size() == 0){
			return ;
		}
		
		if(currentshape.isSelected()){
			currentshape.setFillColor(fillColor);
			drawingPanel.repaint();
		}
	}
		
	private void textColor() {
		;
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EColorMenuItem.lineColor.getName())) {
				lineColor();
			} else if(e.getActionCommand().equals(EColorMenuItem.fillColor.getName())) {
				fillColor();
			} else if(e.getActionCommand().equals(EColorMenuItem.textColor.getName())) {
				textColor();
			} 
		}
	}	
}
