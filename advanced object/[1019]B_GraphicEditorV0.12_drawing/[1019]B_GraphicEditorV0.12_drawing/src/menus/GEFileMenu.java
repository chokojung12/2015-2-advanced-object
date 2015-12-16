package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;

	public GEFileMenu() {	
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EFileMenuItem eMenuItem : GEConstant.EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
}
