package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EColorMenuItem;
import constants.GEConstant.EFileMenuItem;


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
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			menuItem.setText(eMenuItem.getName());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EColorMenuItem.lineColor.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EColorMenuItem.fillColor.getName())) {
				System.exit(1);
			} else if(e.getActionCommand().equals(EColorMenuItem.textColor.getName())) {
				System.exit(1);
			}	
	
		}
	}	
}
