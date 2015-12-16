package main;

import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GEMenuBar extends JMenuBar {
	
	private Vector<GEFileMenu> fileMenus;

	public GEMenuBar() {
		
		fileMenus = new Vector<GEFileMenu>();
		for(int i = 0; i < GEConstants.FILE_MENUS.length; i++){
			GEFileMenu menuItem = new GEFileMenu(GEConstants.FILE_MENUS[i],GEConstants.FILE_MENU_ITEMS[i]);
			fileMenus.add(menuItem);
			this.add(menuItem);
		}

	}
}
