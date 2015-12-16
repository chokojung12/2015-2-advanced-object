package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EEditMenuItem;
import constants.GEConstant.EFileMenuItem;

public class GEEditMenu extends GEMenu {

	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;

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
	
	private void undo() {
		// TODO Auto-generated method stub
		this.drawingPanel.undo();
	}
	
	@SuppressWarnings("unused")
	class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EEditMenuItem.copy.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.cut.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.redo.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.delete.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.group.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.paste.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.selectAll.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.ungroup.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EEditMenuItem.undo.getName())) {
				undo();
			}		
		}
	}	
}
