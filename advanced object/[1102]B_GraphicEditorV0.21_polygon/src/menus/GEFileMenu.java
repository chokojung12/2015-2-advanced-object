package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EButtons;
import constants.GEConstant.EFileMenuItem;

public class GEFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;

	public GEFileMenu() {	
		actionListener = new ActionHandler();
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EFileMenuItem eMenuItem : GEConstant.EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItems.add(menuItem);
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.add(menuItem);
		}
	}

	private void open() {
	}	
	private void save() {
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.getClass())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getClass())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getClass())) {
				System.exit(1);
			}
		}
	}
}
