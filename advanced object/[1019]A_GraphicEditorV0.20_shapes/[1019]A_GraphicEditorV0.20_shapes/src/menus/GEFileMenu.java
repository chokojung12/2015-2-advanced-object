package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEFileMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;

	public GEFileMenu() {		
		super();

		this.menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EFileMenuItem eFileMenuItem: GEConstant.EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(eFileMenuItem.getName());
			
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
}
