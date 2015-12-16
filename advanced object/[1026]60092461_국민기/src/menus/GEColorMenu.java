package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;


public class GEColorMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;

	public GEColorMenu() {		
		super();
		
		this.menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EColorMenuItem eMenuItem: GEConstant.EColorMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(eMenuItem.getName());
			
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
}
