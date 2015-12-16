package main;

import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GEMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<GEFileMenu> menus;

	public GEMenuBar() {
		
		menus = new Vector<GEFileMenu>();
		for(int i = 0; i < GEConstants.menuNames.length; i++){
			GEFileMenu menu = new GEFileMenu(GEConstants.menuNames[i],GEConstants.menuItemNames[i]);
			menus.add(menu);
			this.add(menu);
		}

	}
}
