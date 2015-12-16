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
	/*
	public GEMenuBar(){
		
		
		JMenu fileMenu = new JMenu("file");		
		this.add(fileMenu);
		
		JMenu editMenu = new JMenu("Edit");
		this.add(editMenu);
		
		JMenu colorMenu = new JMenu("Color");
		this.add(colorMenu);
	}*/
	
	private Vector<GEMenu> menus;

	public GEMenuBar() {
		
		menus = new Vector<GEMenu>();
		for(int i = 0; i < GEConstants.menuNames.length; i++){
			GEMenu menu = new GEMenu(GEConstants.menuNames[i],GEConstants.menuItemNames[i]);
			menus.add(menu);
			this.add(menu);
		}

	}
}
